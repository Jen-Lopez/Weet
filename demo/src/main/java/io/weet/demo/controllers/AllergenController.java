package io.weet.demo.controllers;
import io.weet.demo.services.OpenMenuService;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
//@RequestMapping("/allergen")
public class AllergenController {
    private List<Allergen> listAllergens;

    @PostConstruct
    private void loadData(){
        Allergen emp1= new Allergen(1, "Peanuts");
        Allergen emp2= new Allergen(2, "Eggs");
        Allergen emp3= new Allergen(3, "Soy");
        Allergen emp4= new Allergen(4, "Soy");
        Allergen emp5= new Allergen(5, "Avocado");

        listAllergens=new ArrayList<>();
        listAllergens.add(emp1);
        listAllergens.add(emp2);
        listAllergens.add(emp3);
        listAllergens.add(emp4);
        listAllergens.add(emp5);

    }
    @GetMapping("/list")
    public String showAllergens(Model model){
        model.addAttribute("allergens", listAllergens);
        return "Allergen";
    }
   

}
