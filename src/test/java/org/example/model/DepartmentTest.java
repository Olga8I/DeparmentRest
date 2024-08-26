package org.example.model;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

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
    public void testDefaultConstructor() {
        Department department = new Department();
        assertNotNull(department.getUserList());
        assertTrue(department.getUserList().isEmpty());
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

        assertNotEquals(department1, "Some String");

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

    @Test
    public void testSetUserList() {
        Set<User> newUserList = new HashSet<>();
        User user1 = new User();
        user1.setId(1L);  // Устанавливаем id для user1
        User user2 = new User();
        user2.setId(2L);  // Устанавливаем id для user2
        newUserList.add(user1);
        newUserList.add(user2);

        department.setUserList(newUserList);
        assertEquals(newUserList, department.getUserList());
        assertEquals(2, department.getUserList().size());
    }


    @Test
    public void testUserListEmpty() {
        assertTrue(department.getUserList().isEmpty());
    }

    @Test
    public void testUserListAddAndRemove() {
        User user = new User();
        user.setId(1L);
        department.getUserList().add(user);
        assertTrue(department.getUserList().contains(user));

        department.getUserList().remove(user);
        assertFalse(department.getUserList().contains(user));
        assertTrue(department.getUserList().isEmpty());
    }
}
