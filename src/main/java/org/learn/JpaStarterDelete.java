package org.learn;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 * Class meant to demo delete operation and cascading effects
 */
public class JpaStarterDelete {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("persistence-test");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        Employee employee = entityManager.find(Employee.class, 1);
        //paystub has many-to-one relationship with employee
        //emailGroup has many-to-many relationship with employee
        //accessCard has one-to-one relationship with employee
        //Ques: what happens to these relationship if you delete employee?
        //Ans: By default, JPA will only delete records maintained in join table for a given employee id i.e. the table used by Employee-EmailGroup.
        //This table does not have any data, it just maintains the mapping of ids from both the related entities. that's the reason JPA assumes it safely delete.
        // Other relationships such paystub, accesscard could not be deleted as they may exist even without employee so JPA rather spits database errors

        // Use cascade to instruct JPA to perform same operation for these relationships as well depending on the configuration
        // e.g. cascade = CascadeType.REMOVE will only work for remove operation

        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        entityManager.remove(employee);

        transaction.commit();
        entityManager.close();
        entityManagerFactory.close();

    }
}
