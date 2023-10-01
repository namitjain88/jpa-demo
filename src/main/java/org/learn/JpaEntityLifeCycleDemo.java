package org.learn;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.Date;

public class JpaEntityLifeCycleDemo {
    public static void main(String[] args) {
        Employee employee1 = new Employee();
        employee1.setName("Employee Three");
        employee1.setDob(new Date());
        employee1.setType(EmployeeType.FULL_TIME);

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("persistence-test");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        // employee1 entity goes into Managed stage from Transient post invoking persist
        System.out.println("********** Before persisting new employee1");
        entityManager.persist(employee1);

        // Forcing JPA to write persistence context to Database using flush()
        //System.out.println("*********** Before flushing persistence context");
        //entityManager.flush();

        // Removing entity and then finding before transaction.commit(); employee1 will be in Removed stage
        // JPA returned null for find operation immediately after remove
        // But actual delete was performed on transaction.commit()
        System.out.println("*********** Before removing employee1");
        entityManager.remove(employee1);

        // employee1 entity moves back to Managed staged from Removed post persist operation
        // JPA does not run any DELETE sql as there is nothing to delete in the end
        System.out.println("********* Moving back removed entity to managed");
        entityManager.persist(employee1);

        // detaching employee1 entity to remove it from managed entities
        // any changes made to a detached entity will not be synced to DB
        // JPA returns null for find operation for a detached entity
        entityManager.detach(employee1);
        employee1.setName("Updated Name");

        System.out.println("********* Before finding employee1");
        Employee employee = entityManager.find(Employee.class, 1);
        System.out.println(employee);

        System.out.println("*********** Before invoking transaction.commit()");
        transaction.commit();
        entityManager.close();
        entityManagerFactory.close();

    }
}
