package org.nak.systembanker.dao.implementations;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import org.nak.systembanker.config.EntityManagerFactoryConfig;
import org.nak.systembanker.dao.contracts.StatusContract;
import org.nak.systembanker.entities.Status;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class StatusDao implements StatusContract {

    private EntityManagerFactory ENTITY_MANAGER_FACTORY;
    public StatusDao() {
        ENTITY_MANAGER_FACTORY = EntityManagerFactoryConfig.getEntityManagerFactory();
    }
    @Override
    public List<Status> findAll() {
        EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction entitytransaction = entityManager.getTransaction();
        List listStatus = null;
        try{
            entitytransaction.begin();
            listStatus = entityManager.createQuery("select s from Status s")
                    .getResultList();
            entitytransaction.commit();
        }catch (Exception e){
            if (entitytransaction.isActive()) {
                entitytransaction.rollback();
            }
            e.printStackTrace();
        }finally {
            entityManager.close();
        }
        return listStatus;

    }

        @Override
        public Optional<Status> findByStatus(String statusName) {

            EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
            EntityTransaction entitytransaction = entityManager.getTransaction();
            Status status = null;
            try{
                entitytransaction.begin();
                status = entityManager.createQuery("select s from Status s where s.statusName = :statusName" ,Status.class)
                        .setParameter("statusName",statusName)
                        .getSingleResult();

                entitytransaction.commit();
            }catch (Exception e){
                if (entitytransaction.isActive()) {
                    entitytransaction.rollback();
                }
                e.printStackTrace();
            }finally {
                entityManager.close();
            }

            return Optional.ofNullable(status);
        }
}
