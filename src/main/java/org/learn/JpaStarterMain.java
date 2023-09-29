package org.learn;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.Date;
import java.util.List;

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

        PayStub payStub1 = new PayStub();
        payStub1.setPaystubStartDate(new Date());
        payStub1.setPaystubEndDate(new Date());
        payStub1.setSalary(1000.00F);
        payStub1.setEmployee(employee);

        PayStub payStub2 = new PayStub();
        payStub2.setPaystubStartDate(new Date());
        payStub2.setPaystubEndDate(new Date());
        payStub2.setSalary(2000.00F);
        payStub2.setEmployee(employee);

        employee.setPayStubs(List.of(payStub1, payStub2));

        // employee is part of both the groups while employee2 is part of first group
        Employee employee2 = new Employee();
        employee2.setName("Bar Baz");
        employee2.setDob(new Date());
        employee2.setType(EmployeeType.FULL_TIME);

        EmailGroup emailGroup1 = new EmailGroup();
        emailGroup1.setName("Company water cooler discussions");
        emailGroup1.addEmployee(employee); // associating employee to first group
        employee.addEmailGroup(emailGroup1);
        employee2.addEmailGroup(emailGroup1); // associating employee2 to first group only

        EmailGroup emailGroup2 = new EmailGroup();
        emailGroup2.setName("Engineering");
        emailGroup2.addEmployee(employee2);
        employee.addEmailGroup(emailGroup2); // associating employee to second group


        // 1. Create entityManagerFactory for persistenceUnitName configured in persistence.xml
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("persistence-test");

        // 2. Create entityManager instance using the factory
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        // 3. Get and begin transaction
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        // 4. Save/persist the entity
        entityManager.persist(employee);
        entityManager.persist(employee2);

        entityManager.persist(card1);

        entityManager.persist(payStub1);
        entityManager.persist(payStub2);

        entityManager.persist(emailGroup1);
        entityManager.persist(emailGroup2);

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
