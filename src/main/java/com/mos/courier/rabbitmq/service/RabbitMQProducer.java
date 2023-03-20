package com.mos.courier.rabbitmq.service;

import com.mos.courier.model.CourierMoveModel;

public interface RabbitMQProducer {

    void send(CourierMoveModel courierMoveModel);
}
