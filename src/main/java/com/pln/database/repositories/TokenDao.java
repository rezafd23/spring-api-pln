package com.pln.database.repositories;

import com.pln.database.model.Power;
import com.pln.database.model.Token;
import com.pln.util.Util;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

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
}
