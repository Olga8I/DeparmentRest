package org.example.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RoleTest {

    @Test
    void testRoleConstructorAndGetters() {
        Role role = new Role("ADMIN", null);

        assertNotNull(role);
        assertEquals("ADMIN", role.getName());
        assertNull(role.getId());
    }

    @Test
    void testSetName() {
        Role role = new Role();
        role.setName("USER");

        assertEquals("USER", role.getName());
    }

    @Test
    void testSetId() {
        Role role = new Role();
        role.setId(1L);

        assertEquals(1L, role.getId());
    }

    @Test
    void testToString() {
        Role role = new Role("GUEST", null);
        role.setId(3L);

        String expectedString = "Role{id=3, name='GUEST'}";
        assertEquals(expectedString, role.toString());
    }

    @Test
    void testEquals() {
        Role role1 = new Role("ADMIN", null);
        role1.setId(1L);
        Role role2 = new Role("ADMIN", null);
        role2.setId(1L);
        Role role3 = new Role("USER", null);
        role3.setId(2L);

        assertEquals(role1, role2);
        assertNotEquals(role1, role3);

        assertNotEquals(role1, null);
        assertNotEquals(role1, "Some String");
    }

    @Test
    void testHashCode() {
        Role role1 = new Role("ADMIN", null);
        role1.setId(1L);
        Role role2 = new Role("ADMIN", null);
        role2.setId(1L);

        assertEquals(role1.hashCode(), role2.hashCode());

        role2.setId(2L);
        assertNotEquals(role1.hashCode(), role2.hashCode());
    }
}