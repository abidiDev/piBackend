package com.spring.pi.entities;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "ads")
public class Ads implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String description;
    private String picture ;


    @OneToMany(mappedBy = "ads", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Rating> ratings = new LinkedHashSet<>();

    @Enumerated(EnumType.STRING)
    private Type type ;


    @ManyToOne(cascade = CascadeType.ALL)
    private Actor actor;


    @ManyToOne(cascade = CascadeType.ALL)

    private Real_Estate real_Estate;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Generic_Message> generic_Messages = new LinkedHashSet<>();


    @OneToMany(mappedBy = "ads", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Rating> rating = new LinkedHashSet<>();


}