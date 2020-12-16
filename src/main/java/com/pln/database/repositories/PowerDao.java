package com.pln.database.repositories;

import com.pln.database.model.Power;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TransactionRequiredException;
import java.util.List;

public class PowerDao {
    private EntityManager entityManager;
    private EntityTransaction entityTransaction;

    public PowerDao(EntityManager entityManager) {
        this.entityManager = entityManager;
        this.entityTransaction = this.entityManager.getTransaction();
    }

    public int addPower(Power power) {
        power.setStatus("1");
        entityManager.persist(power);
        return 1;
    }

    public int editPower(Power power) {
        Power power1 = entityManager.find(Power.class, power.getId_daya());
        power1.setTarif(power.getTarif());
        entityManager.merge(power1);
        return 1;
    }

    public int deletePower(int id_daya) {
        Power power1 = entityManager.find(Power.class, id_daya);
        power1.setStatus("0");
        entityManager.merge(power1);
        return 1;
    }

//    public List<Power> getPower(){
//
//    }
}
