package internet.com.entity.customer;

import com.fasterxml.jackson.annotation.JsonBackReference;
import internet.com.entity.record.Record;
import internet.com.entity.user.AppUser;

import javax.persistence.*;
import java.util.Set;

@Entity(name = "customer")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "customer_name")
    private String name;
    @Column(name = "phone_number")
    private String phoneNumber;
    private String dateOfBirth;
    private String email;
    private Integer activeStatus;
    @Column(name = "delete_status")
    private Integer deleteStatus;
    private Integer remainingTime;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_name", referencedColumnName = "user_name")
    private AppUser user;
    @JoinColumn(name = "address_id")
    @ManyToOne(targetEntity = Commune.class)
    private Commune commune;

    @OneToMany(mappedBy = "customer")
    @JsonBackReference
    private Set<Record> records;

    public Customer() {
    }

    public Customer(Integer id, String name, String phoneNumber, String dateOfBirth, String email, Integer activeStatus, Integer deleteStatus, Integer remainingTime, AppUser user, Commune commune) {
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.dateOfBirth = dateOfBirth;
        this.email = email;
        this.activeStatus = activeStatus;
        this.deleteStatus = deleteStatus;
        this.remainingTime = remainingTime;
        this.user = user;
        this.commune = commune;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getActiveStatus() {
        return activeStatus;
    }

    public void setActiveStatus(Integer activeStatus) {
        this.activeStatus = activeStatus;
    }

    public Integer getDeleteStatus() {
        return deleteStatus;
    }

    public void setDeleteStatus(Integer deleteStatus) {
        this.deleteStatus = deleteStatus;
    }


    public Commune getCommune() {
        return commune;
    }

    public void setCommune(Commune commune) {
        this.commune = commune;
    }

    public Integer getRemainingTime() {
        return remainingTime;
    }

    public void setRemainingTime(Integer remainingTime) {
        this.remainingTime = remainingTime;
    }

    public AppUser getUser() {
        return user;
    }

    public void setUser(AppUser user) {
        this.user = user;
    }

    public Set<Record> getRecords() {
        return records;
    }

    public void setRecords(Set<Record> records) {
        this.records = records;
    }
}