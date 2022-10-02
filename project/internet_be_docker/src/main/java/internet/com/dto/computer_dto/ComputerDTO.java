package internet.com.dto.computer_dto;

import internet.com.entity.computer.ComputerType;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class ComputerDTO{
    private Integer id;

    @NotBlank
    @Pattern(regexp = "^(CP)[0-9]{2,4}$", message = "Code phải có dạng CPXXX")
    private String code;

    private Integer status;

    @NotBlank(message = "Không được để trống.")
    @Pattern(regexp = "^[A-Z][0-9]{4}$", message = "Vị trí phải có định dạng AXXX")
    private String location;

    @NotNull(message = " không được bỏ trống,phải trước ngày hiện tại")
    private String startUsedDate;

    @NotBlank(message = "Không được để trống")
    @Length(min = 3, max = 20, message = "Tối thiểu 3 ký tự và lớn nhất 20 ký tự.")
    @Pattern(regexp = "^\\w+( \\w+)*$",message = "Vui lòng nhập đúng định dạng.")
    private String configuration;

    @Length(min = 1, max = 20, message = "Tối thiểu 3 ký tự và lớn nhất 20 ký tự.")
    @NotBlank(message = "Không được để trống")
    private String manufacturer;

    private Integer deleteStatus;

    @NotBlank(message = "Không được để trống")
    private String warranty;

    private ComputerType computerType;

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getStartUsedDate() {
        return startUsedDate;
    }

    public void setStartUsedDate(String startUsedDate) {
        this.startUsedDate = startUsedDate;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getDeleteStatus() {
        return deleteStatus;
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
