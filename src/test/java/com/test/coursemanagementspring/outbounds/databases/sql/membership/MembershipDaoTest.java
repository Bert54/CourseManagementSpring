package com.test.coursemanagementspring.outbounds.databases.sql.membership;

import com.test.coursemanagementspring.core.common.errors.AlreadyExistsException;
import com.test.coursemanagementspring.core.common.errors.NotFoundException;
import com.test.coursemanagementspring.core.services.classs.entities.Membership;
import com.test.coursemanagementspring.libs.logger.adapters.LoggerAdapter;
import com.test.coursemanagementspring.outbounds.databases.sql.classs.ClassDao;
import com.test.coursemanagementspring.outbounds.databases.sql.classs.MembershipDao;
import com.test.coursemanagementspring.outbounds.databases.sql.classs.entities.MembershipEntity;
import com.test.coursemanagementspring.outbounds.databases.sql.classs.entities.transformer.MembershipTransformerAdapter;
import com.test.coursemanagementspring.outbounds.databases.sql.classs.repositories.MembershipRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

public class MembershipDaoTest {
    private Dependencies dependencies;
    private MembershipDao membershipDao;

    @BeforeEach
    public void setupDependencies() {
        this.dependencies = new Dependencies();
        this.dependencies.logger = Mockito.mock(LoggerAdapter.class);
        this.dependencies.membershipRepository = Mockito.mock(MembershipRepository.class);
        this.dependencies.membershipTransformer = Mockito.mock(MembershipTransformerAdapter.class);
        this.dependencies.classDao = Mockito.mock(ClassDao.class);

        this.membershipDao = new MembershipDao(
                dependencies.logger,
                dependencies.membershipTransformer,
                dependencies.membershipRepository,
                dependencies.classDao);
    }

    @Test
    @DisplayName("Test save - OK")
    public void TestSaveOK() {
        var personId = 1;
        var className = "soldier";
        Membership m = Mockito.mock(Membership.class);
        when(m.getClassName()).thenReturn(className);
        when(m.getPersonId()).thenReturn(1);
        when(this.dependencies.membershipRepository.find(personId, className)).thenReturn(null);

        assertSame(m, this.membershipDao.save(m));
        verify(this.dependencies.classDao, times(1)).find(className);
        verify(this.dependencies.membershipRepository, times(1)).find(personId, className);
        verify(this.dependencies.membershipRepository, times(1)).save(personId, className);
    }

    @Test
    @DisplayName("Test save - Class does not exist")
    public void TestSaveClassNotFoundError() {
        var personId = 1;
        var className = "soldier";
        Membership m = Mockito.mock(Membership.class);
        when(m.getClassName()).thenReturn(className);
        when(m.getPersonId()).thenReturn(1);

        when(this.dependencies.classDao.find(anyString())).thenThrow(new NotFoundException("I am error"));

        assertThrows(NotFoundException.class, () -> this.membershipDao.save(m));
        verify(this.dependencies.classDao, times(1)).find(className);
        verify(this.dependencies.membershipRepository, times(0)).find(personId, className);
        verify(this.dependencies.membershipRepository, times(0)).save(personId, className);
    }

    @Test
    @DisplayName("Test save - Membership already exists")
    public void TestSaveMembershipAlreadyExistsError() {
        var personId = 1;
        var className = "soldier";
        Membership m = Mockito.mock(Membership.class);
        when(m.getClassName()).thenReturn(className);
        when(m.getPersonId()).thenReturn(1);

        when(this.dependencies.membershipRepository.find(anyInt(), anyString())).thenThrow(new AlreadyExistsException("I am error"));

        assertThrows(AlreadyExistsException.class, () -> this.membershipDao.save(m));
        verify(this.dependencies.classDao, times(1)).find(className);
        verify(this.dependencies.membershipRepository, times(1)).find(personId, className);
        verify(this.dependencies.membershipRepository, times(0)).save(personId, className);
    }

    @Test
    @DisplayName("Test find - OK")
    public void TestFindOK() {
        var membershipEntity = Mockito.mock(MembershipEntity.class);
        when(this.dependencies.membershipRepository.find(anyInt(), anyString())).thenReturn(membershipEntity);

        var membership = Mockito.mock(Membership.class);
        when(membershipEntity.toCoreMembership()).thenReturn(membership);

        assertSame(membership, this.membershipDao.find(anyInt(), anyString()));
        verify(this.dependencies.membershipRepository, times(1)).find(anyInt(), anyString());
        verify(membershipEntity, times(1)).toCoreMembership();
    }

    @Test
    @DisplayName("Test find - Membership not found")
    public void TestFindMembershipNotFoundError() {
        when(this.dependencies.membershipRepository.find(anyInt(), anyString())).thenThrow(new NotFoundException("I am error"));
        assertThrows(NotFoundException.class, () -> this.membershipDao.find(anyInt(), anyString()));
    }

    @Test
    @DisplayName("Test delete - OK")
    public void TestDeleteOK() {
        var personId = 1;
        var className = "soldier";

        var membershipEntity = Mockito.mock(MembershipEntity.class);
        when(this.dependencies.membershipRepository.find(anyInt(), anyString())).thenReturn(membershipEntity);

        var membership = Mockito.mock(Membership.class);
        when(membershipEntity.toCoreMembership()).thenReturn(membership);

        when(this.dependencies.membershipRepository.delete(anyInt(), anyString())).thenReturn(1);

        assertSame(membership, this.membershipDao.delete(personId, className));
        verify(this.dependencies.classDao, times(1)).find(className);
        verify(this.dependencies.membershipRepository, times(1)).find(personId, className);
        verify(this.dependencies.membershipRepository, times(1)).delete(personId, className);
    }

    @Test
    @DisplayName("Test delete - Class does not exist")
    public void TestDeleteClassNotFound() {
        var personId = 1;
        var className = "soldier";

        when(this.dependencies.classDao.find(anyString())).thenThrow(new NotFoundException("I am error"));

        assertThrows(NotFoundException.class, () -> this.membershipDao.delete(personId, className));
        verify(this.dependencies.classDao, times(1)).find(className);
        verify(this.dependencies.membershipRepository, times(0)).find(personId, className);
        verify(this.dependencies.membershipRepository, times(0)).delete(personId, className);
    }

    @Test
    @DisplayName("Test delete - Membership does not exist")
    public void TestDeleteMembershipDoesNotExist() {
        var personId = 1;
        var className = "soldier";

        when(this.dependencies.membershipRepository.find(anyInt(), anyString())).thenThrow(new NotFoundException("I am error"));

        assertThrows(NotFoundException.class, () -> this.membershipDao.delete(personId, className));
        verify(this.dependencies.classDao, times(1)).find(className);
        verify(this.dependencies.membershipRepository, times(1)).find(personId, className);
        verify(this.dependencies.membershipRepository, times(0)).delete(personId, className);
    }
}
