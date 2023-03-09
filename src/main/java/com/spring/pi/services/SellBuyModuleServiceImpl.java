package com.spring.pi.services;

import com.spring.pi.entities.*;
import com.spring.pi.entities.notMapped.EmailDetails;
import com.spring.pi.payload.request.ContractRequest;
import com.spring.pi.payload.request.ContractValidationRequest;
import com.spring.pi.repositories.*;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
public class SellBuyModuleServiceImpl implements SellBuyModuleService {
    Real_EstateRepository realEstateRepository;

    ContractRepository contractRepository;
    Actor_ContractRepository actorContractRepository;
    ActorRepository actorRepository;
    TransactionRepository transactionRepository;
    ContactingService contactingService;

    IService IService;


    @Override
    public Contract addAndAssignContractToRealEstateAndActorContract(ContractRequest contractRequest) {
        Actor provider= actorRepository.findById(contractRequest.getIdProvider()).orElse(null);
        Actor consumer= actorRepository.findById(contractRequest.getIdConsumer()).orElse(null);
        Actor_Contract ac = new Actor_Contract();
        actorContractRepository.save(ac);
        ac.setProvider(provider);
        ac.setConsumer(consumer);
        actorContractRepository.save(ac);
        Contract c = new Contract();
        contractRepository.save(c);
        c.getActor_Contrats().add(ac);
        c.setBegin_Date(contractRequest.getBegin_Date());
        c.setEnd_Date(contractRequest.getEnd_Date());
        c.setCreatedDate(contractRequest.getCreatedDate());
        c.setPrice_Cont(contractRequest.getPrice_Cont());
        c.setRevenue(contractRequest.getRevenue());
        c.setValid(false);
        Transaction t = new Transaction();
        transactionRepository.save(t);
        t.setDateTrans(contractRequest.getDateTrans());
        c.getTransactions().add(t);
        Real_Estate re = realEstateRepository.findById(contractRequest.getIdRealEstate()).orElse(null);
        t.setReal_Estate(re);
        transactionRepository.save(t);
        return contractRepository.save(c);
    }

    @Override
    public Contract validerContract(ContractValidationRequest cvr) {
        Contract c = contractRepository.findById(cvr.getIdContract()).orElse(null);
        c.setValid(true);
        c.setBegin_Date(cvr.getStartDate());
        c.setEnd_Date(cvr.getEndDate());
        return  contractRepository.save(c);
    }

    @Override
    public List<String> Statistique() {
        List<String> stat= new ArrayList<String>();

        //Pourcentage des contrats  validés
        List<Contract> lc= contractRepository.findAll();
        List<Contract> lcv= contractRepository.findContractByValid(false);
        stat.add("le pourcentage des contrats validés= " + Float.intBitsToFloat (lcv.size()) /Float.intBitsToFloat( lc.size())*100+ "%");
       //les prix des real estates les plus vendus
        stat.add("les prix des real estates les plus vendus= " + contractRepository.MostContractPrice().get(0)+ "dt");
        return stat;

    }

    @Override
    //@Scheduled(cron = "0 0 12 */3 * *")
    //@Scheduled(cron = "*/30 * * * * *")
    public void NotifySellerandBuyer() {
        List<Contract> lc= contractRepository.findAll();
        for (Contract c : lc) {
            Set<Actor_Contract> acs = c.getActor_Contrats();
            for (Actor_Contract ac: acs
                 ) {
                Actor provider=ac.getProvider();
                Actor consumer=ac.getConsumer();
                try{
                EmailDetails ed=new EmailDetails();
                ed.setRecipient(provider.getEmail());
                ed.setSubject(" Notification from Tsamsira");
                String content= "<html>"
                        + "<body>"
                        + "<div style=\"background-color: #f9f9f9; padding: 20px;\">"
                        + "<img src=\"https://zupimages.net/up/23/09/am8j.png\"  style=\"display: block; margin: 0 auto;\" />"
                        + "<p>Bonjour,</p>"
                        +"<p>Nous vous informons que vous avez créé un contrat à "+ c.getCreatedDate()+" mais ne l'avez pas encore signé.</p>"
                        +" <p>Il est important que vous signiez ce contrat dès que possible.</p>"
                        +"<p>Vous pouvez accéder au contrat en cliquant sur le lien ci-dessous :</p>"
                        +"<br>"
                        +"<p>Si vous avez des questions ou des préoccupations, n'hésitez pas à nous contacter.</p>"
                        +"<p>Merci,</p>"
                        +"<p>L'équipe Tsamsira</p>"
                        +"</div>"
                        + "</body>"
                        + "</html>";
                ed.setMsgBody(content);
                contactingService.sendSimpleMail(ed);
                ed.setRecipient(consumer.getEmail());
                contactingService.sendSimpleMail(ed);


            } catch (Exception ex) {



            }
            }

        }
    }

    public String estimerRevenue(Contract contract ) {
        long months= ChronoUnit.MONTHS.between(contract.getBegin_Date(), contract.getEnd_Date());
        double result=contract.getRevenue()*months;


        return "Votre Revenue Estime Total est " +result ;

    }

    public String estimerROA(Contract contract) {
        long months= ChronoUnit.MONTHS.between(contract.getBegin_Date(), contract.getEnd_Date());
        double result=contract.getRevenue()*months;
        double P=contract.getPrice_Cont();
        double ROA=(result-P)/P*100;
        return "Votre ROA Estime Total est %"  +ROA ;

    }

    public String estimerrprix(Contract contract) {
        int yb=contract.getBegin_Date().getYear();
        int y;
        int month=contract.getBegin_Date().getMonthValue();
        int i=0;
        double total=0;
        double P=contract.getPrice_Cont();
        double result=contract.getRevenue();

        do {
            i = i + 1;

            total = total + result;
        }
        while (P > total);

        month = month + i;
        y=yb;
        if (month > 12)
            do {
                month = month - 12;
                y = y + 1;

            } while (month > 12);


        return "le mois et l'annee estimé  du return de votre capital est "  +month+ " "+y ;    }


    public String calculerRevenue (Long id) {
        Contract c=IService.getContractById(id);
        long months= ChronoUnit.MONTHS.between(c.getBegin_Date(), c.getEnd_Date());
        double result=c.getRevenue()*months;

        return "Votre Revenue Total est "  +result ;


    }

    public String calculerROA (Long id) {
        Contract c=IService.getContractById(id);
        long months= ChronoUnit.MONTHS.between(c.getBegin_Date(), c.getEnd_Date());
        double result=c.getRevenue()*months;
        double P=c.getPrice_Cont();
        double ROA=(result-P)/P*100;
        return "Votre ROA Total est %"  +ROA ;    }

    public String calculerrprix (Long id) {
        Contract c=IService.getContractById(id);
        int yb=c.getBegin_Date().getYear();
        int y;
        int month=c.getBegin_Date().getMonthValue();
        int i=0;
        double total=0;
        double P=c.getPrice_Cont();
        double result=c.getRevenue();

        do {
            i = i + 1;

            total = total + result;
        }
        while (P > total);

        month = month + i;
        y=yb;
        if (month > 12)
            do {
                month = month - 12;
                y = y + 1;

            } while (month > 12);
        return "le mois et l'annee estimé  du return de votre capital est "  +month+ " "+y ;    }


}
