package com.kostenko.repositories;

import com.kostenko.models.PhoneNumber;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhoneNumberRepository extends JpaRepository<PhoneNumber, Long> {
    public boolean existsByNumber(String number);
    public PhoneNumber findByNumber(String number);
}
