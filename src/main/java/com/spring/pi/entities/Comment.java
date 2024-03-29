package com.spring.pi.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
@Entity
@NoArgsConstructor

@AllArgsConstructor
@Getter
@Setter
@Table(name = "Comment")
public class Comment implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long idcomm;
    private String content;
    private LocalDate createdAt;


    @ManyToOne(cascade = CascadeType.ALL)
    @JsonBackReference(value="test3")
    private Ads ads;


    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    Actor user;
}
