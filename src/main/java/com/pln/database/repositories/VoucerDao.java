package com.pln.database.repositories;

import com.pln.database.model.Voucer;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

public class VoucerDao {
    private EntityManager entityManager;
    private EntityTransaction entityTransaction;

    public VoucerDao(EntityManager entityManager) {
        this.entityManager = entityManager;
        this.entityTransaction = this.entityManager.getTransaction();
    }

    public int addVoucer(Voucer voucer){
        entityManager.persist(voucer);
        return voucer.getId_voucer();
    }
    public int editVoucer(Voucer voucer){
        Voucer voucer1 = entityManager.find(Voucer.class,voucer.getId_voucer());
        voucer1.setHarga(voucer.getHarga());
        entityManager.merge(voucer1);
        return 1;
    }

    public int deleteVoucer(Voucer voucer){
        Voucer voucer1 = entityManager.find(Voucer.class,voucer.getId_voucer());
        voucer1.setStatus("0");
        entityManager.merge(voucer1);
        return 1;
    }
    public List<Voucer> getVoucer(){
        Voucer voucer = new Voucer();
        voucer.setStatus("1");
        String select = "SELECT id_voucer,voucer,harga FROM Voucer WHERE status=:status";
        Query query = entityManager.createQuery(select);
        query.setParameter("status","1");
        return query.getResultList();
    }
}
