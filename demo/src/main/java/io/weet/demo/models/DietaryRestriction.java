package io.weet.demo.models;

public class DietaryRestriction {
    private String dietaryRestriction;

    public DietaryRestriction(){}

    public DietaryRestriction(String dietaryRestriction){
        this.dietaryRestriction=dietaryRestriction;
    }
  
    public String getDietaryRestriction(){
        return dietaryRestriction;
    }
    public void setDietaryRestriction(String dietaryRestriction){
        this.dietaryRestriction=dietaryRestriction;
    }

    @Override
    public String toString(){
        return String.format("Dietary Restriction = %s", dietaryRestriction);
    }
    
}