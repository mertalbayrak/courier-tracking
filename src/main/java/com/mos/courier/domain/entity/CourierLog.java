package com.mos.courier.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "courier_log")
@Getter
@Setter
public class CourierLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "timestamp")
    private LocalDateTime timestamp;

    @Column(name = "lat")
    private Double lat;

    @Column(name = "lng")
    private Double lng;

    @Column(name = "total_distance")
    private Double totalDistance;

    @Column(name = "courierId")
    private Long courierId;

    @ManyToOne
    @JoinColumn(name = "storeId")
    private Store store;

}
