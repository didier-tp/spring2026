package tp.appliSpring.bank.site.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import tp.appliSpring.bank.core.model.Client;
import tp.appliSpring.bank.site.form.InscriptionForm;


@Controller
@RequestMapping("/site/basic")
//@SessionAttributes({"x" ,"racine" })
public class BasicController {
    @RequestMapping("helloWorld")
    public String helloWorld(Model model) {
        model.addAttribute("message", "Hello_world");
        return "displayBasicMessage" ;//.jsp ou .html(thymeleaf)
    }

    @RequestMapping("calculTva")
    public String calculTva(Model model,
                            @RequestParam(name="ht",defaultValue = "0") double ht,
                            @RequestParam(name="taux",defaultValue = "0") double taux) {
        double tva=ht*taux/100;
        double ttc=ht+tva;
        model.addAttribute("tva", tva);
        model.addAttribute("ttc", ttc);
        model.addAttribute("ht", ht); //pour réafficher la valeur saisie précédemment
        model.addAttribute("taux", taux);//pour réafficher la valeur saisie précédemment
        return "calcul_tva" ;//.jsp ou .html(thymeleaf)
    }

    @ModelAttribute("x")
    public Double addDefaultXAttributeInModel() {
        return 0.0;
    }

    @ModelAttribute("racine")
    public Double addDefaultRacineAttributeInModel() {
        return 0.0;
    }

    @RequestMapping("calculRacineCarree")
    public String calculRacineCarree(Model model,
                                     @RequestParam(name="x",required = false) Double x) {
        if(x!=null) {
            double racine = Math.sqrt(x);
            model.addAttribute("x", x);
            model.addAttribute("racine", racine);
        }
        return "calcul_racine" ;//.jsp ou .html(thymeleaf)
    }

    @ModelAttribute("inscriptionF")
    public InscriptionForm addDefaultInscriptionAttributeInModel() {
        return new InscriptionForm();
    }
    @RequestMapping("toInscription")
    public String toInscription(Model model) {
        return "inscription";
    }
    @RequestMapping("doInscription")
    public String doInscription(Model model,
                                @ModelAttribute InscriptionForm inscriptionForm) {
        if(inscriptionForm.getSportif()==false)
            inscriptionForm.setSportPrincipal(null);
        model.addAttribute("inscriptionF" , inscriptionForm );
        return "recapInscription";
    }

}