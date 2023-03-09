package com.spring.pi.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;

@NoArgsConstructor
@Entity
@AllArgsConstructor
@Getter
@Setter
public class ForumPublication implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long idforum;
    private String object;
    private String topic;
    private LocalDate date_pub;

    // @JsonManagedReference
    @OneToMany()

    Set<Comment> comments;

    @OneToMany(mappedBy = "forumpublication")
    @JsonIgnore

    // @JsonManagedReference
    Set<Reactions> reactions;

    @ManyToOne
    @JsonIgnore
    Actor usersf;

}
