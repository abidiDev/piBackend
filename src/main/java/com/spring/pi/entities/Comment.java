package com.spring.pi.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
@NoArgsConstructor
@Entity
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
    @JsonBackReference

    @JoinColumn(name = "ads_id")
    private Ads ads;
}