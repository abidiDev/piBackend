package com.spring.pi.services;

import com.spring.pi.entities.*;

import java.util.List;

public interface IService {
    ////////////ads////////////////
    public List<Ads> getAllAds();
    public Ads getAdsById(long id);
    public Ads addAds(Ads ads);
    public Ads updateAds(Ads ads);
    public void deleteAds(long id);
    //////////////rating//////////////
    public List<Rating> getAllRating();
    public Rating getRatingById(long id);
    public Rating addRating(Rating rating);
    public Rating updateRating(Rating rating);
    public void deleteRating(long id);
    /////////////////////contract//////////////////////////////
    public List<Contract> getAllContract();
    public Contract getContractById(long id);
    public Contract addContract(Contract contract);
    public Contract updateContract(Contract contract);
    public void deleteContract(long id);
    /////////////////localisation////////////////
    public Localisation addLoca(Localisation localisation);
    public String deletelocalisation(Long id);
    public Localisation findlocation(float latitude,float longitude);
    ////////////////////service///////////
    public Social_Service addSoc(Social_Service socialService);
    public  int deleteSocial(Long id);
    //////////////message ////////////////////////////
    public Generic_Message addgenericmessage(Generic_Message generic_message);
    void Deletegenericmessage (Long id);

    List<Generic_Message> getGnericMessage ();
    public Generic_Message updategenericmessage(Generic_Message genericMessage);
    ///////////realstate/////////////
    public List<Real_Estate> getAllReal_Estate();
    public Real_Estate getReal_EstateById(long id);
    public House addReal_Estate(House house);
    public House updateReal_Estate(House house);
    public void deleteReal_Estate(long id);
    /////////////agency/////////////////////////////
    public List<Agency> getAllAgency();
    public Agency getAgencyById(long id);
    public Agency addAgency(Agency agency);

    public Agency updateAgency(Agency agency);
    public void deleteAgency(long id);
    ////////////actor//////////////
    public List<Actor> getAllActor();
    public Actor getActorById(long id);
    public Actor addActor(Actor actor);

    public Actor updateActor(Actor actor);
    public void deleteActor(long id);


    ///////////////conversation/////////////////////////



    public List<Conversation> getAllConversations();
    public Conversation addConversation(Conversation c);
    public Conversation updateConversation(Conversation c);
    public void deleteConversation(long id);
    public Conversation getConversationById(long id);



}
