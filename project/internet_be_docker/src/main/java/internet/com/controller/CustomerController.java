package internet.com.controller;

import internet.com.dto.customer_dto.CustomerDTO;
import internet.com.dto.customer_dto.EmailDTO;
import internet.com.dto.customer_dto.PhoneDTO;
import internet.com.dto.customer_dto.UserDTO;
import internet.com.dto.customer_dto.ICustomerDTO;
import internet.com.entity.customer.Customer;
import internet.com.entity.user.AppUser;
import internet.com.services.customer.ICustomerService;
import internet.com.services.user.IUserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/customer")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CustomerController {

    @Autowired
    private ICustomerService customerService;

    @Autowired
    private IUserService userService;
    @Autowired
    private IUserService iUserService;

    @Autowired
    private ModelMapper modelMapper;


    /**
     * Created by: HaoNH
     * Date Created: 09/08/2022
     * method save customer
     *
     * @param customerDTO
     * @param bindingResult
     * @return
     */
    @PostMapping("")
    public ResponseEntity<?> saveCustomer(@Valid @RequestBody CustomerDTO customerDTO,
                                          BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            List<FieldError> errors = bindingResult.getFieldErrors();
            Map<String, String> errorList = new LinkedHashMap<>();
            for (FieldError item : errors) {
                String field = item.getField();
                String msg = item.getDefaultMessage();
                errorList.put(field, msg);
            }
            return new ResponseEntity<>(errorList, HttpStatus.BAD_REQUEST);
        }
        customerService.saveCustomer(customerDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    /**
     * Created by: DuyNT
     * Date Created: 10/08/2022
     * load customer info from database by id parameter
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public ResponseEntity<Customer> findById(@PathVariable("id") Integer id) {
        Optional<Customer> customer = this.customerService.findCustomerById(id);
        if (!customer.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(customer.get(), HttpStatus.OK);
    }

    /**
     * Created by: TrungTHQ
     * Date Created: 10/08/2022
     * load customers from database by id parameter
     */
    @GetMapping("/searchCustomer")
    public ResponseEntity<?> searchByAddress(@RequestParam("address") String address,
                                             @RequestParam("name") String name,
                                             @RequestParam("activeStatus") String activeStatus,
                                             @RequestParam("starDay") String starDay,
                                             @RequestParam("endDay") String endDay,
                                             @RequestParam(value = "page") int p) {
        Page<ICustomerDTO> list = customerService.searchCustomerByProvince(address, name, activeStatus, starDay, endDay, PageRequest.of(p, 5));
        Page<ICustomerDTO> list1 = customerService.searchCustomerByDistrict(address, name, activeStatus, starDay, endDay, PageRequest.of(p, 5));
        Page<ICustomerDTO> list2 = customerService.searchCustomerByCommune(address, name, activeStatus, starDay, endDay, PageRequest.of(p, 5));
        if (list.getTotalElements() != 0) {
            return new ResponseEntity<>(list, HttpStatus.OK);
        }
        if (list1.getTotalElements() != 0) {
            return new ResponseEntity<>(list1, HttpStatus.OK);
        }
        return new ResponseEntity<>(list2, HttpStatus.OK);
    }

    /**
     * Created by: TrungTHQ
     * Date Created: 13/08/2022
     * delete customer by id
     */

    @DeleteMapping("/deleteCustomerBy")
    public ResponseEntity<?> deleteCustomer(@RequestParam("id") Integer id) {
        customerService.deleteCustomerById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * Created by: CuongTM
     * Date Created: 13/08/2022
     *
     * @param id
     * @return
     */
    @GetMapping("getCustomer/{id}")
    public ResponseEntity<Object> getCustomerById(@PathVariable("id") Integer id) {
        Optional<Customer> customer = this.customerService.findCustomerById(id);
        if (!customer.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        CustomerDTO customerDTO = new CustomerDTO(
                customer.get().getId(),
                customer.get().getName(),
                customer.get().getDateOfBirth(),
                new EmailDTO(customer.get().getId(), customer.get().getEmail()),
                new PhoneDTO(customer.get().getId(), customer.get().getPhoneNumber()),
                new UserDTO(customer.get().getId(), customer.get().getUser().getUsername()),
                customer.get().getUser().getPassword(),
                customer.get().getCommune(),
                customer.get().getActiveStatus(),
                customer.get().getRemainingTime(),
                customer.get().getDeleteStatus()
        );

        return new ResponseEntity<>(customerDTO, HttpStatus.OK);
    }

    /**
     * Created by:CuongTM
     * Date Created:
     * 11 / 08 / 2022
     *
     * @param id
     * @param customerDTO
     * @param bindingResult
     * @return
     */

    @PatchMapping("/{id}")
    public ResponseEntity<?> updateCustomer(@PathVariable Integer id, @RequestBody @Valid CustomerDTO
            customerDTO, BindingResult bindingResult) {
        System.out.println(customerDTO.toString());
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Customer customerEdit = customerService.findCustomerById(id).get();
        if (customerEdit.getId() == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        customerEdit = modelMapper.map(customerDTO, Customer.class);
        customerEdit.getUser().setPassword(customerDTO.getPassword());
        customerService.update(customerEdit);

        return new ResponseEntity<>(customerDTO, HttpStatus.OK);

    }

    @GetMapping("/checkUserName/{userName}")
    public ResponseEntity<?> checkUserName(@PathVariable("userName") String userName) {
        return new ResponseEntity<>(userService.existsByUsername(userName), HttpStatus.OK);
    }

    @GetMapping("/checkUserNameInEdit/{userName}")
    public ResponseEntity<?> checkUserNameInEdit(@PathVariable("userName") String userName, @PathVariable("id") Integer id) {
        return new ResponseEntity<>(userService.existsByUsernameInEdit(userName, id), HttpStatus.OK);
    }

    @GetMapping("/checkEmail/{email}")
    public ResponseEntity<?> checkEmail(@PathVariable("email") String email) {
        return new ResponseEntity<>(customerService.existsEmail(email), HttpStatus.OK);
    }

    @GetMapping("/checkPhone/{phone}")
    public ResponseEntity<?> checkPhone(@PathVariable("phone") String phone) {
        return new ResponseEntity<>(customerService.existsPhoneNumber(phone), HttpStatus.OK);
    }

    /**
     * Create by HoangHN
     * Date create: 16/08/2022
     * method set Remaining Time of customer
     *
     * @param id
     * @return
     */

    @GetMapping("/setOutOfTime")
    public ResponseEntity<?> setOutOfTime(@RequestParam Integer id, @RequestParam Integer remaining) {
        System.out.println(id);
        System.out.println(remaining);
        customerService.setOutOfTime(id,remaining);
        Map<String,String> map = new HashMap<>();
        map.put("status","Thành công");
        return new ResponseEntity<>(map,HttpStatus.OK);
    }

    /**
     * Create by HoangHN
     * Date create: 16/08/2022
     * method set Remaining Time of customer
     *
     * @param id
     * @return
     */
    @GetMapping("getRemainingTime/{id}")
    public ResponseEntity<?> getRemainingTime(@PathVariable("id") Integer id) {
        Integer remaining = customerService.getRemainingTime(id);
        Map<String,Integer> map = new HashMap<>();
        map.put("remaining_time",remaining);
        return new ResponseEntity<>(map,HttpStatus.OK);
    }

    @PatchMapping("update/{id}")
    public ResponseEntity<?> updateCustomerInfo(@PathVariable Integer id, @RequestBody @Valid CustomerDTO
            customerDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>("Once time again and winning", HttpStatus.BAD_REQUEST);
        }
        Customer customerEdit = customerService.findCustomerById(id).get();
        if (customerEdit == null) {
            return new ResponseEntity<>("Khách hàng không tồn tại.", HttpStatus.NOT_FOUND);
        }
        customerEdit = modelMapper.map(customerDTO, Customer.class);
        customerEdit.getUser().setPassword(customerDTO.getPassword());
        customerEdit.setCommune(customerDTO.getCommune());
        customerService.update(customerEdit);
        return new ResponseEntity<>(customerDTO, HttpStatus.OK);
    }

    @GetMapping("matchesPassword/{password}/{id}")
    public ResponseEntity<?> matchesPassword(@PathVariable("password") String password, @PathVariable("id") Integer id){
        return new ResponseEntity<>(customerService.matchesPassword(password, id), HttpStatus.OK);
    }
}
