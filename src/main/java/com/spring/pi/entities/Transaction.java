package com.spring.pi.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "transaction")
public class Transaction implements Serializable {
    @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
   private Long id;
   // @Temporal(TemporalType.DATE)
   // private Date dateTrans;


    @ManyToOne
    private Real_Estate real_Estate;

    @ManyToOne

    private Contract contract;


}