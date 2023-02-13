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
@Table(name = "building")
@DiscriminatorValue("building")

public class Building  extends Real_Estate implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    private int  nbr_appartment;


    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Appartment> appartments = new LinkedHashSet<>();


}