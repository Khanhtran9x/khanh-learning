package internet.com.dto.employee_dto;

import internet.com.entity.customer.Commune;
import internet.com.entity.employee.Position;
import internet.com.entity.user.AppUser;
import internet.com.utils.CheckAge16Constraint;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class EmployeeDTO {
    private Integer id;
    @Pattern(regexp = "^EMP[0-9]{4}$", message = "Nhập sai định dạng")
    private String code;

    @Pattern(regexp = "^[A-ZÀÁẠẢÃÂẦẤẬẨẪĂẰẮẶẲẴÈÉẸẺẼÊỀẾỆỂỄÌÍỊỈĨÒÓỌỎÕÔỒỐỘỔỖƠỜỚỢỞỠÙÚỤỦŨƯỪỨỰỬỮỲÝỴỶỸĐ][a-zàáạảãâầấậẩẫăằắặẳẵèéẹẻẽêềếệểễìíịỉĩòóọỏõôồốộổỗơờớợởỡùúụủũưừứựửữỳýỵỷỹđ]*(?:[ ][A-ZÀÁẠẢÃÂẦẤẬẨẪĂẰẮẶẲẴÈÉẸẺẼÊỀẾỆỂỄÌÍỊỈĨÒÓỌỎÕÔỒỐỘỔỖƠỜỚỢỞỠÙÚỤỦŨƯỪỨỰỬỮỲÝỴỶỸĐ][a-zàáạảãâầấậẩẫăằắặẳẵèéẹẻẽêềếệểễìíịỉĩòóọỏõôồốộổỗơờớợởỡùúụủũưừứựửữỳýỵỷỹđ]*)*$", message = "Không được để trống")
    private String name;

    @Pattern(regexp = "^[A-Za-z0-9]+@[A-Za-z0-9]+(\\.[A-Za-z0-9]+){1,2}$",
            message = "Nhập sai định dạng")
    private String email;

    @Pattern(regexp = "^[0-9]{10}$", message = "Nhập sai định dạng")
    private String phone;

    @NotEmpty(message = "Không được để trống")
    @CheckAge16Constraint
    private String dob;

    @NotEmpty(message = "Không được để trống")
    private String salary;
    private String startWork;
    private String image;

    @NotNull(message = "Không được để trống")
    private Position position;

    @NotNull(message = "Không được để trống")
    private Commune commune;

    private AppUser appUser;
    //
//    @NotEmpty(message = "Không được để trống")
//    @CheckUserNameConstraint
//    private String username;
//
//    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{6,}$", message = "Nhập sai định dạng")


    public EmployeeDTO() {
    }

    public EmployeeDTO(Integer id, String code, String name, String email, String phone, String dob, String salary, String startWork, String image, Position position, Commune commune, AppUser appUser) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.dob = dob;
        this.salary = salary;
        this.startWork = startWork;
        this.image = image;
        this.position = position;
        this.commune = commune;
        this.appUser = appUser;


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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public String getStartWork() {
        return startWork;
    }

    public void setStartWork(String startWork) {
        this.startWork = startWork;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Commune getCommuneDto() {
        return commune;
    }

    public void setCommune(Commune commune) {
        this.commune = commune;
    }

    public Commune getCommune() {
        return commune;
    }

    public AppUser getAppUser() {
        return appUser;
    }

    public void setAppUser(AppUser appUser) {
        this.appUser = appUser;
    }


}
