package com.spring.pi.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
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
    private double price;

    @OneToOne( cascade = CascadeType.ALL, orphanRemoval = true)
    private Localisation localisation;

    @OneToMany(mappedBy = "real_Estate", cascade = CascadeType.ALL)
    @JsonManagedReference(value="test1")
    private List<Ads> adses = new ArrayList<>();



}