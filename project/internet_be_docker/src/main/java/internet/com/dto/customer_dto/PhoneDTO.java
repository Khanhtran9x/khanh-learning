package internet.com.dto.customer_dto;

import javax.validation.constraints.Pattern;

public class PhoneDTO {
    private Integer id;

    @Pattern(regexp = "^[0-9]{10,12}$", message = "Số điện thoại bắt đầu từ 0 và có 9 số")
    private String phone;

    public PhoneDTO() {
    }

    public PhoneDTO(Integer id, @Pattern(regexp = "^(0|84+)(3[2-9]|5[6|8|9]|7[0|6-9]|8[0-6|8|9]|9[0-4|6-9])[0-9]{7}$", message = "Số điện thoại bắt đầu từ 0 và có 9 số") String phone) {
        this.id = id;
        this.phone = phone;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
