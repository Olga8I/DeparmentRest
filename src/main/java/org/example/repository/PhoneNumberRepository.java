package org.example.repository;

import org.example.model.PhoneNumber;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface PhoneNumberRepository extends JpaRepository<PhoneNumber, Long> {

    boolean existsByNumber(String number);

    Optional<PhoneNumber> findByNumber(String number);
}
