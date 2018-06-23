package com.example.android.beautystore1.Models;

public class Store {
    private String store_id;
    private String store_address;
    private String working_hours;
    private String phone_number;

    public Store(String store_address, String working_hours, String phone_number) {
        this.store_address = store_address;
        this.working_hours = working_hours;
        this.phone_number = phone_number;
    }

    public String getStore_id() {
        return store_id;
    }

    public void setStore_id(String store_id) {
        this.store_id = store_id;
    }

    public String getStore_address() {
        return store_address;
    }

    public void setStore_address(String store_address) {
        this.store_address = store_address;
    }

    public String getWorking_hours() {
        return working_hours;
    }

    public void setWorking_hours(String working_hours) {
        this.working_hours = working_hours;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }
}
