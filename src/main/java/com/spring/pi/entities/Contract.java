package com.spring.pi.entities;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "contrat")
public class Contract implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    private Boolean valid;
    private LocalDate begin_Date;

    private LocalDate createdDate;
    private  LocalDate end_Date;
    private float price_Cont;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<Actor_Contract> actor_Contrats = new LinkedHashSet<>();


    @OneToMany( cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Transaction> transactions = new LinkedHashSet<>();


}