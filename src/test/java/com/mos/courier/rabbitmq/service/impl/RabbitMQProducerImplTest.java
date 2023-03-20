package com.mos.courier.rabbitmq.service.impl;

import com.mos.courier.model.CourierMoveModel;
import com.mos.courier.rabbitmq.config.RabbitMQConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.amqp.core.AmqpTemplate;

@ExtendWith(MockitoExtension.class)
public class RabbitMQProducerImplTest {

    @Mock
    private AmqpTemplate rabbitTemplate;

    @Mock
    private RabbitMQConfig config;

    @InjectMocks
    private RabbitMQProducerImpl rabbitMQProducer;

    @BeforeEach
    public void setUp() {
        rabbitMQProducer = new RabbitMQProducerImpl(rabbitTemplate, config);
    }

    @Test
    public void test_send() {
        CourierMoveModel courierMoveModel = new CourierMoveModel();
        courierMoveModel.setCourierId(1L);
        courierMoveModel.setLat(1D);
        courierMoveModel.setLng(1D);
        rabbitMQProducer.send(courierMoveModel);
    }
}