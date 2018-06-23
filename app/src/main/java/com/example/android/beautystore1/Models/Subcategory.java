package com.example.android.beautystore1.Models;

public class Subcategory {
    private int subcategory_id;
    private String subcategory_name;
    private Category mCategory;

    public Subcategory(int subcategory_id, String subcategory_name, Category category) {
        this.subcategory_id = subcategory_id;
        this.subcategory_name = subcategory_name;
        mCategory = category;
    }

    public int getSubcategory_id() {
        return subcategory_id;
    }

    public void setSubcategory_id(int subcategory_id) {
        this.subcategory_id = subcategory_id;
    }

    public String getSubcategory_name() {
        return subcategory_name;
    }

    public void setSubcategory_name(String subcategory_name) {
        this.subcategory_name = subcategory_name;
    }

    public Category getCategory() {
        return mCategory;
    }

    public void setCategory(Category category) {
        mCategory = category;
    }


}
