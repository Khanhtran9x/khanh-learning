package internet.com.dto.user_dto.response;

import internet.com.entity.customer.Customer;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class JWTResponseCustomer {
    private Boolean errorStatus = true;
    private String message = "";
    private Customer customer;
    private String token;
    private Integer computerInUse;
    private String computerCode;
    private String startTime;
    private String endTime;

    private Integer recordId;

    private Integer computerId;
    private Collection<? extends GrantedAuthority> roles;

    public JWTResponseCustomer() {
    }

    public JWTResponseCustomer(Boolean errorStatus, String message) {
        this.errorStatus = errorStatus;
        this.message = message;
    }

    public JWTResponseCustomer(Boolean errorStatus, String message, Customer customer, String token, Integer computerInUse, String computerCode, String startTime, String endTime, Integer recordId, Integer computerId, Collection<? extends GrantedAuthority> roles) {
        this.errorStatus = errorStatus;
        this.message = message;
        this.customer = customer;
        this.token = token;
        this.computerInUse = computerInUse;
        this.computerCode = computerCode;
        this.startTime = startTime;
        this.endTime = endTime;
        this.recordId = recordId;
        this.computerId = computerId;
        this.roles = roles;
    }

    public Integer getComputerId() {
        return computerId;
    }

    public void setComputerId(Integer computerId) {
        this.computerId = computerId;
    }

    public Integer getRecordId() {
        return recordId;
    }

    public void setRecordId(Integer recordId) {
        this.recordId = recordId;
    }

    public String getComputerCode() {
        return computerCode;
    }

    public void setComputerCode(String computerCode) {
        this.computerCode = computerCode;
    }

    public Boolean getErrorStatus() {
        return errorStatus;
    }

    public void setErrorStatus(Boolean errorStatus) {
        this.errorStatus = errorStatus;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Integer getComputerInUse() {
        return computerInUse;
    }

    public void setComputerInUse(Integer computerInUse) {
        this.computerInUse = computerInUse;
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

    public Collection<? extends GrantedAuthority> getRoles() {
        return roles;
    }

    public void setRoles(Collection<? extends GrantedAuthority> roles) {
        this.roles = roles;
    }

    @Override
    public String  toString() {
        return "JWTResponseCustomer{" +
                "errorStatus=" + errorStatus +
                ", message='" + message + '\'' +
                ", customer=" + customer +
                ", token='" + token + '\'' +
                ", computerInUse=" + computerInUse +
                ", computerCode='" + computerCode + '\'' +
                ", startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                ", roles=" + roles +
                '}';
    }
}