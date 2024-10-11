package org.nak.systembanker.dao.implementations;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import org.nak.systembanker.config.EntityManagerFactoryConfig;
import org.nak.systembanker.dao.contracts.RequestContract;
import org.nak.systembanker.entities.Request;

import java.time.LocalDate;
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

        EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        Request request= null ;
        try{
            transaction.begin();
            request = entityManager.find(Request.class,id);
            transaction.commit();
        }catch (Exception e){
            if (transaction.isActive()){
                transaction.rollback();
            }
            e.printStackTrace();

        }finally {
            entityManager.close();
        }
        return Optional.ofNullable(request);

    }

    @Override
    public List<Request> findAll() {
        EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        List requests = null;
        try {
            transaction.begin();
            requests = entityManager.createQuery("select r from Request r join fetch r.requestStatuses p join fetch p.status s order by p.dateInsert").getResultList();

        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            entityManager.close();
        }
        return requests;
    }

    @Override
    public boolean delete(Request request) {
      EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
      EntityTransaction entityTransaction = entityManager.getTransaction();
      boolean isDeleted = false;
      try{
          entityTransaction.begin();
          Request managedRequest = entityManager.find(Request.class , request.getId());
          if (managedRequest != null){
              entityManager.remove(managedRequest);
              entityTransaction.commit();
              isDeleted = true;

          }else {
              entityTransaction.rollback();
          }

      }catch (Exception e){
          if (entityTransaction.isActive()){
              entityTransaction.rollback();
          }
          e.printStackTrace();
      }finally{
          entityManager.close();
      }
      return isDeleted;
    }

    @Override
    public Request update(Request request) {
     EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
     EntityTransaction entityTransaction = entityManager.getTransaction();
     Request updatedRequest = null ;
     try{
         entityTransaction.begin();
         updatedRequest = entityManager.merge(request);
         entityTransaction.commit();
     }catch (Exception e){
         if (entityTransaction.isActive()){
             entityTransaction.rollback();
         }
   e.printStackTrace();

     }finally {
         entityManager.close();
     }
     return updatedRequest;
    }

    @Override
    public List<Request> findRequestByDate(LocalDate date) {

        EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        List requests = null;
        try {
            entityTransaction.begin();

            requests = entityManager.createQuery("select r from Request r join fetch r.requestStatuses p join fetch p.status s where r.hireDate = :hireDate")
                    .setParameter("hireDate", date)
                    .getResultList();

            entityTransaction.commit();
        } catch (Exception e) {
            if (entityTransaction.isActive()) {
                entityTransaction.rollback();
            }
            e.printStackTrace();
        } finally {
            entityManager.close();
        }
        return requests;
    }



}
