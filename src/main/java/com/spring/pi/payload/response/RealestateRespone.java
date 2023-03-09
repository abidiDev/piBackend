package com.spring.pi.payload.response;

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
@NoArgsConstructor

public class RealestateRespone {
    private String type;
    private Real_Estate realEstate;
    private Localisation localisation;
    private List<Social_Service> socialService;

}
