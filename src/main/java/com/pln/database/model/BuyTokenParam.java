package com.pln.database.model;

public class BuyTokenParam {
    private int id_voucer;
    private String no_pelanggan;
    private String status_bayar;
    private String portal_bayar;
    private String kode_bayar;

    public BuyTokenParam() {
    }

    public int getId_voucer() {
        return id_voucer;
    }

    public void setId_voucer(int id_voucer) {
        this.id_voucer = id_voucer;
    }

    public String getNo_pelanggan() {
        return no_pelanggan;
    }

    public void setNo_pelanggan(String no_pelanggan) {
        this.no_pelanggan = no_pelanggan;
    }

    public String getStatus_bayar() {
        return status_bayar;
    }

    public void setStatus_bayar(String status_bayar) {
        this.status_bayar = status_bayar;
    }

    public String getPortal_bayar() {
        return portal_bayar;
    }

    public void setPortal_bayar(String portal_bayar) {
        this.portal_bayar = portal_bayar;
    }

    public String getKode_bayar() {
        return kode_bayar;
    }

    public void setKode_bayar(String kode_bayar) {
        this.kode_bayar = kode_bayar;
    }
}
