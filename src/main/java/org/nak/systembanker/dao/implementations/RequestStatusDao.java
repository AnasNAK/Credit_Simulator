package org.nak.systembanker.dao.implementations;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import org.nak.systembanker.config.EntityManagerFactoryConfig;
import org.nak.systembanker.dao.contracts.RequestStatusContract;
import org.nak.systembanker.entities.RequestStatus;

public class RequestStatusDao implements RequestStatusContract {

    private EntityManagerFactory ENTITY_MANAGER_FACTORY;
    public RequestStatusDao() {
        ENTITY_MANAGER_FACTORY = EntityManagerFactoryConfig.getEntityManagerFactory();
    }

    @Override
    public RequestStatus save(RequestStatus requestStatus) {

        EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
       try{
           entityTransaction.begin();
           entityManager.persist(requestStatus);
           entityTransaction.commit();
       }catch (Exception e){
           if (entityTransaction.isActive()){
               entityTransaction.rollback();
           }

       }finally {
           entityManager.close();
       }
       return requestStatus;
    }
}
