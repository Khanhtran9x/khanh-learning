package internet.com.entity.computer;

import com.fasterxml.jackson.annotation.JsonBackReference;
import internet.com.entity.record.Record;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.util.Set;


/**
 * Created by: PhucNQ
 * Date created: 09/08/2022
 * Entity: Computer
 */

@Entity(name = "computer")
public class Computer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "computer_code")
    private String code;

    @Column(name = "active_status")
    private Integer status;

    @Column
    private String location;

    @Column(name = "start_used_date", columnDefinition = "DATE")
    private String startUsedDate;

    @Column(name = "configuration")
    private String configuration;

    @Column
    private String manufacturer;

    @Column(name = "delete_status")
    private Integer deleteStatus;

    @Column
    private String warranty;

    @ManyToOne
    @JoinColumn(name = "typeId", referencedColumnName = "id")
    private ComputerType computerType;


    @OneToMany(mappedBy = "computer")
    @JsonBackReference
    private Set<Record> records;

    public Set<Record> getRecords() {
        return records;
    }

    public void setRecords(Set<Record> records) {
        this.records = records;
    }

    public Integer getDeleteStatus() {
        return deleteStatus;
    }

    public String getStartUsedDate() {
        return startUsedDate;
    }

    public void setStartUsedDate(String startUsedDate) {
        this.startUsedDate = startUsedDate;
    }

    public void setDeleteStatus(Integer deleteStatus) {
        this.deleteStatus = deleteStatus;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getConfiguration() {
        return configuration;
    }

    public void setConfiguration(String configuration) {
        this.configuration = configuration;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getWarranty() {
        return warranty;
    }

    public void setWarranty(String warranty) {
        this.warranty = warranty;
    }

    public ComputerType getComputerType() {
        return computerType;
    }

    public void setComputerType(ComputerType computerType) {
        this.computerType = computerType;
    }
}
