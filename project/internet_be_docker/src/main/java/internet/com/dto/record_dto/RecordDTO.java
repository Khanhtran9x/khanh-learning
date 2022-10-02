package internet.com.dto.record_dto;

import internet.com.entity.computer.Computer;
import internet.com.entity.customer.Customer;


public class RecordDTO {

    private Integer id;
    private String startTime;
    private String endTime;
    private Customer customer;
    private Computer computer;

    public RecordDTO() {
    }

    public RecordDTO(Integer id, String startTime, String endTime, Customer customer, Computer computer) {
        this.id = id;
        this.startTime = startTime;
        this.endTime = endTime;
        this.customer = customer;
        this.computer = computer;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Computer getComputer() {
        return computer;
    }

    public void setComputer(Computer computer) {
        this.computer = computer;
    }
}
