package com.pln.database.repositories;

import com.pln.database.model.Power;
import com.pln.database.model.Token;
import com.pln.database.model.ViewToken;
import com.pln.database.model.Voucer;
import com.pln.util.Util;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.swing.text.View;

public class TokenDao {
    private EntityManager entityManager;
    private EntityTransaction entityTransaction;

    public TokenDao(EntityManager entityManager) {
        this.entityManager = entityManager;
        this.entityTransaction = this.entityManager.getTransaction();
    }

    public int buyToken(Token token) {
        token.setStatus("1");
        token.setStatus_redeem("non");
        token.setNo_token(Util.generateToken());
        entityManager.persist(token);
        return 1;
    }

    public int redeemToken(String token) {
        System.out.println("isiNoToken: "+token.substring(1,20));
        String query = "UPDATE Token SET status_redeem='redeem' WHERE no_token LIKE :def";
        Query query1 = entityManager.createQuery(query);
        query1.setParameter("def","%" + token.substring(1,20) + "%");
        return query1.executeUpdate();
    }

    public double getToken(String no_pelanggan){
        ViewToken token = entityManager.find(ViewToken.class,no_pelanggan);
        return token.getToken();
    }
}
