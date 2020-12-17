package com.pln.database.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "view_token")
public class ViewToken {
    @Id
    String no_pelanggan;

    double token;

    public ViewToken() {
    }

    public String getNo_pelanggan() {
        return no_pelanggan;
    }

    public void setNo_pelanggan(String no_pelanggan) {
        this.no_pelanggan = no_pelanggan;
    }

    public double getToken() {
        return token;
    }

    public void setToken(double token) {
        this.token = token;
    }
}
