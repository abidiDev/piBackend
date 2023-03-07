package com.spring.pi.payload.request;

import com.spring.pi.entities.Real_Estate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OccsOfRealEastateRequest implements Serializable {
    int occs;
    List< ? extends Real_Estate> Re= new ArrayList<Real_Estate>();;

}
