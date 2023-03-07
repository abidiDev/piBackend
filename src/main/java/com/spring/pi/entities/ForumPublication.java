package com.spring.pi.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Table(name = "ForumPublication")
public class ForumPublication implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long idforum;
    private String object;
    private String topic;
    private LocalDate date_pub;

    // @JsonManagedReference
    @OneToMany(mappedBy = "forum")

    Set<Comment> comments;

    @OneToMany(mappedBy = "forumPublication")
    @JsonIgnore

    // @JsonManagedReference
    Set<Reactions> reactions;

    @ManyToOne
    @JsonIgnore
    Actor usersf;
}
