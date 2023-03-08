package com.spring.pi.services;

import com.spring.pi.entities.*;
import com.spring.pi.payload.request.OccsOfRealEastateRequest;
import com.spring.pi.payload.response.ObjectRassemblenceResponse;
import com.spring.pi.repositories.ActorAdsFavRepository;
import com.spring.pi.repositories.ActorRepository;
import com.spring.pi.repositories.AdsRepository;
import com.spring.pi.repositories.Real_EstateRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@AllArgsConstructor
public class UserServiceImpl implements IuserService{
    ActorRepository actorRepository;
    AdsRepository adsRepository;
    ActorAdsFavRepository actorAdsFavRepository;
    private final Real_EstateRepository real_EstateRepository;

    @Override
    public void addFavToAdsAndAssignToActor(Long actorId, Long adsId) {
        Actor a= actorRepository.findById(actorId).orElse(null);
        Ads ads=adsRepository.findById(adsId).orElse(null);
        ActorAdsFav afa=new ActorAdsFav();
        afa.setFavorite(Boolean.TRUE);
        actorAdsFavRepository.save(afa);
        //asign actor to favorite
        a.getActorAdsFavs().add(afa);
        actorRepository.save(a);
        //assign ads to favorite
        afa.setAds(ads);
        actorAdsFavRepository.save(afa);
    }

    @Override
    public List<OccsOfRealEastateRequest>  getPertinentAds(Long actorId) {

        Actor a= actorRepository.findById(actorId).orElse(null);

        //getting the favorite Ads Of user
        List<Ads> favAds=new ArrayList<>();

            for (ActorAdsFav actorAdsFav:a.getActorAdsFavs()) {
                favAds.add(actorAdsFav.getAds());}

        //getting RealEastate of ads
        List<House> favH=new ArrayList<>();
        List<Parking> favP=new ArrayList<>();
        List<Ground> favG=new ArrayList<>();
        List<Commercial_Property> favC=new ArrayList<>();
        List<Office_Center> favO=new ArrayList<>();
        List<Building> favB=new ArrayList<>();

            for (Ads ads:favAds) {
                if (ads.getReal_Estate() instanceof House) {

                    favH.add((House) ads.getReal_Estate());
                }
                if (ads.getReal_Estate() instanceof Parking) {

                    favP.add((Parking) ads.getReal_Estate());
                }
                if (ads.getReal_Estate() instanceof Ground) {

                    favG.add((Ground) ads.getReal_Estate());
                }
                if (ads.getReal_Estate() instanceof Commercial_Property) {

                    favC.add((Commercial_Property) ads.getReal_Estate());
                }
                if (ads.getReal_Estate() instanceof Office_Center) {

                    favO.add((Office_Center) ads.getReal_Estate());
                }
                if (ads.getReal_Estate() instanceof Building) {

                    favB.add((Building) ads.getReal_Estate());
                }
                }
        //Occurence of each type of ads
        List<OccsOfRealEastateRequest> Ore=new ArrayList<>();
        Ore.add(new OccsOfRealEastateRequest(favH.size(), real_EstateRepository.HousesList()));
        Ore.add(new OccsOfRealEastateRequest(favP.size(), real_EstateRepository.ParkingsList()));
        Ore.add(new OccsOfRealEastateRequest(favG.size(), real_EstateRepository.GroundsList()));
        Ore.add(new OccsOfRealEastateRequest(favC.size(), real_EstateRepository.CommercialPList()));
        Ore.add(new OccsOfRealEastateRequest(favO.size(), real_EstateRepository.OfficesCList()));
        Ore.add(new OccsOfRealEastateRequest(favB.size(), real_EstateRepository.BuildingsList()));








//chercher parmi la liste des favories les criteres les plus recherchées(selon type +les cryteres de type)
        List<Float> surfaces =new ArrayList<>();
        List<Float> prices =new ArrayList<>();
        List<Integer> nbrFloor =new ArrayList<>();
        List<Integer> nbrRooms =new ArrayList<>();
        List<Float> gardens =new ArrayList<>();
        List<Float> pools =new ArrayList<>();

        for (House h:favH) {
            surfaces.add(h.getSurface());
            prices.add(h.getPrice());
            nbrFloor.add(h.getNumber_floor());
            nbrRooms.add(h.getNumber_rooms());
            gardens.add((float) h.getGarden());
            pools.add((float) h.getPool());


        }
        House Pertnenth = new House();
        Pertnenth.setSurface(mostOcuuredvaluesF(surfaces));
        Pertnenth.setPrice(mostOcuuredvaluesF(prices));
        Pertnenth.setNumber_floor(mostOcuuredvaluesI(nbrFloor));
        Pertnenth.setNumber_rooms(mostOcuuredvaluesI(nbrRooms));
        Pertnenth.setGarden(mostOcuuredvaluesF(gardens));
        Pertnenth.setPool(mostOcuuredvaluesF(pools));
        //int nb=mostOcuuredvaluesF(pools);

        //searching similar house
        //List<House> houses= favH;

        List<House> houses=real_EstateRepository.HousesList();
        List<House> pertinentHouses=new ArrayList<>();
        List<ObjectRassemblenceResponse> Orr=new ArrayList<>();
        for (House re:houses) {
            Orr.add(new ObjectRassemblenceResponse(ObjectComparation(re,Pertnenth),re));

        }
        //sort houses by ressemblance
        Collections.sort(Orr, new Comparator<ObjectRassemblenceResponse>() {
            public int compare(ObjectRassemblenceResponse obj1, ObjectRassemblenceResponse obj2) {
                return Integer.compare(obj1.getResseblance(), obj2.getResseblance());
            }
        });
        //Collections.reverse(Orr);
        for (ObjectRassemblenceResponse or:Orr) {
            pertinentHouses.add((House) or.getObject());

        }

        Ore.set(0,new OccsOfRealEastateRequest(favH.size(),pertinentHouses ));
        //sort of realEastate byOccs
        Collections.sort(Ore, new Comparator<OccsOfRealEastateRequest>() {
            public int compare(OccsOfRealEastateRequest obj1, OccsOfRealEastateRequest obj2) {
                return Integer.compare(obj1.getOccs(), obj2.getOccs());
            }
        });
        Collections.reverse(Ore);

        return Ore;
    }


