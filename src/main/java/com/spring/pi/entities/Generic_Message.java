package com.spring.pi.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
@Table(name = "generic_message")
public class Generic_Message  implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

   private String content;
    @Enumerated(EnumType.STRING)

    private Specification specification ;
    @Temporal(TemporalType.DATE)
    private Date date;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ads_id")
    private Ads ads;

    @ManyToOne
    @JoinColumn(name = "actor_id")
    //@JsonBackReference
    @JsonManagedReference
    private Actor actor;

    public Ads getAds() {
        return ads;
    }

    public void setAds(Ads ads) {
        this.ads = ads;
    }
}