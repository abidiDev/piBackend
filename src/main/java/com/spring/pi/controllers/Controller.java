package com.spring.pi.controllers;

import com.spring.pi.entities.*;
import com.spring.pi.repositories.*;
import com.spring.pi.services.IService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@AllArgsConstructor
@RestController
@RequestMapping("/CRUD")

public class Controller {
    IService iService;
    ////////////////////////ads//////////////////////////////////
    @GetMapping("/AllAds")
    @ResponseBody
    public List<Ads> getAllAds(){
        return iService.getAllAds();
    }
    @PostMapping("/addAds")
    @ResponseBody
    public Ads addAds(@RequestBody Ads ads){
        return iService.addAds(ads);
    }
    @PutMapping("/updateAds")
    @ResponseBody
    public Ads updateAds(@RequestBody Ads ads){
        return iService.updateAds(ads);
    }


    @DeleteMapping("/deleteAds/{id}")
    @ResponseBody
    public void deleteAds(@PathVariable("id")int id){
        iService.deleteAds(id);
    }

    @GetMapping("/getAdsById/{id}")
    @ResponseBody
    public Ads getAdsById(@PathVariable("id")int id){
        return iService.getAdsById(id);
    }


    //////////////////////rating////////////////////////////

    @GetMapping("/getAllRating")
    @ResponseBody
    public List<Rating> getAllRating(){
        return iService.getAllRating();
    }
    @PostMapping("/addRating")
    @ResponseBody
    public Rating addRating(@RequestBody Rating rating){
        return iService.addRating(rating);
    }
    @PutMapping("/updateRating")
    @ResponseBody
    public Rating updateRating(@RequestBody Rating rating){
        return iService.updateRating(rating);
    }


    @DeleteMapping("/deleteRating/{id}")
    @ResponseBody
    public void deleteRating(@PathVariable("id")long id){
        iService.deleteRating(id);
    }

    @GetMapping("/getRatingById/{id}")
    @ResponseBody
    public Rating getRatingById(@PathVariable("id")long id){
        return iService.getRatingById(id);
    }
    ////////////////////contract/////////////////////////
    @GetMapping("/getAllContract")
    @ResponseBody
    public List<Contract> getAllContract(){  return iService.getAllContract();}
    @GetMapping("/getContractById/{id}")
    @ResponseBody
    public Contract getContractById(@PathVariable long id){return iService.getContractById(id);}
    @PostMapping("/addContract")
    @ResponseBody
    public Contract addContract( @RequestBody Contract contract){ return iService.addContract(contract);}
    @PutMapping("/updateContract")
    @ResponseBody
    public Contract updateContract(@RequestBody  Contract contract){return iService.updateContract(contract);}
    @DeleteMapping("/deleteContract/{id}")
    @ResponseBody
    public void deleteContract(long id){iService.deleteContract(id);}
    ///////////////////localisation//////////////////////////////
    @PostMapping("ajouterlocalisation")
    @ResponseBody
    public Localisation ajouterlocatisation(@RequestBody Localisation l){
        return iService.addLoca(l);
    }
    @DeleteMapping("deletelocatitation/{id}")
    @ResponseBody
    public String Deletelocalisation(@PathVariable("id") Long id){
        return iService.deletelocalisation(id);
    }
    @GetMapping("findlocalisation/{latitudes}/{longitudes}")
    @ResponseBody
    public Localisation findlocalisation(@PathVariable("latitudes") float latitudes, @PathVariable("longitudes")float longitudes){
        return iService.findlocation(latitudes,longitudes);
    }
    ///////////service///////////////////
    @PostMapping("addSocialService")
    @ResponseBody
    public Social_Service addSocialService(@RequestBody Social_Service socialService){
        return iService.addSoc(socialService);
    }
    @DeleteMapping("deleteSocial/{id}")
    @ResponseBody
    public int deleteSocial(Long id){
        return iService.deleteSocial(id);
    }
    /////////////////////message////////////
    @GetMapping("/AllMessages")
    @ResponseBody
    public List<Generic_Message> getGnericMessage(){
        return iService.getGnericMessage();
    }

