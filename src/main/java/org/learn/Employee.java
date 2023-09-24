package org.learn;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "EMPLOYEE_DATA")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) //default
    private int id;

    @Column(name = "EMP_NAME", length = 100)
    private String name;

    @Temporal(TemporalType.DATE)
    private Date dob;

    @Enumerated(EnumType.STRING)
    private EmployeeType type;

    @Transient
    private String debugString;

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", dob=" + dob +
                ", type=" + type +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public EmployeeType getType() {
        return type;
    }

    public void setType(EmployeeType type) {
        this.type = type;
    }
}
