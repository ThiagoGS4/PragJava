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
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name =  "roles")

public class Roles implements Serializable{
       
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "name")
    private String name;

    @ManyToMany(mappedBy = "roles")
    @JsonBackReference // evitando loop infinito (child)
    private Collection<Users> userList;

    //@ManyToMany
    //@JoinTable(
    //    name = "roles_privileges", 
    //    joinColumns = @JoinColumn(
    //      name = "role_id", referencedColumnName = "id"), 
    //    inverseJoinColumns = @JoinColumn(
    //      name = "privilege_id", referencedColumnName = "id"))
    //private Collection<Privilege> privileges;
    // todo: fazer roles funcionar e fazer error handling melhor para melhor informar o usuário

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "roles_permissions",
            joinColumns = @JoinColumn(name = "role_id"),
            inverseJoinColumns = @JoinColumn(name = "permission_id"))
    private Set<Permission> permissions = new HashSet<>();
}