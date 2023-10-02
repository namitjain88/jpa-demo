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

        TypedQuery<Employee> query = entityManager.createQuery(
                "SELECT emp FROM Employee emp WHERE emp.name LIKE '%Bar%' AND emp.id BETWEEN 1 AND 2 ORDER BY emp.type DESC",
                Employee.class
        );
        List<Employee> employees = query.getResultList();
        employees.forEach(System.out::println);

        entityManager.close();
        entityManagerFactory.close();
    }
}
