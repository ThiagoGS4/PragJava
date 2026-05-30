package com.antiprag.prag.domain;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.sql.Date;
import java.time.Instant;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name =  "schedules")

public class Schedules implements Serializable{
       
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "created_by")
    private Integer created_by;
    @Column(name = "scheduled_start")
    private Date scheduled_start;
    @Column(name = "scheduled_end")
    private Date scheduled_end;
    @Column(name = "scheduled_at")
    private Date scheduled_at;
    @Column(name = "notes")
    private String notes;
    @Column(name = "deleted_at")
    private Date deleted_at;
    @Column(name = "created_at")
    private Instant created_at;
    @Column(name = "updated_at")
    private Date updated_at;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "plague_id")
    private Plagues plagues;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "status_id")
    private Status status;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "services_id")
    private Services services;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "property_id", nullable = false)
    private Properties properties;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "assined_technician_id", nullable = false)
    private Users users;
}

