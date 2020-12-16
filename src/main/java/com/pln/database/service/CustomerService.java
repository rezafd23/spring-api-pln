package com.pln.database.service;

import com.pln.database.model.ViewPelanggan;
import com.pln.database.model.Voucer;
import com.pln.database.repositories.CustomerDao;
import com.pln.database.repositories.VoucerDao;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.util.List;

public class CustomerService {
    private CustomerDao customerDao;
    private EntityManager entityManager;

    public CustomerService() {
    }
    public void connectJPA() {
        this.entityManager = Persistence
                .createEntityManagerFactory("pln-api")
                .createEntityManager();
        customerDao = new CustomerDao(entityManager);
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

    public Object getCustomerById(String no_pelanggan){
        connectJPA();
        Object pelanggan = customerDao.getCustomerById(no_pelanggan);
        commitJPA(entityManager);
        return pelanggan;
    }
}
