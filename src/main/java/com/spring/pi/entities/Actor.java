package com.spring.pi.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

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
    private Boolean connected;
    private String Badge;
    private boolean blocked=false;
    @Column(name = "reset_password_token")
    private String resetPasswordToken;

    public Actor(String username, String email, String password) {
        this.username=username;
        this.email=email;
        this.password=password;
    }



    @JsonBackReference
    @ManyToMany(mappedBy = "actors", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH})
    private Set<Conversation> conversations = new LinkedHashSet<>();
    @JsonBackReference
    @OneToMany(mappedBy = "actor", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Generic_Message> generic_Messages = new LinkedHashSet<>();

    @ManyToOne(cascade = CascadeType.ALL)
    private Agency agency;


    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Actor_Contract> actor_Contrats = new LinkedHashSet<>();

    @ManyToMany(fetch = FetchType.LAZY)

    private Set<Role> roles = new HashSet<>();

    @OneToMany()
    private Set<ActorAdsFav> actorAdsFavs = new LinkedHashSet<>();

    @OneToMany
    private List<Ads> adses = new ArrayList<>();





    @OneToMany(mappedBy = "utilisateur")
    @JsonIgnore
    Set<Notification> notifs;

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    Set<Reactions> reacts;

    @OneToMany()
    private List<Actor_construction> actorConstructionList;

}