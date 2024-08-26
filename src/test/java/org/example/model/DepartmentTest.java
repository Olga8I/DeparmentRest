package org.example.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class DepartmentTest {

    private Department department;

    @BeforeEach
    public void setUp() {
        department = new Department("Engineering");
    }

    @Test
    public void testConstructor() {
        Department engineering = new Department("Engineering");
        assertEquals("Engineering", engineering.getName());
        assertNotNull(engineering.getUserList());
    }

    @Test
    public void testGettersAndSetters() {
        department.setId(1L);
        assertEquals(1L, department.getId());

        department.setName("HR");
        assertEquals("HR", department.getName());

        User user = new User();
        user.setId(1L);
        department.getUserList().add(user);
        assertTrue(department.getUserList().contains(user));
    }

    @Test
    public void testEqualsAndHashCode() {
        Department department1 = new Department("Engineering");
        department1.setId(1L);

        Department department2 = new Department("Engineering");
        department2.setId(1L);

        assertEquals(department1, department2);
        assertEquals(department1.hashCode(), department2.hashCode());

        department2.setId(2L);
        assertNotEquals(department1, department2);

        Object nonDepartmentObject = new Object();
        assertNotEquals(department1, nonDepartmentObject);

        assertNotEquals(department1, null);

        assertEquals(department1, department1);
    }

    @Test
    public void testToString() {
        String expectedString = "Department{id=null, name='Engineering', userList=[]}";
        assertEquals(expectedString, department.toString());
    }

    @Test
    public void testUserListManipulation() {
        User user1 = new User();
        user1.setId(1L);
        department.getUserList().add(user1);

        User user2 = new User();
        user2.setId(2L);
        department.getUserList().add(user2);

        Set<User> users = new HashSet<>();
        users.add(user1);
        users.add(user2);

        assertEquals(users, department.getUserList());
    }
}
