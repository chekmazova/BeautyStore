package com.example.android.beautystore1.Models;

import java.util.Date;
import java.util.List;

public class Customer extends User {
    private  Integer customer_id;
    private Voucher voucher_id;
    private List<Cart> cartItems;
//    private Date birth_date;
//    private String gender;


    public Customer(String first_name, String last_name, String phone_number, String email, String password) {
        super(first_name, last_name, phone_number, email, password);
    }

    public Customer(int customer_id, String first_name, String last_name, String phone_number, String email, String password) {
        super(first_name, last_name, phone_number, email, password);
        this.customer_id = customer_id;
//        this.birth_date = birth_date;
//        this.gender = gender;
    }

    public Integer getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(Integer customer_id) {
        this.customer_id = customer_id;
    }

    public Voucher getVoucher_id() {
        return voucher_id;
    }

    public void setVoucher_id(Voucher voucher_id) {
        this.voucher_id = voucher_id;
    }

    public List<Cart> getCartItems() {
        return cartItems;
    }

    public void setCartItems(List<Cart> cartItems) {
        this.cartItems = cartItems;
    }
}
