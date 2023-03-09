package com.spring.pi.controllers;


//import com.itextpdf.text.DocumentException;
import com.itextpdf.text.DocumentException;
import com.spring.pi.entities.Actor;
import com.spring.pi.entities.Actor_construction;
import com.spring.pi.entities.HouseBuilding;
import com.spring.pi.payload.request.AddConstructionRequeste;
import com.spring.pi.repositories.ActorConstructionRepostory;
import com.spring.pi.repositories.ActorRepository;
import com.spring.pi.repositories.MaisonRepository;
import com.spring.pi.services.IServiceConstruction;
import com.spring.pi.services.PFservice;
import com.spring.pi.services.PdfService;
import com.spring.pi.services.ServiceImpConstructionConstruction;
//import freemarker.template.TemplateException;
import lombok.AllArgsConstructor;
import net.sf.jasperreports.engine.JRException;
import org.apache.commons.io.IOUtils;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;




import javax.mail.MessagingException;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;



@RestController
@RequestMapping("/gener")
@AllArgsConstructor
public class ConstructionControlleur {
    IServiceConstruction iServiceConstruction;
    ActorRepository actorRepository;
    ActorConstructionRepostory actorConstructionRepostory;
    //MailService mailService;
    ServiceImpConstructionConstruction serviceImpConstruction;
    PdfService pdfGeneratorService;
    MaisonRepository maisonRepository;
    PFservice pservice;

    @GetMapping("/serach")
    @ResponseBody
    public ResponseEntity<?> search(@RequestParam(value = "surfaceterrain",required = false) Long surfaceterrain,
                                    @RequestParam(value = "HouseSurface",required = false) Long HouseSurface,
                                    @RequestParam(value = "nbrofroom",required = false) Integer nbrofroom,
                                    @RequestParam(value = "piscine",required = false) Boolean piscine,
                                    @RequestParam(value = "jardin",required = false) Boolean jardin,
                                    @RequestParam(value = "nbreEtage",required = false) Integer nbreEtage,
                                    @RequestParam(value = "lieux",required = false)String lieux,
                                    @RequestParam(value = "prix",required = false) Long prix) {
        List<Object> maisonList = iServiceConstruction.search(surfaceterrain,HouseSurface,nbrofroom,piscine,jardin,nbreEtage,lieux,prix);
    return new ResponseEntity<>(maisonList, HttpStatus.ACCEPTED);
    }
    @GetMapping("/find")
    @ResponseBody
    public List<HouseBuilding> findl(){
        return maisonRepository.findAll();
    }
   @GetMapping(value = "/pdfreport", produces = MediaType.APPLICATION_PDF_VALUE)
   public ResponseEntity<ByteArrayResource> generatePdf(HttpServletResponse response) throws IOException, DocumentException {

       List<Actor_construction> data=actorConstructionRepostory.findAll();
       ByteArrayInputStream bis = pdfGeneratorService.generatePdf(data);

       HttpHeaders headers = new HttpHeaders();
       headers.add("Content-Disposition", "inline; filename=pdfreport.pdf");
       byte[] byteArray = IOUtils.toByteArray(bis);

       response.setContentType("application/pdf");
       response.setHeader("Content-Disposition", "attachment; filename=pdfreport.pdf");
       response.getOutputStream().write(IOUtils.toByteArray(bis));
       response.getOutputStream().flush();
       return ResponseEntity
               .ok()
               .headers(headers)
               .contentType(MediaType.APPLICATION_PDF)
               .body(new ByteArrayResource(byteArray));
   }
   /*@GetMapping("/send/{id}")
   @ResponseBody
    public ResponseEntity<?>  sendmail(@PathVariable("id") Long id, Map<String, String> Model) throws MessagingException, TemplateException, IOException {
       Actor u= actorRepository.findById(id).get();
       Model.put("name",u.getFullName());
       mailService.sendmail(u,Model);

       return new ResponseEntity<>(HttpStatus.OK);
    }*/
    @PostMapping("/add")
    @ResponseBody
    public Actor add(@RequestBody AddConstructionRequeste addConstructionRequeste){
       return serviceImpConstruction.add(addConstructionRequeste);
    }
    /*@GetMapping("/getd")
    public Integer getDate(@RequestBody Maison m){
       return serviceImp.(m);
    }*/
    /*GetMapping("/dret/{id}")
    public Integer dret(@PathVariable("id") Long id){
        Maison m=maisonRepository.findById(id).orElse(null);
       return serviceImp.dret(id);
    }
    /*@GetMapping("/getMonthName/{id}")
    @ResponseBody
    public String getMonthName(@PathVariable("id") Long id){
        Maison m=maisonRepository.findById(id).orElse(null);
        return serviceImp.getMonthName(id);

    }*/
    @GetMapping("/getAdvandes/{id}/{firstDay}/{lastDay}/{numbertranche}")
    public List<Object> getAdvandes(@PathVariable("id") Long id,
                                    @PathVariable("firstDay") Date firstDay,
                                    @PathVariable("lastDay") Date lastDay,
                                    @PathVariable("numbertranche")Integer numbertranche){
        return serviceImpConstruction.getAdvandeg(id, firstDay, lastDay,numbertranche);
    }
    @GetMapping("/report")
    public String generateReport( String format) throws FileNotFoundException, JRException {
        return pservice.exportReport(format);
    }

}
