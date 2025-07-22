package com.example.ClaimManagementSystem.model;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import java.util.Date;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="claim_photo")
public class ClaimPhoto {

    @Id
    @Column(name="id", nullable=false, unique=true)
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column(name="uuid", nullable=false, unique=true)
    private String uuid;

    @Basic
    @Column(name="path", length=255, nullable=false, unique=true)
    private String path;

    @Basic
    @Column(name="file_name")
    private String OriginalFileName;

    @Basic
    @Column(name="content_type")
    private String contentType;

    @Basic
    @Column(name="size")
    private Long size;

    @Basic
    @Column(name="claim_id", nullable=false)
    private Long claimId;

    @Column(name="created_at", nullable=false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
}