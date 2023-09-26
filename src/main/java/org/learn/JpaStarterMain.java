package org.learn;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.Date;

public class JpaStarterMain {

    public static void main(String[] args) {

        // Start of creating, persisting and reading entities
        Employee employee = new Employee();
        employee.setName("Foo Bar");
        employee.setDob(new Date());
        employee.setType(EmployeeType.CONTRACTOR);

        AccessCard card1 = new AccessCard();
        card1.setIssuedDate(new Date());
        card1.setActive(true);
        card1.setFirmwareVersion("1.0.0");
        // setting both-way relationship
        card1.setEmployee(employee);
        employee.setAccessCard(card1);

        // 1. Create entityManagerFactory for persistenceUnitName configured in persistence.xml
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("persistence-test");

        // 2. Create entityManager instance using the factory
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        // 3. Get and begin transaction
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        // 4. Save/persist the entity
        entityManager.persist(employee);

        entityManager.persist(card1);

        // 5. Commit transaction
        transaction.commit();

        // 6. Reading entities from db
        AccessCard accessCard = entityManager.find(AccessCard.class, 2);
        System.out.println(accessCard);

        // 7. Closing persistence context
        entityManager.close();
        entityManagerFactory.close();

        // End of creating, persisting and reading entities
    }
}
