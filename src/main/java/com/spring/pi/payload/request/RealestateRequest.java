package com.spring.pi.payload.request;

import com.spring.pi.entities.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.util.List;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
public class RealestateRequest {
    String type;
    House house;
    Parking parking;
    Office_Center officeCenter;
    Ground ground;
    Building building;
    Commercial_Property commercialProperty;
    Localisation localisation;
    Ads ads;
    List<Social_Service> socialService;
 Set<String> SharedLocation ;
}
