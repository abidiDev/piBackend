 package com.spring.pi.services;

import com.spring.pi.entities.*;
import com.spring.pi.payload.request.RealestateRequest;
import com.spring.pi.payload.response.RealestateRespone;
import com.spring.pi.payload.response.SocialServiceResponce;
import com.spring.pi.repositories.LocalisationRepository;
import com.spring.pi.repositories.Real_EstateRepository;
import com.spring.pi.repositories.Social_ServiceRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class ServiceSocialSer implements IServiceSocialSer {
    Social_ServiceRepository socialServiceRepository;
    Real_EstateRepository realEstateRepository;
    LocalisationRepository localisationRepository;

    @Override
    public List<Social_Service> getServiceByTypeSS(TypeSS typess) {
        return socialServiceRepository.findSocial_ServiceByTypess(typess);
    }

    @Override
    @Transactional
    public SocialServiceResponce addServiceAndAsignToRealestate(RealestateRequest realestateRequest, long idRealEstate) {

       Real_Estate realEstate = realEstateRepository.findById(idRealEstate).orElse(null);
        for (Social_Service s : realestateRequest.getSocialService()) {
            socialServiceRepository.save(s);
            // asgining service to realestate
            Localisation l = new Localisation();
            localisationRepository.save(l);
            s.setLocalisation(l);
            socialServiceRepository.save(s);
            for (String shared : realestateRequest.getSharedLocation()) {
                switch (shared) {
                    case "city":
                        l.setCity(realEstate.getLocalisation().getCity());
                        break;
                    case "state":
                        l.setState(realEstate.getLocalisation().getState());
                        break;
                    case "distrit":
                        l.setDistrict(realEstate.getLocalisation().getDistrict());
                        break;
                    case "street":
                        l.setStreet(realEstate.getLocalisation().getStreet());

                        break;
                }
            }
            socialServiceRepository.save(s);
        }
        SocialServiceResponce sr = new SocialServiceResponce();
        sr.setRealEstate(realEstate);
        sr.setSocialService(socialServiceRepository.findSocial_ServiceByLocalisationState(realEstate.getLocalisation().getState()));
        return sr;

    }
}

   /* @Override
    public Social_Service getServiceByRealestate(long idService, long idRealestate) {
        Real_Estate realEstate =realEstateRepository.findById(idRealestate).orElse(null);
return null;
    }



    @Override
    public Social_Service addServiceByLocalisation(RealestateRequest realestateRequest, long idRealestate) {
        Social_Service socialService= socialServiceRepository.save(realestateRequest.getSocialService());
        Localisation localisation= localisationRepository.save(realestateRequest.getLocalisation());
        socialService.setLocalisation(localisation);
        socialServiceRepository.save(socialService);
        Real_Estate realEstate=realEstateRepository.findById(idRealestate).orElse(null);
        socialService.getRealEstates().add(realEstate);


        return socialServiceRepository.save(socialService);


    }


}*/
