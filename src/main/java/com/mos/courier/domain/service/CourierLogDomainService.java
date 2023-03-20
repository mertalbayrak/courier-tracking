package com.mos.courier.domain.service;

import com.mos.courier.domain.entity.CourierLog;
import com.mos.courier.domain.entity.Store;
import com.mos.courier.domain.repository.CourierLogRepository;
import com.mos.courier.model.CourierMoveModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CourierLogDomainService {

    private final CourierLogRepository courierLogRepository;

    public void save(CourierMoveModel courierMoveModel, Store store, Double totalDistance) {
        CourierLog courierLog = new CourierLog();
        courierLog.setCourierId(courierMoveModel.getCourierId());
        courierLog.setLat(courierMoveModel.getLat());
        courierLog.setLng(courierMoveModel.getLng());
        courierLog.setTotalDistance(totalDistance);
        courierLog.setTimestamp(LocalDateTime.now());
        courierLog.setStore(store);
        courierLogRepository.save(courierLog);
    }

    public Long checkCourierLastOneMinuteLogCount(Long courierId) {
        return courierLogRepository.getLastOneMinuteLog(courierId, LocalDateTime.now().minusMinutes(1));
    }

    public CourierLog fetchLastLogData(Long courierId) {
        return courierLogRepository.getLastLogData(courierId);
    }

    public List<CourierLog> getCourierLogList(Long courierId) {
        return courierLogRepository.findByCourierId(courierId);
    }
}
