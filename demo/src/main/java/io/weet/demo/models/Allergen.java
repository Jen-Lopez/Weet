package io.weet.demo.models;

public class Allergen {
    private int id;
    private String allergen;

    public Allergen(){}

    public Allergen(int id, String allergen){
        this.id=id;
        this.allergen=allergen;
    }
    public int getId(){
        return id;
    }
    public void setId(int id){
        this.id=id;
    }
    public String getAllergen(){
        return allergen;
    }
    public void setAllergen(String allergen){
        this.allergen=allergen;
    }

    @Override
    public String toString(){
        return String.format("ID = %d; Allergen = %s", id, allergen);
    }
    
    
}
