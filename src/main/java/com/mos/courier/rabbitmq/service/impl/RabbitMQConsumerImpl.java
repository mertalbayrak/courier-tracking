package com.mos.courier.rabbitmq.service.impl;

import com.mos.courier.common.constants.CourierTrackingConstants;
import com.mos.courier.common.utils.CourierTrackingUtils;
import com.mos.courier.domain.entity.CourierLog;
import com.mos.courier.domain.entity.Store;
import com.mos.courier.domain.service.CourierLogDomainService;
import com.mos.courier.domain.service.StoreDomainService;
import com.mos.courier.model.CourierMoveModel;
import com.mos.courier.rabbitmq.service.RabbitMQConsumer;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RabbitMQConsumerImpl implements RabbitMQConsumer {

    private final CourierLogDomainService courierLogDomainService;

    private final StoreDomainService storeDomainService;

    @RabbitListener(queues = "couriertracking.queue")
    public void recievedMessage(CourierMoveModel courierMoveModel) {

        Long courierLastOneMinuteLogCount = courierLogDomainService.checkCourierLastOneMinuteLogCount(courierMoveModel.getCourierId());
        if (courierLastOneMinuteLogCount != 0) {
            return;
        }
        List<Store> storeList = storeDomainService.fetchAllStores();
        storeList.forEach(store -> {
            double distanceOfCourierToStore = CourierTrackingUtils.haversine(courierMoveModel.getLat(), courierMoveModel.getLng(), store.getLat(), store.getLng());
            if (distanceOfCourierToStore <= CourierTrackingConstants.STORES_RADIUS_METER) {
                courierLogDomainService.save(courierMoveModel, store, getDistance(courierMoveModel));
            }
        });
    }

    private double getDistance(CourierMoveModel courierMoveModel) {
        CourierLog courierLog = courierLogDomainService.fetchLastLogData(courierMoveModel.getCourierId());
        if (courierLog != null) {
            return CourierTrackingUtils.haversine(courierMoveModel.getLat(), courierMoveModel.getLng(), courierLog.getLat(), courierLog.getLng());
        }
        return 0;
    }
}
