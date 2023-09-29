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

        System.out.println("Before finding EmailGroup *****************");
        EmailGroup emailGroup = entityManager.find(EmailGroup.class, 5);
        System.out.println("Before accessing employees *****************");
        System.out.println(emailGroup.getEmployees());
    }
}
