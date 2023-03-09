package com.spring.pi.services;

import com.spring.pi.entities.Actor;
import com.spring.pi.entities.Actor_construction;
import com.spring.pi.entities.ConstructionAgency;
import com.spring.pi.entities.HouseBuilding;
import com.spring.pi.payload.request.AddConstructionRequeste;
import com.spring.pi.repositories.ActorConstructionRepostory;
import com.spring.pi.repositories.ActorRepository;
import com.spring.pi.repositories.AgenceRepository;
import com.spring.pi.repositories.MaisonRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;




import java.time.LocalDate;

import java.util.*;



@Slf4j
@Service
@AllArgsConstructor
public class ServiceImpConstructionConstruction implements IServiceConstruction {
    MaisonRepository maisonRepository;
    ActorRepository actorRepository;
    AgenceRepository agenceRepository;
    ActorConstructionRepostory actorConstructionRepostory;
    //////fonction de recherche de maisonn de recherche
    @Override
    public List<Object> search(Long surfaceterrain, Long HouseSurface, Integer nbrofroom, Boolean piscine, Boolean jardin,
                               Integer nbreEtage, String lieux, Long prix) {
        List<HouseBuilding> houseBuildingList = maisonRepository.finfMaison(surfaceterrain, HouseSurface, nbrofroom, piscine, jardin, nbreEtage, lieux, prix);
        List<HouseBuilding> m = maisonRepository.findAll();
        List<Object> staticList=statique(surfaceterrain, HouseSurface, nbrofroom, piscine, jardin, nbreEtage, lieux, prix);
        List<Object> objectList= new ArrayList<>();
        objectList.add(houseBuildingList);
        objectList.add(staticList);
        return objectList;
    }
    public List<Object> statique(Long surfaceterrain, Long HouseSurface, Integer nbrofroom, Boolean piscine, Boolean jardin,
                                 Integer nbreEtage, String lieux, Long prix){
        List<HouseBuilding> m = maisonRepository.findAll();
        Long i= 0L;
        long k=0L;
        int h=0;
        List<Object> agencename= new ArrayList<>();
        if(!(lieux.isEmpty()) && (surfaceterrain!=null)){
            for (HouseBuilding s : m) {
                if (Objects.equals(lieux, s.getLieux()) && (Objects.equals(surfaceterrain, s.getSurfaceterrain()))  ) {
                    i += (s.getPrix());
                    List<HouseBuilding> q = maisonRepository.findAllByLieuxAndSurfaceterrainEquals(lieux, surfaceterrain);
                    h=q.size();
                    k = i / h;
                    for (HouseBuilding houseBuilding :q){
                        agencename.add(houseBuilding.getConstructionAgency().getName());
                        agencename.add(houseBuilding.getConstructionAgency().getTelephone());
                    }

                }}}
        else if(!(lieux.isEmpty()) && (surfaceterrain!=null) && (HouseSurface!=null)){
            for (HouseBuilding s : m) {
                if (Objects.equals(lieux, s.getLieux()) && (Objects.equals(surfaceterrain, s.getSurfaceterrain()))  && (Objects.equals(HouseSurface, s.getHouseSurface()))) {
                    i += (s.getPrix());
                    List<HouseBuilding> q = maisonRepository.findAllByLieuxAndSurfaceterrainEqualsAndHouseSurfaceEquals(lieux, surfaceterrain,HouseSurface);
                    h=q.size();
                    k = i / h;
                    for (HouseBuilding houseBuilding :q){
                        agencename.add(houseBuilding.getConstructionAgency().getName());
                        agencename.add(houseBuilding.getConstructionAgency().getTelephone());
                    }
                }
            }
        }
        else if(!(lieux.isEmpty()) && (surfaceterrain!=null) && (HouseSurface!=null) && (nbrofroom!=null) &&(nbreEtage!=null)){
            for (HouseBuilding s : m) {
                if (Objects.equals(lieux, s.getLieux()) && (Objects.equals(surfaceterrain, s.getSurfaceterrain()))  && (Objects.equals(HouseSurface, s.getHouseSurface()))) {
                    i += (s.getPrix());
                    List<HouseBuilding> q = maisonRepository.findAllByLieuxAndSurfaceterrainEqualsAndHouseSurfaceEqualsAndNbrofroomAndNbreEtage(lieux, surfaceterrain,HouseSurface,nbrofroom,nbreEtage);

                    h=q.size();
                    k = i / h;
                    for (HouseBuilding houseBuilding :q){
                        agencename.add(houseBuilding.getConstructionAgency().getName());
                        agencename.add(houseBuilding.getConstructionAgency().getTelephone());
                    }
                }
            }
        }
        else if(!(lieux.isEmpty()))
        {
            for (HouseBuilding s : m){
                if (lieux.equals(s.getLieux())){
                    i +=(s.getPrix());
                    List<HouseBuilding> q =maisonRepository.findAllByLieux(lieux);
                    h=q.size();
                    k = i / h;
                    for (HouseBuilding houseBuilding :q){
                        agencename.add(houseBuilding.getConstructionAgency().getName());
                        agencename.add(houseBuilding.getConstructionAgency().getTelephone());
                    }
                }
            }}
        List<Object> objectList= new ArrayList<>();
        String message="Hola ! this the name and number telephone of agency of construction  " ;
        objectList.add(message);
        objectList.add(agencename);
        String message1="In order to Help you this the number of house_building like your dream hose  " ;
        objectList.add(message1);
        objectList.add(h);
        String message2="and for the more important this the averge of coast of you dream house <3  " ;
        objectList.add(message2);
        objectList.add(k);
        return objectList;
    }
    /////fonction d'aide fianciere pour lutulisatuer
    public List<Object> getAdvandeg(Long id,Date firstDay,Date lastDay,Integer numbertrance){
        HouseBuilding m=maisonRepository.findById(id).orElse(null);
        float ch=0;
        int i=0;
        int z=0;
        int x=0;
        Integer monthback=0;
        Integer monthbetwen=0;
        Integer priceBack = Math.toIntExact(m.getPrix() / numbertrance);
        z=firstDay.getYear()-lastDay.getYear();
        x=z*12;
        i=firstDay.getMonth()-lastDay.getMonth();
        monthbetwen=i+x;
        monthback=monthbetwen/numbertrance;
        List<Object> object=new ArrayList<>();
        String message="Hola ! this the name and number telephone of agency of construction  " ;
        object.add(message);
        object.add(priceBack);
        String message1="In order to Help you this the number of house_building like your dream hose  " ;
        object.add(message1);
        object.add(monthbetwen);
        String message2="and for the more important this the averge of coast of you dream house <3  " ;
        object.add(message2);
        object.add(monthback);
        return object;
    }
    @Override
    public List<Actor_construction> add(AddConstructionRequeste actorConstruc){
        Actor actor= actorRepository.findById(actorConstruc.getIdActor()).orElse(null);
        ConstructionAgency constructionAgency= agenceRepository.findById(actorConstruc.getIdAgenceConstruction()).orElse(null);
        Actor_construction actorConstruction=new Actor_construction();
        int z=actorConstruc.getDateLimit().getYear()-actorConstruc.getDateDebut().getYear();
        int x=z*12;
        int i= 0;
        Float l = (float) (actorConstruc.getPrixC() / actorConstruc.getNbredetranche());
        //actorConstruc.setDate(i+x);
        //actorConstruc.setMensionalite(l);
        actorConstructionRepostory.save(actorConstruction);
        actorConstruction.setDateDebut(actorConstruc.getDateDebut());
        actorConstruction.setDateLimit(actorConstruc.getDateLimit());
        actorConstruction.setPrixC(actorConstruc.getPrixC());
        actorConstruction.setNbredetranche(actorConstruc.getNbredetranche());
        actorConstruction.setMontantRestant(actorConstruc.getPrixC());
        actorConstruction.setDate(i+x);
        actorConstruction.setMensionalite(l);
        HouseBuilding houseBuilding=maisonRepository.save(actorConstruc.getHouseBuilding());

        actor.getActorConstructionList().add(actorConstruction);
        actorRepository.save(actor);
        actorConstruction.setConstructionAgency(constructionAgency);
        actorConstructionRepostory.save(actorConstruction);
        houseBuilding.setConstructionAgency(constructionAgency);
        maisonRepository.save(houseBuilding);


        return actor.getActorConstructionList();

    }
    /////////fonction dgestion d'aide financiere
    @Override
    public Float getPr(Actor_construction a){
        LocalDate currentDate = LocalDate.now();
        float ch=0;
        if(a.getDate().equals(currentDate)){
        float l = a.getPrixC() / a.getNbredetranche();
        if(a.getDateLimit().equals(a.getDateDebut())){
            ch = a.getPrixC() - l;
        }}


        return ch;
    }
    public void updateM(Actor_construction a){
        //Maison maison=maisonRepository.findById(id).orElse(null);


        float f=getPr(a);
        a.setMontantRestant((long) f);
        actorConstructionRepostory.save(a);
    }
    @Scheduled(cron = "* * 1 * * *" )
    public void miseAjou(){
        List<Actor_construction> actorConstructions=actorConstructionRepostory.findAll();
        for(Actor_construction m : actorConstructions){
           if(m.getDate().equals(LocalDate.now().getMonth())){
               updateM(m);
           }
        }
    }}
    /*public Integer getMonthBetwen(Actor_construction a){
        //actor_construction a=actorConstructionRepostory.findById(id).orElse(null)

        int z=a.getDateFin().getYear()-a.getDateDisponbilite().getYear();
        int x=z*12;
        int i=a.getDateFin().getMonth()-a.getDateDisponbilite().getMonth();
        return i+x;
    }

    public Integer dret(Actor_construction a){
        //actor_construction m=actorConstructionRepostory.findById(id).orElse(null);
        Integer c=0;
        assert a != null;
        c=getMonthBetwen(a);
        int k=0;
        k=c/ a.getNbredetranche();
        return k;
    }
    //////statistique apartir l'aide financiere


    }
    /*
    public String getMonthName(Long id) {

        Integer month=dret(id);
        LocalDate localDate = LocalDate.of(0, month, 0);
        return localDate.getMonth().name();
    }
   public LocalDate getDateretour(Maison m){


        long daysBetween = ChronoUnit.MONTHS.between((Temporal)m.getDateDisponbilite(), (Temporal) m.getDateFin());
        int  tranch =Integer.parseInt(String.valueOf(daysBetween/ m.getNbredetranche()));


        System.out.println("Days between dates: " + tranch);
        LocalDate dtnow = LocalDate.now();
        long daysB = ChronoUnit.DAYS.between(dtnow,(Temporal)m.getDateDisponbilite());


        LocalDate date3 = dtnow.plusDays(daysB);
        System.out.println("Date1 plus 10 days: " + date3);
        return date3;

    }
*/

