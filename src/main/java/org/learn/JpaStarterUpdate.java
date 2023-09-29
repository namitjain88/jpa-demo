package org.learn;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaStarterUpdate {

    public static void main(String[] args) {

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("persistence-test");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        //Add second emailGroup subscription to employee 2
        Employee employee = entityManager.find(Employee.class, 2);
        EmailGroup emailGroup = entityManager.find(EmailGroup.class, 7);

        // Updating object states per needs
        employee.addEmailGroup(emailGroup);
        emailGroup.addEmployee(employee);

        // Asking JPA to persist the updated objects
        entityManager.persist(employee);
        entityManager.persist(emailGroup);

        transaction.commit();
        entityManager.close();
        entityManagerFactory.close();
    }
}
