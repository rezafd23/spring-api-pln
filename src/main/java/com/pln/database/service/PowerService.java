package com.pln.database.service;

import com.google.gson.Gson;
import com.pln.database.model.Power;
import com.pln.database.repositories.PowerDao;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public class PowerService {
    private PowerDao powerDao;
    private EntityManager entityManager;

    public PowerService() {
    }

    public void connectJPA() {
        this.entityManager = Persistence
                .createEntityManagerFactory("pln-api")
                .createEntityManager();
        powerDao = new PowerDao(entityManager);
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

    public int addPower(String register) {
        Power power = new Gson().fromJson(register, Power.class);
        connectJPA();
        int res = powerDao.addPower(power);
//        System.out.println("PowerService1_1: " + res);
        try {
            commitJPA(entityManager);
            return res;
        } catch (Exception e) {
//            e.printStackTrace();
//            System.out.println("PowerService1_2");
            return 0;
        }
    }

    public int editPower(String register) {
        Power power = new Gson().fromJson(register, Power.class);
        connectJPA();
        int res = powerDao.editPower(power);
        try {
            commitJPA(entityManager);
            return res;
        } catch (Exception e) {
            return 0;
        }
    }

    public int deletePower(String id_power) {
//        Power power = new Gson().fromJson(register, Power.class);
        connectJPA();
        int res = powerDao.deletePower(Integer.parseInt(id_power));
        try {
            commitJPA(entityManager);
            return res;
        } catch (Exception e) {
            return 0;
        }
    }
}
