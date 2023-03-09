package com.spring.pi.entities;

import aj.org.objectweb.asm.ConstantDynamic;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class HouseBuilding implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    private Long surfaceterrain;
    private Long houseSurface;
    private Integer nbrofroom ;
    private Boolean piscine ;
    private Boolean jardin;
    private Integer nbreEtage;
    private String lieux;

    private Long prix;

    @ManyToOne
    @JoinColumn(name = "IDAgnce")
    private ConstructionAgency constructionAgency;



}