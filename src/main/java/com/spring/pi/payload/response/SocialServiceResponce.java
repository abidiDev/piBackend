package com.spring.pi.payload.response;

import com.spring.pi.entities.Real_Estate;
import com.spring.pi.entities.Social_Service;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SocialServiceResponce {
    private Real_Estate realEstate;
    private List<Social_Service>  socialService;
}
