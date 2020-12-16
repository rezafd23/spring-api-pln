package com.pln.database.model;

import javax.persistence.*;

@Entity
@Table(name = "tbl_token")
public class Token {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id_token;

    String no_token;
    int id_pelanggan;
    int id_voucer;
    String portal_bayar;
    String status_bayar;
    String status_redeem;
    String kode_bayar;

    public Token() {
    }

    public int getId_token() {
        return id_token;
    }

    public void setId_token(int id_token) {
        this.id_token = id_token;
    }

    public String getNo_token() {
        return no_token;
    }

    public void setNo_token(String no_token) {
        this.no_token = no_token;
    }

    public int getId_pelanggan() {
        return id_pelanggan;
    }

    public void setId_pelanggan(int id_pelanggan) {
        this.id_pelanggan = id_pelanggan;
    }

    public int getId_voucer() {
        return id_voucer;
    }

    public void setId_voucer(int id_voucer) {
        this.id_voucer = id_voucer;
    }

    public String getPortal_bayar() {
        return portal_bayar;
    }

    public void setPortal_bayar(String portal_bayar) {
        this.portal_bayar = portal_bayar;
    }

    public String getStatus_bayar() {
        return status_bayar;
    }

    public void setStatus_bayar(String status_bayar) {
        this.status_bayar = status_bayar;
    }

    public String getStatus_redeem() {
        return status_redeem;
    }

    public void setStatus_redeem(String status_redeem) {
        this.status_redeem = status_redeem;
    }

    public String getKode_bayar() {
        return kode_bayar;
    }

    public void setKode_bayar(String kode_bayar) {
        this.kode_bayar = kode_bayar;
    }
}

