package com.spring.pi.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "construction_agency")
public class ConstructionAgency {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    private String name;
    private String lieux_Agence;
    private String email;
    private Integer telephone;

    @OneToMany(mappedBy = "constructionAgency")
    @JsonIgnore
    private List<HouseBuilding> buildinghouses;

    public List<HouseBuilding> getBuildinghouses() {
        return buildinghouses;
    }

    public void setBuildinghouses(List<HouseBuilding> buildinghouses) {
        this.buildinghouses = buildinghouses;
    }
}