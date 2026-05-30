package com.antiprag.prag.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.sql.Date;
import java.time.Instant;
import java.util.Set;

import org.hibernate.annotations.DynamicInsert;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name =  "customers")
@DynamicInsert

public class Customer implements Serializable{
       
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "name")
    private String name;
    @Column(name = "cpf")
    private String cpf;
    @Column(name = "cnpj")
    private String cnpj;
    @Column(name = "phone")
    private String phone;
    @Column(name = "email")
    private String email;
    @Column(name = "created_by")
    private Integer created_by;
    @Column(name = "edited_by")
    private Integer edited_by;
    @Column(name = "created_at")
    private Instant created_at;
    @Column(name = "updated_at")
    private Instant updated_at;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "customer")
    private Set<Properties> properties;
}
