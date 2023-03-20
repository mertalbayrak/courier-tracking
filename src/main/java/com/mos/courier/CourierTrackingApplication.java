package com.mos.courier;

import com.mos.courier.rabbitmq.config.RabbitMQConfig;
import com.mos.courier.rabbitmq.config.RabbitMQListenerConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import({RabbitMQConfig.class, RabbitMQListenerConfig.class})
public class CourierTrackingApplication {

    public static void main(String[] args) {
        SpringApplication.run(CourierTrackingApplication.class, args);
    }

}
