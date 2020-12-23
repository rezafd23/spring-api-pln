package com.pln.database.repositories;

import com.pln.database.model.Customer;
import com.pln.database.model.ViewPelanggan;
import com.pln.database.model.Voucer;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.util.List;

public class CustomerDao {
    private EntityManager entityManager;
    private EntityTransaction entityTransaction;

    public CustomerDao(EntityManager entityManager) {
        this.entityManager = entityManager;
        this.entityTransaction = this.entityManager.getTransaction();
    }

    public int addCustomer(Customer customer) {
        entityManager.persist(customer);
        return customer.getId_user();
    }

    public Object getCustomerById(String no_pelanggan) {
        String select = "SELECT nama,no_pelanggan,daya,tarif FROM ViewPelanggan WHERE no_pelanggan=:no_pelanggan";
        Query query = entityManager.createQuery(select);
        query.setParameter("no_pelanggan", no_pelanggan);
//        Object result = query.getResultList().get(0);
//        System.out.println("isiOBJECTINI: "+result);
        if (query.getResultList().size()>0) {
            return query.getResultList().get(0);
        } else {
            return "2";
        }
    }
//    public int editUser(Customer customer){
//        Customer custom = entityManager.find(Customer.class,customer.getNo_pelanggan());
//
//
//    }
}
