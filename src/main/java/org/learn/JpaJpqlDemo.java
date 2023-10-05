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
        /*TypedQuery<Employee> query = entityManager.createQuery(
                "SELECT emp FROM Employee emp WHERE emp.accessCard.isActive = true",
                Employee.class
        );
        List<Employee> employees = query.getResultList();
        employees.forEach(System.out::println);*/

        // Fetching single field of different data type e.g. just employee name (String) or employee id (Integer)
        TypedQuery<String> empNameQuery = entityManager.createQuery("SELECT e.name FROM Employee e", String.class);
        TypedQuery<Integer> empIdQuery = entityManager.createQuery("SELECT e.id FROM Employee e", Integer.class);
        List<String> empNameList = empNameQuery.getResultList();
        List<Integer> empIdList = empIdQuery.getResultList();
        empNameList.forEach(System.out::println);
        empIdList.forEach(System.out::println);

        // Fetching multiple fields e.g. just employee name, access card issued at
        TypedQuery<Object[]> empCardIssuedAtQuery = entityManager.createQuery("SELECT e.name, e.accessCard.id FROM Employee e", Object[].class);
        List<Object[]> empCardIssuedAtQueryResultList = empCardIssuedAtQuery.getResultList();
        empCardIssuedAtQueryResultList.forEach(e -> System.out.println(e[0] + ", " + e[1]));

        entityManager.close();
        entityManagerFactory.close();
    }
}
