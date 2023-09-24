package org.learn;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaStarterMain {

    public static void main(String[] args) {

        // Start of updating existing employee object
        // 1. Create entityManagerFactory for persistenceUnitName configured in persistence.xml
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("persistence-test");

        // 2. Create entityManager instance using the factory
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        // 3. Reading an entity data from db
        Employee employee = entityManager.find(Employee.class, 1);

        // 4. Update the data using setter
        employee.setType(EmployeeType.FULL_TIME);
        System.out.println(employee);

        // 5. Get and begin transaction
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        // 6. Save/persist the entity
        entityManager.persist(employee);

        // 7. Commit transaction
        transaction.commit();

        // 8. Closing persistence context
        entityManager.close();
        entityManagerFactory.close();
        // End of updating existing employee object


        /*// Start of create and persisting employee objects
        Employee employee = new Employee();
        //employee.setId(1);
        employee.setName("Foo Bar");
        employee.setDob(new Date());
        employee.setType(EmployeeType.CONTRACTOR);

        Employee employee1 = new Employee();
        //employee1.setId(2);
        employee1.setName("Bar Baz");
        employee1.setDob(new Date());
        employee1.setType(EmployeeType.FULL_TIME);

        *//* Steps involved in persisting an entity object to database *//*
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

        // 5. Commit transaction
        transaction.commit();

        // 6. Closing persistence context
        entityManager.close();
        entityManagerFactory.close();
        // End of create and persisting employee objects*/
    }
}
