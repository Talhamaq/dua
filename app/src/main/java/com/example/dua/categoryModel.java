package com.example.dua;

public class categoryModel {
    String subCategory;
    int numDuas;
    
    public String getParentCategory() {
        return parentCategory;
    }
    
    public void setParentCategory(String parentCategory) {
        this.parentCategory = parentCategory;
    }
    
    String parentCategory;
    
    
    public categoryModel(String category, int numDuas, String parentCategory) {
        this.subCategory = category;
        this.numDuas = numDuas;
        this.parentCategory = parentCategory;
    }
    
    public String getSubCategory() {
        return subCategory;
    }
    
    public void setSubCategory(String subCategory) {
        this.subCategory = subCategory;
    }
    
    public int getNumDuas() {
        return numDuas;
    }
    
    public void setNumDuas(int numDuas) {
        this.numDuas = numDuas;
    }
}
