package com.pln.database.model;

import javax.persistence.*;

@Entity
@Table(name = "tbl_pelanggan")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id_pelanggan;

    String no_pelanggan;
    int id_daya;
    int id_user;

    public Customer() {
    }

    public int getId_pelanggan() {
        return id_pelanggan;
    }

    public void setId_pelanggan(int id_pelanggan) {
        this.id_pelanggan = id_pelanggan;
    }

    public String getNo_pelanggan() {
        return no_pelanggan;
    }

    public void setNo_pelanggan(String no_pelanggan) {
        this.no_pelanggan = no_pelanggan;
    }

    public int getId_daya() {
        return id_daya;
    }

    public void setId_daya(int id_daya) {
        this.id_daya = id_daya;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }
}
