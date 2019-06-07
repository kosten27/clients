package com.kostenko.repositories;

import com.kostenko.models.Call;
import com.kostenko.models.CallStatisticsByCities;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface CallRepository extends JpaRepository<Call, Long> {
    @Query("SELECT new com.kostenko.models.CallStatisticsByCities(c.city, COUNT(c)) FROM Call c " +
            "GROUP BY c.city ORDER BY COUNT(c) DESC")
    public List<CallStatisticsByCities> getNumberOfCalls();

    public Call findFirstByClient_IdAndDateBetweenOrderByDurationDesc(Long clientId, Date start, Date end);
}
