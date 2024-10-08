package org.nak.systembanker.dao.implementations;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import org.nak.systembanker.config.EntityManagerFactoryConfig;
import org.nak.systembanker.dao.contracts.RequestContract;
import org.nak.systembanker.entities.Request;

import java.util.List;
import java.util.Optional;

public class RequestDao implements RequestContract {
    private EntityManagerFactory ENTITY_MANAGER_FACTORY;
    public RequestDao() {
        ENTITY_MANAGER_FACTORY = EntityManagerFactoryConfig.getEntityManagerFactory();
    }

    @Override
    public Request save(Request request) {
       EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
       EntityTransaction transaction = entityManager.getTransaction();
       try{
           transaction.begin();
           entityManager.persist(request);
           transaction.commit();

       }catch(Exception e){
           if (transaction.isActive()){
               transaction.rollback();
           }
           e.printStackTrace();

       }finally {
           entityManager.close();
       }
       return request;
    }

    @Override
    public Optional<Request> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public List<Request> findAll() {
        return List.of();
    }

    @Override
    public boolean delete(Request request) {
        return false;
    }

    @Override
    public Request update(Request request) {
        return null;
    }




}
