package internet.com.dto.customer_dto;

import javax.validation.constraints.Pattern;

public class EmailDTO {
    private Integer id;

    @Pattern(regexp = "^[A-Za-z0-9]+@[A-Za-z0-9]+(\\.[A-Za-z0-9]+){1,2}$",
             message = "Nhập sai định dạng email")
    private String email;

    public EmailDTO(Integer id, String email) {
        this.id = id;
        this.email = email;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
