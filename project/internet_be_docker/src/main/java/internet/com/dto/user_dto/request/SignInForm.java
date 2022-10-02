package internet.com.dto.user_dto.request;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class SignInForm {

    @NotEmpty(message = "username cannot be blank")
    @Size(min = 6, max = 40, message = "minimum username 6 and maximum 40 characters")
//    @Pattern(regexp = "^[^<>]+$", message = "username cannot contain characters")
    private String username;
    @Size(min = 6, max = 40, message = "minimum username 6 and maximum 30 characters")
    @NotEmpty(message = "password cannot be blank")
    private String password;

    public SignInForm() {
    }

    public SignInForm(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
