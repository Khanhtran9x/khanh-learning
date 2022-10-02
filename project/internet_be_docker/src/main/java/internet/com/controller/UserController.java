package internet.com.controller;

import internet.com.dto.user_dto.request.SignInForm;
import internet.com.dto.user_dto.response.JWTResponseCustomer;
import internet.com.dto.user_dto.response.JWTResponseEmployee;
import internet.com.entity.customer.Customer;
import internet.com.entity.employee.Employee;
import internet.com.security.userprincal.UserOrEmail;
import internet.com.services.computer.IComputerService;
import internet.com.services.customer.ICustomerService;
import internet.com.services.employee.IEmployeeService;
import internet.com.services.record.IRecordService;
import internet.com.services.user.impl.RoleServiceImpl;
import internet.com.services.user.impl.UserServiceImpl;
import internet.com.security.jwt.JwtProvider;
import internet.com.security.userprincal.UserPrinciple;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;


@RequestMapping("/api/auth")
@RestController
@CrossOrigin(origins = "*")
public class UserController {
    @Autowired
    UserServiceImpl userService;
    @Autowired
    RoleServiceImpl roleService;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    JwtProvider jwtProvider;
    @Autowired
    UserOrEmail userOrEmail;
    @Autowired
    IRecordService iRecordService;
    @Autowired
    ICustomerService iCustomerService;
    @Autowired
    IComputerService iComputerService;

    @Autowired
    IEmployeeService iEmployeeService;


    /**
     * Create by HoangHN
     * Date create: 09/08/2022
     * function: check login by username or email of customer or employee
     * @param signInForm
     * @return
     */
    @PostMapping(value = "/login")
    public ResponseEntity<?> login(@Valid @RequestBody SignInForm signInForm){
        SignInForm account = userOrEmail.checkUsernameOrEmail(signInForm);
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(account.getUsername(), account.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtProvider.createToken(authentication);
        UserPrinciple userPrinciple = (UserPrinciple) authentication.getPrincipal();

        if (userPrinciple.getAuthorities().toString().contains("CUSTOMER")){
            if (iCustomerService.getRemainingTime(iCustomerService.findCustomerByUserName(account.getUsername()).get().getId()) < 1){
                return new ResponseEntity<>(new JWTResponseCustomer(true,"Tài khoản đã hết giờ, vui lòng nạp thêm"), HttpStatus.BAD_REQUEST);
            }
            if (!iComputerService.findUnusedComputer().isEmpty()){
                JWTResponseCustomer jwtResponseCustomer =  iRecordService.createRecord(iCustomerService.findCustomerByUserName(account.getUsername()).get().getId());
                jwtResponseCustomer.setRoles(userPrinciple.getAuthorities());
                Optional<Customer> customer = iCustomerService.findCustomerByUserName(account.getUsername());
                jwtResponseCustomer.setCustomer(customer.get());
                jwtResponseCustomer.setErrorStatus(false);
                jwtResponseCustomer.setComputerCode(iComputerService.findById(jwtResponseCustomer.getComputerInUse()).getCode());
                jwtResponseCustomer.setToken(jwtProvider.createToken(authentication));
                return ResponseEntity.ok(jwtResponseCustomer);
            }else{
                return new ResponseEntity<>(new JWTResponseCustomer(true,"Hiện không còn máy trống"), HttpStatus.BAD_REQUEST);
            }
        } else {
            JWTResponseEmployee jwtResponseEmployee = new JWTResponseEmployee();
            jwtResponseEmployee.setMessage("Đăng nhập thành công");
            jwtResponseEmployee.setRoles(userPrinciple.getAuthorities());
            jwtResponseEmployee.setToken(token);
            Optional<Employee> employee = iEmployeeService.findEmployeeByUserName(account.getUsername());
            jwtResponseEmployee.setEmployee(employee.get());
            jwtResponseEmployee.setErrorStatus(false);
            return ResponseEntity.ok(jwtResponseEmployee);
        }
    }


}
