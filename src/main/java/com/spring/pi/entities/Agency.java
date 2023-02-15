package com.spring.pi.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "agence")
public class Agency implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    private String name;


    @OneToMany(mappedBy = "agency", orphanRemoval = true)
    private Set<Actor> actors = new LinkedHashSet<>();


}