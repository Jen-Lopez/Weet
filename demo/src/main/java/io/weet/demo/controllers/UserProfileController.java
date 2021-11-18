package io.weet.demo.controllers;
import io.weet.demo.models.Allergen;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller

public class UserProfileController {
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

    @GetMapping("/user")
    public String userProfile(Model model) {
        model.addAttribute("allergenList", listAllergens);
        return "userProfile";
    }
    
    @PostMapping("/allergenDelete")
    public String allergenDelete(@RequestParam(name = "id") int id) {
        for (int i = 0; i < listAllergens.size(); i++) {
            Allergen a = listAllergens.get(i);
            if (a.getId() == id) {
                listAllergens.remove(a);
                break;
            }
        }
        return "redirect:/user";   
    }

    @PostMapping("/addAllergen")
    public String addAllergen(@RequestParam(name = "name") String name) {
         listAllergens.add(new Allergen(listAllergens.size()+1, name));
        return "redirect:/user";   
    }
   

}
