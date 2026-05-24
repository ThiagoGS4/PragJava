package com.antiprag.prag.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.sql.Date;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import java.util.Collection;

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
    @Column(name = "property_id")
    private Integer property_id;
    @Column(name = "plague_id")
    private Integer plague_id;
    @Column(name = "service_id")
    private Integer service_id;
    @Column(name = "created_by")
    private Integer created_by;
    @Column(name = "assined_technician_id")
    private Integer assined_technician_id;
    @Column(name = "status_id")
    private Integer status_id;
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
    private Date created_at;
    @Column(name = "updated_at")
    private Date updated_at;

}
