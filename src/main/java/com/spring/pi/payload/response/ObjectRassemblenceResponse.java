package com.spring.pi.payload.response;

import com.spring.pi.entities.Real_Estate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@AllArgsConstructor
@Getter
@Setter
public class ObjectRassemblenceResponse implements Serializable {
    Integer resseblance;
    Real_Estate object;
}
