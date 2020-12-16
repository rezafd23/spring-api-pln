package com.pln.database.repositories;

import com.pln.database.model.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class UserDao {
    private EntityManager entityManager;
    private EntityTransaction entityTransaction;

    public UserDao(EntityManager entityManager) {
        this.entityManager = entityManager;
        this.entityTransaction = entityManager.getTransaction();
    }

    public int addUser(User user){
        entityManager.persist(user);
        return user.getId_user();
    }

    public int editUser(User user){
        User user1 = entityManager.find(User.class,user.getNo_ktp());
        user1.setNama_user(user.getNama_user());
        user1.setNo_hp(user.getNo_hp());
        entityManager.merge(user1);
        return 1;
    }

    public int deleteUser(User user){
        User user1 = entityManager.find(User.class,user.getNo_ktp());
        user1.setStatus("0");
        entityManager.merge(user1);
        return 1;
    }
}
