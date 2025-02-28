package com.example.yourmeal.model;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AllCategoriesResponse {
    @SerializedName("meals")
    private List<Category> categoryList;

    public List<Category> getCategories() { return categoryList; }
    public void setCategories(List<Category> categoryList) { this.categoryList = categoryList; }
}



