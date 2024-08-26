package org.example.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RoleTest {
    private Role role;
    private List<User> users;

    @BeforeEach
    void setUp() {
        users = new ArrayList<>();
        role = new Role("Admin", users);
    }

    @Test
    void testConstructorAndGetters() {
        assertEquals("Admin", role.getName());
        assertTrue(role.getUsers().isEmpty());
        assertNull(role.getId());
    }

    @Test
    void testSetters() {
        role.setName("User");
        assertEquals("User", role.getName());

        List<User> newUsers = new ArrayList<>();
        User user = new User();
        newUsers.add(user);
        role.setUsers(newUsers);
        assertEquals(newUsers, role.getUsers());
    }

    @Test
    void testToString() {
        String expected = "Role{id=null, name='Admin', users=[]}";
        assertEquals(expected, role.toString());
    }

    @Test
    void testEqualsAndHashCode() {
        Role anotherRole = new Role("Admin", new ArrayList<>());
        Role yetAnotherRole = new Role("Admin", users);
        role.setId(1L);
        anotherRole.setId(1L);
        yetAnotherRole.setId(2L);

        assertEquals(role, anotherRole);
        assertNotEquals(role, yetAnotherRole);
        assertNotEquals(role, null);
        assertNotEquals(role, new Object());

        assertEquals(role.hashCode(), anotherRole.hashCode());
        assertNotEquals(role.hashCode(), yetAnotherRole.hashCode());
    }

    @Test
    void testSetId() {
        role.setId(1L);
        assertEquals(1L, role.getId());
    }
}

