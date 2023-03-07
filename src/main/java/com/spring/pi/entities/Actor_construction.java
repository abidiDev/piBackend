package com.spring.pi.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "actor_construction")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Actor_construction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long montantRestant;
    @Temporal(TemporalType.DATE)
    private Date dateLimit;
    @JsonIgnore
    private Integer date;
    @Temporal(TemporalType.DATE)
    private Date DateDebut;
    private Long prixC;
    private Float mensionalite;
    private Integer nbredetranche;
    @ManyToOne
    @JoinColumn(name = "construction_agency_id")
    private ConstructionAgency constructionAgency;
}