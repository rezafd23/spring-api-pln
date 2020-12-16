package com.pln.database.model;

import javax.persistence.*;

@Entity
@Table(name="tbl_daya")
public class Power {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id_daya;

    String daya;
    Double tarif;

    public Power() {
    }

    public int getId_daya() {
        return id_daya;
    }

    public void setId_daya(int id_daya) {
        this.id_daya = id_daya;
    }

    public String getDaya() {
        return daya;
    }

    public void setDaya(String daya) {
        this.daya = daya;
    }

    public Double getTarif() {
        return tarif;
    }

    public void setTarif(Double tarif) {
        this.tarif = tarif;
    }
}
