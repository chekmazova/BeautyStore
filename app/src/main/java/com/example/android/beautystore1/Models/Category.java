package com.example.android.beautystore1.Models;

public class Category {
    private Integer category_id;
    private String category_name;


    public Category(String name) {
        this.category_name = name;
    }

    public Category() {
    }

    public String getName() {
        return category_name;

    }

    public void setName(String name) {
        this.category_name = name;
    }

    public Integer getCategory_id() {
        return category_id;
    }

    public void setCategory_id(Integer category_id) {
        this.category_id = category_id;
    }
}