    //créer un objet avec ses criteres
//chercher les objets qui le rassemble
//ajouter une durée pour les favories bschedular
//fazt ta3ml list fha id mt3 real estate wl wscore de similarité // ogjet pertinent


/*********************utils functions*****************************************/
/*********************String*****************************************/

    public String mostOcuuredvaluesS(List<String> strings) {
        List<Integer> nbrOccs = new ArrayList<>();
        for (String s : strings) {
            nbrOccs.add(stringOccurs(strings, s));
        }

        Integer MaxOcc = MaxInt(nbrOccs);
        Integer index = 0;
        for (int i = 0; i < nbrOccs.size(); i++) {
            if (nbrOccs.get(i) == MaxOcc) {
                index = i;
            }
        }
        return strings.get(index);
    }
        public Integer stringOccurs(List<String> strings,String searched){
            int occ=0;
            for (String s:strings) {
                if (s.equals(searched)){
                    occ=occ+1;
                }

            }
            return occ;
        }


    /*********************Boolean*****************************************/

    public Boolean mostOcuuredvaluesB(List<Boolean> booleans){
        List<Integer> nbrOccs=new ArrayList<>();
        for (Boolean b:booleans) {
            nbrOccs.add(booleanOccurs(booleans,b));
        }

        Integer MaxOcc=MaxInt(nbrOccs);
        Integer index=0;
        for (int i = 0; i < nbrOccs.size(); i++) {
            if (nbrOccs.get(i)==MaxOcc) {
                index=i;
            }
        }
        return booleans.get(index);
    }
    /*********************Integer*****************************************/

    public Integer mostOcuuredvaluesI(List<Integer> integers) {
        List<Integer> nbrOccs = new ArrayList<>();
        for (Integer i : integers) {
            nbrOccs.add(integerOccurs(integers, i));
        }

        Integer MaxOcc = MaxInt(nbrOccs);
        Integer index = 0;
        for (int i = 0; i < nbrOccs.size(); i++) {
            if (nbrOccs.get(i) == MaxOcc) {
                index = i;
            }
        }
        return integers.get(index);
    }
    public Integer integerOccurs(List<Integer> integers,Integer searched){
        int occ=0;
        for (Integer i:integers) {
            if (i==searched){
                occ=occ+1;
            }

        }
        return occ;
    }
    /*********************Float*****************************************/

    public Float mostOcuuredvaluesF(List<Float> floats) {
        List<Integer> nbrOccs = new ArrayList<>();
        for (Float f : floats) {
            nbrOccs.add(floatOccurs(floats, f));
        }

        Integer MaxOcc = MaxInt(nbrOccs);
        Integer index = 0;
        for (int i = 0; i < nbrOccs.size(); i++) {
            if (nbrOccs.get(i) == MaxOcc) {
                index = i;
            }
        }
        return floats.get(index);
    }
    public Integer floatOccurs(List<Float> floats,Float searched){
        int occ=0;
        for (Float f:floats) {
            if (Float.compare(f, searched)==0){
                occ=occ+1;
            }

        }
        return occ;
    }

    public Integer booleanOccurs(List<Boolean> bools,Boolean searched){
        int occ=0;
        for (Boolean b:bools) {
            if (b.equals(searched)){
                occ=occ+1;
            }

        }
return occ;
    }

    public Integer MaxInt(List<Integer> nbrOccs){
        Integer max = nbrOccs.get(0);

        for (Integer i : nbrOccs) {
            if (i > max) {
                max = nbrOccs.get(i);
            }
        }

        return max;
    }
    public int ObjectComparation(House h1, House h2){
        int similarity=0;
        if (Float.compare(h1.getPrice(), h2.getPrice())==0){
            similarity=similarity+1;
        }
        if (Float.compare((float) h1.getGarden(),(float) h2.getGarden())==0){
            similarity=similarity+1;
        }
        if (Float.compare(h1.getSurface(), h2.getSurface())==0){
            similarity=similarity+1;
        }
        if (Float.compare((float) h1.getPool(),(float) h2.getPool())==0){
            similarity=similarity+1;
        }
        if (h1.getNumber_rooms()==h2.getNumber_rooms()){
            similarity=similarity+1;
        }
        if (h1.getNumber_floor()==h2.getNumber_floor()){
            similarity=similarity+1;
        }
        return  similarity;
    }
}
