package com.example.ClaimManagementSystem.model;

import com.example.ClaimManagementSystem.ClaimStatus;
import lombok.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.Basic;

import java.time.Instant;
import java.util.Date;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.EnumType;
import org.hibernate.annotations.CreationTimestamp;


@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="claim")
public class Claim {


    @Id
    @Basic
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id", unique=true, nullable=false)
    private Long id;

    @Column(name="uuid", unique=true, nullable=false)
    private String uuid;

    @Basic
    @Column(name="claim_number", nullable = false, unique=true)
    private String claimNumber;

    @Column(name="creation_date")
    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private Date creationDate;

    @Column(name="accident_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date accidentDate;

    @Enumerated(EnumType.STRING)
    private ClaimStatus status=ClaimStatus.Ouvert;

    @Column(name="contract_id", nullable=false)
    private Long contractId;

}