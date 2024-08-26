package org.example.model;

import jakarta.persistence.*;
import java.util.List;

/**
 * Role entity
 * Many to One: User -> Role
 */

@Entity
@Table(name = "roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "role_id")
    private Long id;
    @Column (name = "role_name")
    private String name;

    @OneToMany(mappedBy = "role")
    private List<User> users;

    public Role() {
    }

    public Role(String name, List<User> users) {
        this.users = users;
        this.name = name;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", users=" + users +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Role)) return false;
        Role role = (Role) o;

        if (id != null && role.id != null) {
            return id.equals(role.id);
        }

        return name != null && name.equals(role.name);
    }

    @Override
    public int hashCode() {
        int result = (id != null ? id.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

}
