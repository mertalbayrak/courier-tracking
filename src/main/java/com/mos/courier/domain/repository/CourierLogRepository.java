package com.mos.courier.domain.repository;

import com.mos.courier.domain.entity.CourierLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface CourierLogRepository extends JpaRepository<CourierLog, Long> {

    @Query("SELECT COUNT(1) FROM CourierLog c WHERE c.courierId = :courierId AND c.timestamp >= :timestamp")
    Long getLastOneMinuteLog(@Param("courierId") Long courierId, @Param("timestamp") LocalDateTime timestamp);

    @Query("SELECT c FROM CourierLog c WHERE c.courierId = :courierId ORDER BY c.timestamp DESC LIMIT 1")
    CourierLog getLastLogData(@Param("courierId") Long courierId);

    List<CourierLog> findByCourierId(Long courierId);
}
