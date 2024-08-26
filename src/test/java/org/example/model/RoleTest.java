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
    void testDefaultConstructor() {
        Role defaultRole = new Role();
        assertNull(defaultRole.getId());
        assertNull(defaultRole.getName());
        assertNotNull(defaultRole.getUsers());
        assertTrue(defaultRole.getUsers().isEmpty());
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
        Role role1 = new Role("Admin", new ArrayList<>());
        Role role2 = new Role("Admin", new ArrayList<>());
        Role role3 = new Role("User", new ArrayList<>());
        Role role4 = new Role("Admin", new ArrayList<>());

        role1.setId(1L);
        role2.setId(1L);
        role3.setId(2L);

        assertEquals(role1, role2, "Roles with same id and name should be equal");

        assertNotEquals(role1, role3, "Roles with different ids should not be equal");

        assertNotEquals(role1, null, "Role should not be equal to null");

        assertNotEquals(role1, new Object(), "Role should not be equal to an instance of a different class");

        role1.setId(null);
        role2.setId(null);
        assertEquals(role1, role2, "Roles with same name and null id should be equal");

        assertNotEquals(role1, role3, "Roles with different names should not be equal");

        assertEquals(role1.hashCode(), role2.hashCode(), "Hash codes should be equal for equivalent roles");

        assertNotEquals(role1.hashCode(), role3.hashCode(), "Hash codes should not be equal for non-equivalent roles");

        role1.setId(null);
        role2.setId(null);
        assertEquals(role1.hashCode(), role2.hashCode(), "Hash codes should be equal for roles with same name and null id");
    }

    @Test
    void testSetId() {
        role.setId(1L);
        assertEquals(1L, role.getId());
    }

    @Test
    void testAddUser() {
        User user = new User();
        users.add(user);
        role.setUsers(users);
        assertTrue(role.getUsers().contains(user));

        user.setRole(role);
        assertEquals(role, user.getRole());
    }
}