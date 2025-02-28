
package com.example.yourmeal.model;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AllAreasResponse {
    @SerializedName("meals")
    private List<AreaMeal> areaMealList;

    public List<AreaMeal> getAreaMealList() { return areaMealList; }
    public void setAreaMealList(List<AreaMeal> areaMealList) { this.areaMealList = areaMealList; }
}



