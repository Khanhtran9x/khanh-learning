package internet.com.utils;

import internet.com.dto.customer_dto.UserDTO;
import internet.com.entity.customer.Customer;
import internet.com.services.customer.ICustomerService;
import internet.com.services.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UserNameValidator implements ConstraintValidator<CheckUserNameConstraint, UserDTO> {
    @Autowired
    private IUserService userService;
    @Autowired
    private ICustomerService customerService;

    @Override
    public void initialize(CheckUserNameConstraint constraintAnnotation) {
    }

    /**
     * Create by HaoNH
     * Date create: 11/08/2022
     * method check username is exits in DB
     * @param value
     * @param context
     * @return
     */
    @Override
    public boolean isValid(UserDTO value, ConstraintValidatorContext context) {
        if (value.getId() == null){
            return !userService.existsByUsername(value.getUserName());
        }
        Customer customer = customerService.findCustomerById(value.getId()).get();
        if (!customer.getUser().getUsername().equals(value.getUserName())){
            return !userService.existsByUsername(value.getUserName());
        }
        return true;
    }
}
