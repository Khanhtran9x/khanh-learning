package internet.com.dto.user_dto.response;

import internet.com.entity.employee.Employee;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class JWTResponseEmployee {
    private Boolean errorStatus = true;
    private String message = "";
    private Employee employee;
    private String token;
    private Collection<? extends GrantedAuthority> roles;

    public JWTResponseEmployee() {
    }

    public JWTResponseEmployee(Boolean errorStatus, String message, Employee employee, String token, Collection<? extends GrantedAuthority> roles) {
        this.errorStatus = errorStatus;
        this.message = message;
        this.employee = employee;
        this.token = token;
        this.roles = roles;
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

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Collection<? extends GrantedAuthority> getRoles() {
        return roles;
    }

    public void setRoles(Collection<? extends GrantedAuthority> roles) {
        this.roles = roles;
    }
}
