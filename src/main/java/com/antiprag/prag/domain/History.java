package com.antiprag.prag.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name =  "history")

public class History implements Serializable{
       
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "schedule_id")
    private Integer schedule_id;
    @Column(name = "property_id")
    private Integer property_id;
    @Column(name = "plague_id")
    private Integer plague_id;
    @Column(name = "service_id")
    private Integer service_id;
    @Column(name = "technician_id")
    private Integer technician_id;
    @Column(name = "status_id")
    private Integer status_id;
    @Column(name = "notes")
    private String notes;
    @Column(name = "certificate_url")
    private String certificate_url;
    @Column(name = "created_by")
    private Integer created_by;
    @Column(name = "edited_by")
    private Integer edited_by;
    @Column(name = "created_at")
    private Date created_at;
    @Column(name = "updated_at")
    private Date updated_at;
    @Column(name = "execution_date")
    private Date execution_date;


}
