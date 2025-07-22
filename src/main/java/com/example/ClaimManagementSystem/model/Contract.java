package com.example.ClaimManagementSystem.model;

import lombok.*;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.Basic;
import java.util.Date;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="contract")
public class Contract {

    @Id
    @Column(name="id", nullable=false, unique=true)
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column(name="uuid", nullable=false, unique=true)
    private String uuid;

    @Basic
    @Column(name="contract_number", nullable = false, unique = true)
    private String contractNumber;

    @Column(name="start_date", nullable=false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date startDate;

    @Column(name="end_date",nullable=false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date endDate;

    @Basic
    @Column(name="insured_id", nullable=false)
    private Long insuredId;

    @Basic
    @Column(name="vehicle_plate", length=15, nullable=false)
    private String vehiclePlate;


}
