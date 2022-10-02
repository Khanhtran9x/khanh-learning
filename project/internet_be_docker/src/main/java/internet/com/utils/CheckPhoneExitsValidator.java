package internet.com.utils;

import internet.com.dto.customer_dto.PhoneDTO;
import internet.com.entity.customer.Customer;
import internet.com.services.customer.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CheckPhoneExitsValidator implements ConstraintValidator<CheckPhoneExitsConstraint, PhoneDTO> {
    @Autowired
    private ICustomerService customerService;

    @Override
    public void initialize(CheckPhoneExitsConstraint constraintAnnotation) {

    }

    /**
     * Create by HaoNH
     * Date create: 11/08/2022
     * method check phone number is exits in DB
     * @param value
     * @param context
     * @return
     */
    @Override
    public boolean isValid(PhoneDTO value, ConstraintValidatorContext context) {
        if (value.getId() == null){
            return !customerService.existsPhoneNumber(value.getPhone());
        }
        Customer customer = customerService.findCustomerById(value.getId()).get();
        if (!customer.getPhoneNumber().equals(value.getPhone())){
            return !customerService.existsPhoneNumber(value.getPhone());
        }
        return true;
    }
}
