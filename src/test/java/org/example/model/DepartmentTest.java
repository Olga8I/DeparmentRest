package org.example.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class DepartmentTest {

    private Department department;

    @BeforeEach
    void setUp() {
        department = new Department("IT");
    }

    @Test
    void testConstructorWithoutParameters() {
        Department dep = new Department();
        assertNotNull(dep.getUserList());
        assertTrue(dep.getUserList().isEmpty());
    }

    @Test
    void testConstructorWithName() {
        assertEquals("IT", department.getName());
        assertNotNull(department.getUserList());
        assertTrue(department.getUserList().isEmpty());
    }

    @Test
    void testGetIdAndSetId() {
        department.setId(1L);
        assertEquals(1L, department.getId());
    }

    @Test
    void testGetNameAndSetName() {
        department.setName("HR");
        assertEquals("HR", department.getName());
    }

    @Test
    void testGetUserListAndSetUserList() {
        Set<User> users = new HashSet<>();
        users.add(new User());
        assertEquals(users, department.getUserList());
    }

    @Test
    void testEquals() {
        Department dep1 = new Department("Finance");
        dep1.setId(1L);
        Department dep2 = new Department("Finance");
        dep2.setId(1L);

        assertEquals(dep1, dep2);

        dep2.setId(2L);
        assertNotEquals(dep1, dep2);
    }

    @Test
    void testHashCode() {
        Department dep1 = new Department();
        dep1.setId(1L);
        Department dep2 = new Department();
        dep2.setId(1L);

        assertEquals(dep1.hashCode(), dep2.hashCode());

        dep2.setId(2L);
        assertNotEquals(dep1.hashCode(), dep2.hashCode());
    }

    @Test
    void testToString() {
        String expected = "Department{id=null, name='IT', userList=[]}";
        assertEquals(expected, department.toString());
    }
}
