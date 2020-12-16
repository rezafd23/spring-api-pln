package com.pln.database.service;

import com.pln.database.model.Voucer;
import com.pln.database.repositories.PowerDao;
import com.pln.database.repositories.VoucerDao;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.List;

public class VoucerService {
    private VoucerDao voucerDao;
    private EntityManager entityManager;

    public VoucerService() {
    }
    public void connectJPA() {
        this.entityManager = Persistence
                .createEntityManagerFactory("pln-api")
                .createEntityManager();
        voucerDao = new VoucerDao(entityManager);
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

    public List<Voucer> getVoucer(){
        connectJPA();
        List<Voucer> voucerList= voucerDao.getVoucer();
        commitJPA(entityManager);
        return voucerList;
    }
}
