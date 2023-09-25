package org.learn;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.Date;

public class JpaStarterMain {

    public static void main(String[] args) {

        // Start of create and persisting employee objects
        Employee employee = new Employee();
        employee.setName("Foo Bar");
        employee.setDob(new Date());
        employee.setType(EmployeeType.CONTRACTOR);

        Employee employee1 = new Employee();
        employee1.setName("Bar Baz");
        employee1.setDob(new Date());
        employee1.setType(EmployeeType.FULL_TIME);

        AccessCard card1 = new AccessCard();
        card1.setIssuedDate(new Date());
        card1.setActive(true);
        card1.setFirmwareVersion("1.0.0");
        employee.setAccessCard(card1);

        AccessCard card2 = new AccessCard();
        card2.setIssuedDate(new Date());
        card2.setActive(false);
        card2.setFirmwareVersion("1.2.0");
        employee1.setAccessCard(card2);


        // Steps involved in persisting an entity object to database
        // 1. Create entityManagerFactory for persistenceUnitName configured in persistence.xml
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("persistence-test");

        // 2. Create entityManager instance using the factory
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        // 3. Get and begin transaction
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        // 4. Save/persist the entity
        entityManager.persist(employee);
        entityManager.persist(employee1);

        entityManager.persist(card1);
        entityManager.persist(card2);

        // 5. Commit transaction
        transaction.commit();

        // 6. Closing persistence context
        entityManager.close();
        entityManagerFactory.close();
        // End of create and persisting employee objects

        /*// Start of deleting an existing employee object
        // 1. Create entityManagerFactory for persistenceUnitName configured in persistence.xml
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("persistence-test");

        // 2. Create entityManager instance using the factory
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        // 3. Reading an entity data from db
        Employee employee = entityManager.find(Employee.class, 1);

        // 4. Get and begin transaction
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        // 5. Remove the entity
        entityManager.remove(employee);

        // 6. Commit transaction
        transaction.commit();

        // 7. Closing persistence context
        entityManager.close();
        entityManagerFactory.close();
        // End of deleting an existing employee object*/
    }
}
