package org.learn;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

    @OneToOne(fetch = FetchType.EAGER) // default for one-to-one
    private AccessCard accessCard;

    @OneToMany(mappedBy = "employee", fetch = FetchType.LAZY) // default fetch type for @OneToMany
    private List<PayStub> payStubs;

    // default is LAZY; but in our use case Employee may not have that many emailGroups, so it's ok to fetch eagerly
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "EMAIL_GROUP_SUBSCRIPTIONS", joinColumns = @JoinColumn(name = "EMP_ID"),
            inverseJoinColumns = @JoinColumn(name = "SUBSCRIPTION_ID"))
    private List<EmailGroup> emailGroups = new ArrayList<>();

    @Transient
    private String debugString;

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", dob=" + dob +
                ", type=" + type +
                ", accessCard=" + accessCard +
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

    public AccessCard getAccessCard() {
        return accessCard;
    }

    public void setAccessCard(AccessCard accessCard) {
        this.accessCard = accessCard;
    }

    public List<PayStub> getPayStubs() {
        return payStubs;
    }

    public void setPayStubs(List<PayStub> payStubs) {
        this.payStubs = payStubs;
    }

    public List<EmailGroup> getEmailGroups() {
        return emailGroups;
    }

    public void setEmailGroups(List<EmailGroup> emailGroups) {
        this.emailGroups = emailGroups;
    }

    // Helper method to add emailGroup to the emailGroups list at the time of setting other member variables
    public void addEmailGroup(EmailGroup emailGroup) {
        this.emailGroups.add(emailGroup);
    }
}
