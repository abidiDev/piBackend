package com.spring.pi.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.List;
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
    @JsonFormat(pattern="yyyy-MM-dd")

    private LocalDate BeginDate;
    @JsonFormat(pattern="yyyy-MM-dd")

    private  LocalDate EndDate;

    private double revenueM;



    // @OneToMany(mappedBy = "contract", cascade = CascadeType.ALL, orphanRemoval = true)
    //private Set<Actor_Contract> actor_Contrats = new LinkedHashSet<>();
    @OneToMany
    @JoinTable(name = "actor_contrat",
            joinColumns = {@JoinColumn(name = "contract_id")},
            inverseJoinColumns = {@JoinColumn(name = "actor_id")}
    )
    private List<Actor> actors;

    //@OneToMany(mappedBy = "contract", cascade = CascadeType.ALL, orphanRemoval = true)
    //private Set<Transaction> transactions = new LinkedHashSet<>();

    @OneToMany
    @JoinTable(name = "transaction",
            joinColumns = {@JoinColumn(name = "contract_id")},
            inverseJoinColumns = {@JoinColumn(name = "real_estate_id")}
    )
    private List<Transaction> transactions;


}