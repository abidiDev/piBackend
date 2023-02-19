package com.spring.pi.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "actor")
public class Actor implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    private String FullName;
    @Temporal(TemporalType.DATE)
    private Date Birthdate;
    private  int phone;
    private String gender;
    private String email;
    private String username;

    private String password;
    private String picture;
    private String address;
    @Enumerated(EnumType.STRING)

    private ERole role;

    public Actor(String username, String email, String password) {
        this.username=username;
        this.email=email;
        this.password=password;
    }
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private Actor second_actor;


    @JsonBackReference
    @ManyToMany(mappedBy = "actors", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH})
    private Set<Conversation> conversations = new LinkedHashSet<>();
    @JsonBackReference
    @OneToMany(mappedBy = "actor", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Generic_Message> generic_Messages = new LinkedHashSet<>();

    @ManyToOne(cascade = CascadeType.ALL)
    private Agency agency;


    @OneToMany(mappedBy = "actor", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Actor_Contract> actor_Contrats = new LinkedHashSet<>();

    @ManyToMany(fetch = FetchType.LAZY)

    private Set<Role> roles = new HashSet<>();

}