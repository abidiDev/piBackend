package com.spring.pi.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;


@NoArgsConstructor
@Entity
@AllArgsConstructor
@Getter
@Setter
@Table(name = "Reactions")
public class Reactions implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int idreaction;
    @Enumerated(EnumType.STRING)
    private TypeReaction typeReaction;
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "forumpub_id")
    private ForumPublication forumpublication;

    @ManyToOne
    @JsonIgnore
    private  Actor user;

}