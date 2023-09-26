package org.learn;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "ACCESS_CARD")
public class AccessCard {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "ISSUED_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date issuedDate;

    @Column(name = "IS_ACTIVE")
    private boolean isActive;

    @Column(name = "FIRMWARE_VERSION")
    private String firmwareVersion;

    //accessCard is the name of variable used to declare AccessCard in Employee class
    @OneToOne(mappedBy = "accessCard")
    private Employee employee;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getIssuedDate() {
        return issuedDate;
    }

    public void setIssuedDate(Date issuedDate) {
        this.issuedDate = issuedDate;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public String getFirmwareVersion() {
        return firmwareVersion;
    }

    public void setFirmwareVersion(String firmwareVersion) {
        this.firmwareVersion = firmwareVersion;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    @Override
    public String toString() {
        return "AccessCard{" +
                "id=" + id +
                ", issuedDate=" + issuedDate +
                ", isActive=" + isActive +
                ", firmwareVersion='" + firmwareVersion + '\'' +
                '}';
    }
}
