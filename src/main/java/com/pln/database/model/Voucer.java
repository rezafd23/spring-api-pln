package com.pln.database.model;

import javax.persistence.*;

@Entity
@Table(name = "tbl_voucer")
public class Voucer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id_voucer;

    String voucer;
    int harga;

    public Voucer() {
    }

    public int getId_voucer() {
        return id_voucer;
    }

    public void setId_voucer(int id_voucer) {
        this.id_voucer = id_voucer;
    }

    public String getVoucer() {
        return voucer;
    }

    public void setVoucer(String voucer) {
        this.voucer = voucer;
    }

    public int getHarga() {
        return harga;
    }

    public void setHarga(int harga) {
        this.harga = harga;
    }
}
