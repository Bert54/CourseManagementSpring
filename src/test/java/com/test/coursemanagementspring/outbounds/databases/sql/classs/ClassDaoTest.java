package com.test.coursemanagementspring.outbounds.databases.sql.classs;

import com.test.coursemanagementspring.core.common.errors.AlreadyExistsException;
import com.test.coursemanagementspring.core.common.errors.DeletionException;
import com.test.coursemanagementspring.core.common.errors.NotFoundException;
import com.test.coursemanagementspring.core.services.classs.entities.Class;
import com.test.coursemanagementspring.core.services.person.entities.Person;
import com.test.coursemanagementspring.libs.logger.adapters.LoggerAdapter;
import com.test.coursemanagementspring.outbounds.databases.sql.classs.entities.ClassEntity;
import com.test.coursemanagementspring.outbounds.databases.sql.classs.entities.transformer.ClassTransformerAdapter;
import com.test.coursemanagementspring.outbounds.databases.sql.classs.repositories.ClassRepository;
import com.test.coursemanagementspring.outbounds.databases.sql.person.entities.PersonEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.dao.DataIntegrityViolationException;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class ClassDaoTest {
    private Dependencies dependencies;
    private ClassDao classDao;

    @BeforeEach
    public void setupDependencies() {
        this.dependencies = new Dependencies();
        this.dependencies.logger = Mockito.mock(LoggerAdapter.class);
        this.dependencies.classRepository = Mockito.mock(ClassRepository.class);
        this.dependencies.classTransformer = Mockito.mock(ClassTransformerAdapter.class);

        this.classDao = new ClassDao(dependencies.classRepository, dependencies.logger, dependencies.classTransformer);
    }

    @Test
    @DisplayName("Test find(String) - OK")
    public void TestFindStringOK() {
        var classEntity = Mockito.mock(com.test.coursemanagementspring.outbounds.databases.sql.classs.entities.ClassEntity.class);
        when(this.dependencies.classRepository.find(anyString())).thenReturn(classEntity);

        var cls = Mockito.mock(Class.class);
        when(classEntity.toCoreClass(anyBoolean())).thenReturn(cls);

        assertSame(cls, this.classDao.find(anyString()));
        verify(this.dependencies.classRepository, times(1)).find(anyString());
        verify(classEntity, times(1)).toCoreClass(anyBoolean());
    }

    @Test
    @DisplayName("Test find(String) - Not found error")
    public void TestFindStringNotFound() {
        when(this.dependencies.classRepository.find(anyString())).thenReturn(null);
        assertThrows(NotFoundException.class, () -> this.classDao.find(anyString()));
    }

    @Test
    @DisplayName("Test save - OK")
    public void TestSaveOK() {
        var classDaoSpy = Mockito.spy(this.classDao);
        ClassEntity classEntity = Mockito.mock(ClassEntity.class);
        Class cls = Mockito.mock(Class.class);
        when(this.dependencies.classRepository.save(classEntity)).thenReturn(classEntity);
        when(classEntity.toCoreClass(anyBoolean())).thenReturn(cls);
        when(this.dependencies.classTransformer.toClassEntity(cls)).thenReturn(classEntity);

        assertSame(cls, classDaoSpy.save(cls));
        verify(this.dependencies.classRepository, times(1)).save(classEntity);
        verify(classEntity, times(1)).toCoreClass(anyBoolean());
        verify(classDaoSpy, times(1)).toClassEntity(cls);
        verify(this.dependencies.classTransformer, times(1)).toClassEntity(cls);
    }

    @Test
    @DisplayName("Test save - Already exist error")
    public void TestSaveAlreadyExistError() {
        var classDaoSpy = Mockito.spy(this.classDao);
        ClassEntity classEntity = Mockito.mock(ClassEntity.class);
        Class cls = Mockito.mock(Class.class);
        when(this.dependencies.classRepository.save(classEntity)).thenThrow(new DataIntegrityViolationException("I am error"));
        when(this.dependencies.classTransformer.toClassEntity(cls)).thenReturn(classEntity);

        assertThrows(AlreadyExistsException.class, () -> classDaoSpy.save(cls));
        verify(this.dependencies.classRepository, times(1)).save(classEntity);
        verify(classEntity, times(0)).toCoreClass(anyBoolean());
        verify(classDaoSpy, times(1)).toClassEntity(cls);
        verify(this.dependencies.classTransformer, times(1)).toClassEntity(cls);
    }

    @Test
    @DisplayName("Test delete - OK")
    public void TestDeleteOK() {
        var classDaoSpy = Mockito.spy(this.classDao);
        Class cls = Mockito.mock(Class.class);
        ClassEntity classEntity = Mockito.mock(ClassEntity.class);
        when(this.dependencies.classRepository.delete(anyString())).thenReturn(1);
        // The delete method relies on the find method to assert the class exists. Setup this part here
        when(this.dependencies.classRepository.find(anyString())).thenReturn(classEntity);
        when(classEntity.toCoreClass(anyBoolean())).thenReturn(cls);

        assertSame(cls, classDaoSpy.delete(anyString()));
        verify(this.dependencies.classRepository, times(1)).delete(anyString());
        verify(classDaoSpy, times(0)).toClassEntity(cls);
        verify(this.dependencies.classTransformer, times(0)).toClassEntity(cls);
    }

    @Test
    @DisplayName("Test delete - Class does not exist")
    public void TestDeleteClassDoesNotExist() {
        when(this.dependencies.classRepository.find(anyString())).thenReturn(null);

        assertThrows(NotFoundException.class, () -> this.classDao.delete(anyString()));
        verify(this.dependencies.classRepository, times(0)).delete(anyString());
    }

    @Test
    @DisplayName("Test delete - Class exists but can't be deleted from database")
    public void TestDeleteClassExistsButCantBeDeletedFromDatabase() {
        var classDaoSpy = Mockito.spy(this.classDao);
        Class cls = Mockito.mock(Class.class);
        ClassEntity classEntity = Mockito.mock(ClassEntity.class);
        // 0 means no lines removed
        when(this.dependencies.classRepository.delete(anyString())).thenReturn(0);
        // The delete method relies on the find method to assert the class exists. Setup this part here
        when(this.dependencies.classRepository.find(anyString())).thenReturn(classEntity);
        when(classEntity.toCoreClass(anyBoolean())).thenReturn(cls);

        assertThrows(DeletionException.class, () -> this.classDao.delete(anyString()));
        verify(this.dependencies.classRepository, times(1)).delete(anyString());
        verify(classDaoSpy, times(0)).toClassEntity(cls);
        verify(this.dependencies.classTransformer, times(0)).toClassEntity(cls);
    }
}
