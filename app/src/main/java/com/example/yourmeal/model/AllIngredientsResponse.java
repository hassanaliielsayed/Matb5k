package com.example.yourmeal.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AllIngredientsResponse {
    @SerializedName("meals")
    private List<IngredientsAPI> ingredientsAPIList;

    public List<IngredientsAPI> getIngredientsAPI() { return ingredientsAPIList; }
    public void setIngredientsAPI(List<IngredientsAPI> ingredientsAPIList) { this.ingredientsAPIList = ingredientsAPIList; }
}


