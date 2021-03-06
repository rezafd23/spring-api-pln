package com.pln.database.service;

import com.google.gson.Gson;
import com.pln.database.model.Power;
import com.pln.database.model.Token;
import com.pln.database.repositories.PowerDao;
import com.pln.database.repositories.TokenDao;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public class TokenService {
    private TokenDao tokenDao;
    private EntityManager entityManager;

    public TokenService() {
    }

    public void connectJPA() {
        this.entityManager = Persistence
                .createEntityManagerFactory("pln-api")
                .createEntityManager();
        tokenDao = new TokenDao(entityManager);
        try {
            entityManager.getTransaction().begin();
        } catch (IllegalStateException e) {
            entityManager.getTransaction().rollback();
        }
    }

    public void commitJPA(EntityManager entity) {
        try {
            entity.getTransaction().commit();
            entity.close();
        } catch (IllegalStateException e) {
            entity.getTransaction().rollback();
        }
    }

    public String buyToken(String buyToken) {
        Token token = new Gson().fromJson(buyToken, Token.class);
        connectJPA();
        String res = tokenDao.buyToken(token);
//        System.out.println("PowerService1_1: " + res);
        try {
            commitJPA(entityManager);
            return res;
        } catch (Exception e) {
//            e.printStackTrace();
//            System.out.println("PowerService1_2");
            return "0";
        }
    }

    public int redeemToken(String no_token) {
        connectJPA();
        int res = tokenDao.redeemToken(no_token);
        try {
            commitJPA(entityManager);
            return res;
        } catch (Exception e) {
            return 0;
        }
    }

    public String getToken(String no_pelanggan){
        connectJPA();
        double res = tokenDao.getToken(no_pelanggan);
        try {
            commitJPA(entityManager);
            return String.valueOf(res);
        } catch (Exception e) {
            return "null";
        }
    }
}
