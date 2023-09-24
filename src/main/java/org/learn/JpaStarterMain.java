package org.learn;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaStarterMain {

    public static void main(String[] args) {
        Employee employee = new Employee();
        employee.setId(1);
        employee.setName("Foo Bar");

        /* Steps involved in persisting an entity object to database */
        // 1. Create entityManagerFactory for persistenceUnitName configured in persistence.xml
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("persistence-test");

        // 2. Create entityManager instance using the factory
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        // 3. Get and begin transaction
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        // 4. Save/persist the entity
        entityManager.persist(employee);

        // 5. Commit transaction
        transaction.commit();
    }
}
