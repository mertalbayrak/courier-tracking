package com.mos.courier.rabbitmq.service.impl;

import com.mos.courier.model.CourierMoveModel;
import com.mos.courier.rabbitmq.config.RabbitMQConfig;
import com.mos.courier.rabbitmq.service.RabbitMQProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RabbitMQProducerImpl implements RabbitMQProducer {

    private final AmqpTemplate rabbitTemplate;

    private final RabbitMQConfig config;

    @Override
    public void send(CourierMoveModel courierMoveModel) {
        rabbitTemplate.convertAndSend(config.getExchange(), config.getRoutingKey(), courierMoveModel);
    }
}
