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
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="TYPE", length = 15)
@Entity
@Table(name = "real_estate")
public abstract class Real_Estate  implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    private float surface;
    private float price;


    @OneToOne(mappedBy = "real_Estate", cascade = CascadeType.ALL, orphanRemoval = true)
    private Localisation localisation;


    @OneToMany(mappedBy = "real_Estate", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Transaction> transactions = new LinkedHashSet<>();


}