package org.learn;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Meant to demo read-only operations
 */
public class JpaStarterRead {

    public static void main(String[] args) {

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("persistence-test");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        /*System.out.println("Before fetching Employee******************");
        Employee employee = entityManager.find(Employee.class, 1);
        System.out.println(employee);*/

        System.out.println("Before reading PayStubs from employee ******************");
        PayStub payStub = entityManager.find(PayStub.class, 3);
        System.out.println(payStub);

    }
}
