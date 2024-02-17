package com.test.coursemanagementspring.inbounds.httpcontrollers.filters.checkpermission;

import com.test.coursemanagementspring.core.errors.ForbiddenException;
import com.test.coursemanagementspring.core.errors.NotFoundException;
import com.test.coursemanagementspring.core.errors.UnauthorizedException;
import com.test.coursemanagementspring.core.errors.ValidationException;
import com.test.coursemanagementspring.core.services.person.adapters.PersonDaoAdapter;
import com.test.coursemanagementspring.core.services.person.entities.Person;
import jakarta.servlet.http.HttpServletRequest;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Aspect
@Component
public class CheckPermissionAspect {
    public static final String permissionHeader = "x-personid";

    private final PersonDaoAdapter personDao;

    public CheckPermissionAspect(PersonDaoAdapter personDao) {
        this.personDao = personDao;
    }

    @Around("@annotation(checkPermission)")
    public Object doCheckPermission(ProceedingJoinPoint joinPoint, CheckPermission checkPermission) throws Throwable {
        HttpServletRequest req = getRequest();
        if (req == null) {
            // ignore this aspect if req is null
            return joinPoint.proceed();
        }

        String permissionToCheck = checkPermission.permission();
        if (permissionToCheck == null || permissionToCheck.isEmpty()) {
            // ignore this aspect if no permission to check was provided
            return joinPoint.proceed();
        }

        String idStr = req.getHeader(permissionHeader);
        if (idStr == null || idStr.isEmpty()) {
            throw new UnauthorizedException(String.format("Authentication: '%s' header is missing or empty", permissionHeader));
        }

        int id;
        try {
            id = Integer.parseInt(idStr);
        } catch (NumberFormatException e) {
            throw new ValidationException(String.format("Authentication: '%s' header content is not a valid number", idStr));
        }

        Person person;
        try {
            person = this.personDao.find(id);
        } catch (NotFoundException e) {
            throw new NotFoundException(String.format("Authentication: '%s'", e.getMessage()));
        }

        if (!person.getPermissions().contains(permissionToCheck)) {
            throw new ForbiddenException("Person is not allowed to perform this action");
        }

        return joinPoint.proceed();
    }


    private HttpServletRequest getRequest() {
        ServletRequestAttributes sra = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (sra != null) {
            return sra.getRequest();
        }
        return null;
    }
}
