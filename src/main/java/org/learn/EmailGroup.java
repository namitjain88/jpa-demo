package org.learn;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "EMAIL_GROUPS")
public class EmailGroup {

    @Id
    @GeneratedValue
    private int id;
    private String name;

    @ManyToMany(mappedBy = "emailGroups", fetch = FetchType.LAZY) // LAZY is default
    private List<Employee> employees = new ArrayList<>();

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

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    // Helper method to add employee to the employees list at the time setting other member variables
    public void addEmployee(Employee employee) {
        this.employees.add(employee);
    }
}
