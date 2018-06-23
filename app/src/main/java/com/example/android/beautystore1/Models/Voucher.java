package com.example.android.beautystore1.Models;

import java.util.Date;

public class Voucher {
    private Integer voucherID;
    private String voucher_name;
    private Double discount;
    private Date start_date;
    private Date end_date;

    public Voucher(Integer voucherID, String voucher_name, Double discount, Date start_date, Date end_date) {
        this.voucherID = voucherID;
        this.voucher_name = voucher_name;
        this.discount = discount;
        this.start_date = start_date;
        this.end_date = end_date;
    }

    public Integer getVoucherID() {
        return voucherID;
    }

    public void setVoucherID(Integer voucherID) {
        this.voucherID = voucherID;
    }

    public String getVoucher_name() {
        return voucher_name;
    }

    public void setVoucher_name(String voucher_name) {
        this.voucher_name = voucher_name;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public Date getStart_date() {
        return start_date;
    }

    public void setStart_date(Date start_date) {
        this.start_date = start_date;
    }

    public Date getEnd_date() {
        return end_date;
    }

    public void setEnd_date(Date end_date) {
        this.end_date = end_date;
    }
}
