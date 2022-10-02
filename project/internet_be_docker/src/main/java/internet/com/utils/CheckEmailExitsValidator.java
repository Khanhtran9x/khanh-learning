package internet.com.utils;

import internet.com.dto.customer_dto.EmailDTO;
import internet.com.entity.customer.Customer;
import internet.com.services.customer.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CheckEmailExitsValidator implements ConstraintValidator<CheckEmailExitsConstraint, EmailDTO> {
    @Autowired
    private ICustomerService customerService;

    @Override
    public void initialize(CheckEmailExitsConstraint constraintAnnotation) {
    }

    /**
     * Create by HaoNH
     * Date create: 11/08/2022
     * method check email is exits in DB
     * @param value
     * @param context
     * @return
     */
    @Override
    public boolean isValid(EmailDTO value, ConstraintValidatorContext context) {
        if (value.getId() == null){
            return !customerService.existsEmail(value.getEmail());
        }
        Customer customer = customerService.findCustomerById(value.getId()).get();
        if (!customer.getEmail().equals(value.getEmail()) ){
            return !customerService.existsEmail(value.getEmail());
        }
        return true;
    }
}
