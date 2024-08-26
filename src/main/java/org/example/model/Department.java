package org.example.model;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * The Department where User works
 * Relation:
 * Many To Many: Department <-> User
 */

@Entity
@Table(name = "departments")
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "department_id")
    private Long id;
    @Column (name = "department_name")
    private String name;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_department", joinColumns = @JoinColumn (name = "department_id"),
            inverseJoinColumns = @JoinColumn (name = "user_id"))
    private Set<User> userList;

    public Department(){
        userList = new HashSet<>();
    }
    public Department(String name){
        userList = new HashSet<>();
        this.name = name;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) { this.id = id; }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<User> getUserList() {
        return userList;
    }

    public void setUserList(Set<User> userList) {
        this.userList = userList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Department)) return false;
        Department department = (Department) o;
        return Objects.equals(id, department.id) &&
                Objects.equals(name, department.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, userList);
    }

    @Override
    public String toString() {
        return "Department{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", userList=" + userList +
                '}';
    }
}