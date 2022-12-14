package internet.com.dto.customer_dto;

import javax.validation.constraints.NotEmpty;

public class UserDTO {
    private Integer id;

    @NotEmpty(message = "Không được để trống")
    private String userName;

    public UserDTO() {
    }

    public UserDTO(Integer id, @NotEmpty(message = "Không được để trống") String userName) {
        this.id = id;
        this.userName = userName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
