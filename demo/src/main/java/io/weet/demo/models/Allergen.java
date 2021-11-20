package io.weet.demo.models;

public class Allergen {
    private String allergen;

    public Allergen(){}

    public Allergen(String allergen){
        this.allergen=allergen;
    }
  
    public String getAllergen(){
        return allergen;
    }
    public void setAllergen(String allergen){
        this.allergen=allergen;
    }

    @Override
    public String toString(){
        return String.format("Allergen = %s", allergen);
    }
    
}
