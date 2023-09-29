package org.learn;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "PAY_STUBS")
public class PayStub {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "START_DATE")
    private Date paystubStartDate;

    @Column(name = "END_DATE")
    private Date paystubEndDate;

    private float salary;

    @ManyToOne(fetch = FetchType.EAGER) // default fetch type for @ManyToOne
    private Employee employee;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getPaystubStartDate() {
        return paystubStartDate;
    }

    public void setPaystubStartDate(Date paystubStartDate) {
        this.paystubStartDate = paystubStartDate;
    }

    public Date getPaystubEndDate() {
        return paystubEndDate;
    }

    public void setPaystubEndDate(Date paystubEndDate) {
        this.paystubEndDate = paystubEndDate;
    }

    public float getSalary() {
        return salary;
    }

    public void setSalary(float salary) {
        this.salary = salary;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    @Override
    public String toString() {
        return "PayStub{" +
                "id=" + id +
                ", paystubStartDate=" + paystubStartDate +
                ", paystubEndDate=" + paystubEndDate +
                ", salary=" + salary +
                ", employee=" + employee +
                '}';
    }
}
