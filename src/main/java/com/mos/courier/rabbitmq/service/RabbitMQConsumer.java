package com.mos.courier.rabbitmq.service;

import com.mos.courier.model.CourierMoveModel;

public interface RabbitMQConsumer {

    void recievedMessage(CourierMoveModel courierMoveModel);
}
