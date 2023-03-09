package com.spring.pi.services;

import com.spring.pi.entities.*;
import com.spring.pi.payload.request.RealestateRequest;
import com.spring.pi.payload.response.RealestateRespone;
import com.spring.pi.repositories.*;
import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
@AllArgsConstructor

public class ServiceRealestate implements IServiceRealestate {
    Real_EstateRepository realEstateRepository;
    LocalisationRepository localisationRepository;
    AdsRepository adsRepository;
    Social_ServiceRepository socialServiceRepository;
IService iService;

    @Override
    public List<Real_Estate> getRealestateByLocationCity(String city) {
        return realEstateRepository.findReal_EstateByLocalisation_City(city);
    }

    @Override
    @Transactional
    public RealestateRespone AddRealestateAdvanced(RealestateRequest realestateRequest) {
        //add realestate , location , ads and services
        Real_Estate realEstate= iService.addReal_Estate(realestateRequest);

        Localisation localisation = localisationRepository.save(realestateRequest.getLocalisation());
        Ads ads = adsRepository.save(realestateRequest.getAds());
        //asigning location to realestate
        realEstate.setLocalisation(localisation);
        realEstateRepository.save(realEstate);
        //asgin ads to reralestate
        ads.setReal_Estate(realEstate);
        adsRepository.save(ads);
        realEstateRepository.save(realEstate);
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
        RealestateRespone rs = new RealestateRespone();
        rs.setType(realestateRequest.getType());
        rs.setRealEstate(realEstate);
        rs.setLocalisation(localisation);
        rs.setSocialService(socialServiceRepository.findSocial_ServiceByLocalisationState(realEstate.getLocalisation().getState()));
        return rs;
    }

    @Override
    public List<Real_Estate> getRealestateByPrice(float priceMin, float priceMax) {
        List<Real_Estate> allRealEstates = realEstateRepository.findAll();
        List<Real_Estate> realEstateInRange = new ArrayList<>();
        for (Real_Estate realEstate : allRealEstates) {
            if (realEstate.getPrice() >= priceMin && realEstate.getPrice() <= priceMax) {
                realEstateInRange.add(realEstate);
            }
        }
        return realEstateInRange;
    }

    @Override
    public Real_Estate getRealEstateById(Long id) {
        return realEstateRepository.findById(id).orElse(null);
    }
    @Override
    public String calculatePaymentPlanById(Long idRealestate) {

            Optional<Real_Estate> optionalRealEstate = realEstateRepository.findById(idRealestate);


            Real_Estate realEstate = optionalRealEstate.get();
            double price = realEstate.getPrice();

            double fullPrice = price;
            String fullPaymentMessage =
                    "Payment in full without interest. Total payment: "
                    + String.format("%.2f", fullPrice) + " Dt.";

            double installmentPrice3 = price * 1.02;
            double installmentAmount3 = installmentPrice3 / 3;
            String installmentMessage3 =
                    "Payment in 3 months installments with 2% interest. Total payment: "
                    + String.format("%.2f", installmentPrice3) + " Dt. Payment plan: 3 installments of "
                    + String.format("%.2f", installmentAmount3) + " Dt each.";

            double installmentPrice6 = price * 1.05;
            double installmentAmount6 = installmentPrice6 / 6;
            String installmentMessage6 =
                    "Payment in 6 months installments with 5% interest. Total payment: "
                    + String.format("%.2f", installmentPrice6)
                    + " Dt. Payment plan: 6 installments of "
                    + String.format("%.2f", installmentAmount6) + " Dt each.";

            double installmentPrice9 = price * 1.07;
            double installmentAmount9 = installmentPrice9 / 9;
            String installmentMessage9 =
                    "Payment in 9 months installments with 7% interest. Total payment: "
                    + String.format("%.2f", installmentPrice9) + " Dt. Payment plan: 9 installments of "
                    + String.format("%.2f", installmentAmount9) + " Dt each.";

            double installmentPrice12 = price * 1.1;
            double installmentAmount12 = installmentPrice12 / 12;
            String installmentMessage12 =
                    "Payment in 12 months installments with 10% interest. Total payment: "
                    + String.format("%.2f", installmentPrice12) + " Dt. Payment plan: 12 installments of "
                    + String.format("%.2f", installmentAmount12) + " Dt each.";

            String result = fullPaymentMessage + "\n "
                    + installmentMessage3 + "\n "
                    + installmentMessage6 + "\n "
                    + installmentMessage9 + "\n "
                    + installmentMessage12;

            return result;
        }

}




