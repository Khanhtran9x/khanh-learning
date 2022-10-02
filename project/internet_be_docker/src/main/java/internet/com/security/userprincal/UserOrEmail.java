package internet.com.security.userprincal;

import internet.com.dto.user_dto.request.SignInForm;
import internet.com.services.customer.ICustomerService;
import internet.com.services.employee.IEmployeeService;
import internet.com.services.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserOrEmail {

    @Autowired
    ICustomerService iCustomerService;

    @Autowired
    IEmployeeService iEmployeeService;

    @Autowired
    IUserService iUserService;

    /**
     * Create by HoangHN
     * Date create: 13/08/2022
     * Method check Username Or Email of a customer or employee
     * @param signInForm
     * @return
     */
    public SignInForm checkUsernameOrEmail(SignInForm signInForm) {
        SignInForm account = new SignInForm(signInForm.getUsername(),signInForm.getPassword());
        if (iUserService.existsByUsername(account.getUsername())){
            return account;
        }else if (iEmployeeService.findByEmailGetUsername(account.getUsername()) != null){
            account.setUsername(iEmployeeService.findByEmailGetUsername(account.getUsername()));
            return account;
        }else if (iCustomerService.findByEmailGetUsername(account.getUsername()) != null){
            account.setUsername(iCustomerService.findByEmailGetUsername(account.getUsername()));
            return account;
        }
        return account;
    }


    /**
     * Create by HoangHN
     * Date create: 13/08/2022
     * Method get entity can be customer or employee
     * @param username
     * @return
     */
    public Object getEntity(String username) {
        if (iCustomerService.findCustomerByUserName(username).isPresent()){
            return iCustomerService.findCustomerByUserName(username);
        }
        return iEmployeeService.findEmployeeByUserName(username);
    }
}