    @PostMapping("/addmessages")
    @ResponseBody
    public Generic_Message addgenericmessage(@RequestBody Generic_Message Generic_Message){
        return iService.addgenericmessage(Generic_Message);
    }


    @PutMapping("/updategeneratemessages")
    @ResponseBody
    public Generic_Message updategenericmessage(@RequestBody Generic_Message Generic_Message){
        return iService.updategenericmessage(Generic_Message);
    }


    @DeleteMapping("/deletegenericmessages/{id}")
    @ResponseBody
    public void Deletegenericmessage(@PathVariable("id")Long id){
        iService.Deletegenericmessage(id);
    }
    /////////////////realstate////////////////////
    @GetMapping("/getAllReal_Estate")
    @ResponseBody
    public List<Real_Estate> getAllReal_Estate(){ return iService.getAllReal_Estate();}
    @GetMapping("/getReal_EstateById/{id}")
    @ResponseBody
    public Real_Estate getReal_EstateById(@PathVariable long id){return iService.getReal_EstateById(id);}


    @PostMapping("/addRealEstate")
    @ResponseBody
    public House addReal_Estate(@RequestBody House house){return iService.addReal_Estate(house);}
    @PutMapping("/updateRealEstate")
    @ResponseBody
    public House updateReal_Estate(@RequestBody House house){return iService.updateReal_Estate(house);}
    @DeleteMapping("/deleteRealEstate/{id}")
    @ResponseBody
    public void deleteReal_Estate(@PathVariable long id){iService.deleteReal_Estate(id);}
    ///////////////////////agency////////////
    @GetMapping("/getAllAgency")
    @ResponseBody
    public List<Agency> getAllAgency(){return iService.getAllAgency();}
    @GetMapping("/getAgencyById/{id}")
    @ResponseBody
    public Agency getAgencyById(long id){return iService.getAgencyById(id);}
    @PostMapping("/addAgency")
    @ResponseBody
    public Agency addAgency(@RequestBody Agency agency){return iService.addAgency(agency);}
    @PutMapping("/updateAgency")
    @ResponseBody

    public Agency updateAgency(@RequestBody Agency agency){return iService.updateAgency(agency);}
    @DeleteMapping("/deleteAgency/{id}")
    @ResponseBody
    public void deleteAgency(@PathVariable  long id){iService.deleteAgency(id);}
////////////////////actor///////////
@GetMapping("/getAllActor")
@ResponseBody
    public List<Actor> getAllActor(){return iService.getAllActor();}
    @GetMapping("/getActorById/{id}")
    @ResponseBody
    public Actor getActorById(@PathVariable long id){return iService.getActorById(id);}
    @PostMapping("/addActor")
    @ResponseBody
    public Actor addActor(@RequestBody Actor actor){return iService.addActor(actor);}
    @PutMapping("/updateActor")
    @ResponseBody
    public Actor updateActor(@RequestBody Actor actor){return iService.updateActor(actor);}
    @DeleteMapping("/deleteActor/{id}")
    @ResponseBody
    public void deleteActor(@PathVariable long id){iService.deleteActor(id);}
    //////////////////conversation////////////////////////
    @GetMapping("/getAllConversation")
    public List<Conversation> getAllCoonversations() {
        return iService.getAllConversations();
    }
    @PostMapping("/addConversation")
    @ResponseBody
    public Conversation addConversation(@RequestBody Conversation c) {
        return iService.addConversation(c);
    }


    @PutMapping("/updateConversation")
    @ResponseBody
    public Conversation updateConversation(@RequestBody Conversation c) {
        return iService.updateConversation(c);
    }


    @DeleteMapping("/deleteConversation")
    public void deleteConversation(@PathVariable("id") long id) {
        iService.deleteConversation(id);
    }


    @GetMapping("/getConversationById/{id}")
    public Conversation getConversationById(@PathVariable("id") long id) {


        return iService.getConversationById(id);
    }


}



