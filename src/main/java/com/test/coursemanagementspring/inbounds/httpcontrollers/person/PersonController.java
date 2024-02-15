package com.test.coursemanagementspring.inbounds.httpcontrollers.person;

import com.test.coursemanagementspring.core.errors.ValidationException;
import com.test.coursemanagementspring.core.person.adapters.PersonServiceAdapter;
import com.test.coursemanagementspring.core.person.entities.Person;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/person")
public class PersonController {
    private final PersonServiceAdapter personService;

    PersonController(PersonServiceAdapter personService) {
        this.personService = personService;
    }

    @GetMapping("/{id}")
    public Person getPersonById(@PathVariable("id") String idStr) {
        int id;
        try {
            id = Integer.parseInt(idStr);
        } catch (NumberFormatException e) {
            throw new ValidationException(String.format("'%s' is not a valid number", idStr));
        }

        return this.personService.getPerson(id);
    }

    @GetMapping("/name/{name}")
    public Person getPersonByName(@PathVariable("name") String name) {
        return this.personService.getPerson(name);
    }
}
