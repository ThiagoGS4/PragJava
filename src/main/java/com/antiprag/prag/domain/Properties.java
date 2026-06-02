package com.antiprag.prag.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.io.Serializable;
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
@Table(name =  "properties")
@DynamicInsert

public class Properties implements Serializable{
       
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "nickname")
    private String nickname;
    @Column(name = "cep")
    private String cep;
    @Column(name = "street")
    private String street;
    @Column(name = "number")
    private String number;
    @Column(name = "complement")
    private String complement;
    @Column(name = "district")
    private String district;
    @Column(name = "city")
    private String city;
    @Column(name = "state")
    private String state;
    @Column(name = "country")
    private String country;
    @Column(name = "formatted_address")
    private String formatted_address;
    @Column(name = "google_place_id")
    private String google_place_id;
    @Column(name = "latitude")
    private Integer latitude;
    @Column(name = "longitude")
    private Integer longitude;
    @Column(name = "property_type")
    private String property_type;
    @Column(name = "is_active")
    private Integer is_active;
    @Column(name = "created_at")
    private Instant created_at;
    @Column(name = "updated_at")
    private Instant updated_at;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "properties")
    private Set<Schedules> schedules;
}
