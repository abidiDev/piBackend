package com.spring.pi.services;

import com.spring.pi.entities.*;
import com.spring.pi.repositories.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
@Service
@AllArgsConstructor
public class ServiceImp implements IService{
    AdsRepository adsRepository;
    RatingRepository ratingRepository;
    ContractRepository contractRepository;
    LocalisationRepository localisationRepository;
    Social_ServiceRepository socialServiceRepository;
    Generic_MessageRepository genericMessageRepository;
    Real_EstateRepository realEstateRepository;
    AgencyRepository agencyRepository;
    ActorRepository actorRepository;
    ConversationRepository conversationRepository;



    //////////////ads//////////////////
    @Override
    public List<Ads> getAllAds() {
         return adsRepository.findAll();
    }

    @Override
    public Ads getAdsById(long id) {
        return adsRepository.findById( id).orElse(null);
    }

    @Override
    public Ads addAds(Ads ads) {
        return  adsRepository.save(ads) ;}


    @Override
    public Ads updateAds(Ads ads) {
         return adsRepository.save(ads);}



    @Override
    public void deleteAds(long id) {
        adsRepository.deleteById(id);}

//////////////////////////rating////////////////////////////////////////////////////
    @Override
    public List<Rating> getAllRating() {
        return ratingRepository.findAll();

    }

    @Override
    public Rating getRatingById(long id) {
        return ratingRepository.findById(id).orElse(null) ;
    }

    @Override
    public Rating addRating(Rating rating) {
        return ratingRepository.save(rating);
    }

    @Override
    public Rating updateRating(Rating rating) {
        return ratingRepository.save(rating);
    }

    @Override
    public void deleteRating(long id) {
        ratingRepository.deleteById(id);

    }
/////////////contract/////////////////
    @Override
    public List<Contract> getAllContract() {
        return contractRepository.findAll();
    }

    @Override
    public Contract getContractById(long id) {
        return contractRepository.findById(id).orElse(null);
    }

    @Override
    public Contract addContract(Contract contract) {
        return contractRepository.save(contract);
    }

    @Override
    public Contract updateContract(Contract contract) {
        return contractRepository.save(contract);
    }

    @Override
    public void deleteContract(long id) {
        contractRepository.deleteById(id);

    }
    /////////////localisation/////////////////////
    @Override
    public Localisation addLoca(Localisation localisation) {

        return localisationRepository.save(localisation);
    }

    @Override
    public String deletelocalisation(Long id) {

        localisationRepository.deleteById(id);
        return "localisation supprimée avec succes";
    }

    @Override
    public Localisation findlocation(float latitude, float longitudes) {
        return localisationRepository.findByLatitudeAndLongitude(latitude,longitudes);
    }
////////////service////////////////////////////
    @Override
    public Social_Service addSoc(Social_Service socialService) {
        return socialServiceRepository.save(socialService);
    }

    @Override
    public int deleteSocial(Long id) {
        socialServiceRepository.deleteById(id);
        return 0;
    }
//////////////////message/////////////////////

    @Override
    public Generic_Message addgenericmessage(Generic_Message generic_message) {
        return genericMessageRepository.save(generic_message);
    }

    @Override
    public void Deletegenericmessage(Long id) {
        genericMessageRepository.deleteAllById(Collections.singleton(id));


    }

    @Override
    public List<Generic_Message> getGnericMessage() {
        return genericMessageRepository.findAll();
    }

    @Override
    public Generic_Message updategenericmessage(Generic_Message genericMessage) {
        return genericMessageRepository.save(genericMessage);
    }
    ////////realestate////////////////////

    @Override
    public List<Real_Estate> getAllReal_Estate() {
        return realEstateRepository.findAll();
    }

    @Override
    public Real_Estate getReal_EstateById(long id) {
        return realEstateRepository.findById(id).orElse(null);
    }

    @Override
    public House addReal_Estate(House house) {
        return realEstateRepository.save(house);
    }

    @Override
    public House updateReal_Estate(House house) {
        return realEstateRepository.save(house);

    }

    @Override
    public void deleteReal_Estate(long id) {
        realEstateRepository.deleteById(id);

    }
    ///////////agency/////////////////////////////

    @Override
    public List<Agency> getAllAgency() {
        return agencyRepository.findAll();
    }

    @Override
    public Agency getAgencyById(long id) {
        return agencyRepository.findById(id).orElse(null);
    }

    @Override
    public Agency addAgency(Agency agency) {
        return agencyRepository.save(agency);
    }

    @Override
    public Agency updateAgency(Agency agency) {
        return agencyRepository.save(agency);

    }

    @Override
    public void deleteAgency(long id) {
         agencyRepository.deleteById(id);

    }
    //////////////actor//////////////

    @Override
    public List<Actor> getAllActor() {
        return actorRepository.findAll();
    }

    @Override
    public Actor getActorById(long id) {
        return actorRepository.findById(id).orElse(null);
    }

    @Override
    public Actor addActor(Actor actor) {
        return actorRepository.save(actor);
    }

    @Override
    public Actor updateActor(Actor actor) {
        return actorRepository.save(actor);
    }

    @Override
    public void deleteActor(long id) {
         actorRepository.deleteById(id);

    }
    ///////////////////conversation///////////////////


    @Override
    public List<Conversation> getAllConversations() {
        return conversationRepository.findAll();}


    @Override
    public Conversation addConversation(Conversation c) {
        return conversationRepository.save(c);
    }


    @Override
    public Conversation updateConversation(Conversation c) {
        return conversationRepository.save(c);
    }


    @Override
    public void deleteConversation(long id) {
        conversationRepository.deleteById(id);


    }


    @Override
    public Conversation getConversationById(long id) {
        return conversationRepository.findById(id).orElse(null);
    }

}







