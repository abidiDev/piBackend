package com.spring.pi.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "localisation")
public class Localisation implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    private String city;
    private String district;
    private String street;
    private long number;
    private float latitude ;
    private float longitude;


    @OneToOne(mappedBy = "localisation", cascade = CascadeType.ALL)
    private Real_Estate real_Estate;


    @OneToOne(mappedBy = "localisation", cascade = CascadeType.ALL)
    @JsonBackReference
    private Social_Service social_Service;

}