package io.weet.demo.controllers;



public class Allergen {
   // private String user;
    private int id;
    private String allergen;

    public Allergen(){

    }
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
   /*public String getUser(){
        return user;
    }
    public void setUser(String user){
        this.user=user;
    }*/
    @Override
    public String toString(){
        return "id= "+ id + "allergen= "+allergen;   }
}
