package org.learn;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;

public class JpaJpqlDemo {
    public static void main(String[] args) {

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("persistence-test");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        // Explicit JOIN: Fetch all employees with active access card using JOIN
        /*TypedQuery<Employee> query = entityManager.createQuery(
                "SELECT emp FROM Employee emp JOIN AccessCard card ON emp.accessCard.id = card.id",
                Employee.class
        );*/

        // Implicit JOIN due to @OneToOne: Fetch all employees with active access card
        TypedQuery<Employee> query = entityManager.createQuery(
                "SELECT emp FROM Employee emp WHERE emp.accessCard.isActive = true",
                Employee.class
        );
        List<Employee> employees = query.getResultList();
        employees.forEach(System.out::println);

        entityManager.close();
        entityManagerFactory.close();
    }
}
