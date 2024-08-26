package org.example.model;

import jakarta.persistence.*;

import java.util.Objects;

/**
 * Phone entity
 * Many To One: PhoneNumber - User
 */
@Entity
@Table(name = "phone_numbers")
public class PhoneNumber {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "user_id")
    private Long id;
    @Column (name = "phonenumber_number")
    private String number;

    @ManyToOne (fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id",  insertable = false, updatable = false)
    private User user;

    public PhoneNumber() {
    }

    public PhoneNumber(String number, User user) {
        this.number = number;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PhoneNumber)) return false;
        PhoneNumber that = (PhoneNumber) o;
        return Objects.equals(id, that.id) && // Сравнение id
                Objects.equals(number, that.number) &&
                Objects.equals(user != null ? user.getId() : null,
                        that.user != null ? that.user.getId() : null);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, number, user != null ? user.getId() : null);
    }

    @Override
    public String toString() {
        return "PhoneNumber{" +
                "id=" + id +
                ", number='" + number + '\'' +
                ", userId=" + (user != null ? user.getId() : "null") +
                '}';
    }
}