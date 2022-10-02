package internet.com.controller;


import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import internet.com.dto.employee_dto.EmployeeDTO;
import internet.com.dto.employee_dto.IEmployeeDTO;
import internet.com.entity.employee.Employee;
import internet.com.entity.user.AppUser;
import internet.com.services.employee.IEmployeeService;
import internet.com.services.employee.IPositionService;
import internet.com.services.user.IUserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.lang.reflect.Array;

@RestController
@RequestMapping("/employee")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class EmployeeController {
    @Autowired
    private IEmployeeService employeeService;

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private IUserService iUserService;

    @Autowired
    private IPositionService positionService;

    /**
     * Create by : TrungND
     * Date created: 09/08/2022
     * function: Add employee
     */
    @PostMapping("/create")
    public ResponseEntity<?> createEmployee(@Valid @RequestBody EmployeeDTO employeeDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity(bindingResult.getAllErrors(), HttpStatus.BAD_REQUEST);
        }
        Employee employee = modelMapper.map(employeeDTO, Employee.class);
        employeeService.save(employee);
        return new ResponseEntity<>(employee, HttpStatus.CREATED);
    }


    /**
     * Create by : TrungND
     * Date created: 09/08/2022
     * function: find by id employee
     */
    @GetMapping("/id/{id}")
    public ResponseEntity<Employee> findById(@PathVariable Integer id) {
        Employee employee = employeeService.findByIdEmployee(id);
        if (employee == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(employee, HttpStatus.OK);
    }

    /**
     * Create by : TrungND
     * Date created: 09/08/2022
     * function: Update Employee by Id
     */
    @PatchMapping("/edit/{id}")
    public ResponseEntity<?> updateEmployee(@Valid @RequestBody EmployeeDTO employeeDTO, @PathVariable Integer id, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            Employee employeeObj = modelMapper.map(employeeDTO, Employee.class);
            employeeObj.setId(id);
            employeeService.update(employeeObj);
            AppUser appUser = iUserService.findByUsername(employeeDTO.getAppUser().getUsername()).get();
            appUser.setPassword(employeeDTO.getAppUser().getPassword());
            iUserService.updateUser(appUser);
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }

    /**
     * Create by LongNB
     * Date create: 09/08/2022
     * function: findAll employee
     */



    @CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
    @GetMapping("/{page}")

    public ResponseEntity<?> getAll(@PathVariable("page") int page, @RequestParam(name = "code") String code,
                                    @RequestParam(name = "name") String name, @RequestParam(name = "workf") String workf,
                                    @RequestParam(name = "workt") String workt, @RequestParam(name = "dobfrom") String dobfrom,
                                    @RequestParam(name = "dobend") String dobend, @RequestParam(name = "pid") String pid,
                                    @RequestParam(name = "address") String address
    ) {
        Page<IEmployeeDTO> findAllByCommune = employeeService.getAll(code, name, workf, workt, dobfrom, dobend, pid, address, PageRequest.of(page, 5));
        Page<IEmployeeDTO> findAllByDistrict = employeeService.getAllByDistrict(code, name, workf, workt, dobfrom, dobend, pid, address, PageRequest.of(page, 5));
        Page<IEmployeeDTO> findAllByProvince = employeeService.getAllByProvince(code, name, workf, workt, dobfrom, dobend, pid, address, PageRequest.of(page, 5));

        if (findAllByCommune.getTotalElements() != 0) {
            return new ResponseEntity<>(findAllByCommune, HttpStatus.OK);
        }
        if (findAllByDistrict.getTotalElements() != 0) {
            return new ResponseEntity<>(findAllByDistrict, HttpStatus.OK);
        }

        return new ResponseEntity<>(findAllByProvince, HttpStatus.OK);
    }

    /**
     * Create by LongNB
     * Date create: 09/08/2022
     * function: delete employee
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteEmployee(@PathVariable("id") Integer id) {
        employeeService.deleteEmployee(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * Create by LongNB
     * Date create: 09/08/2022
     * function: findAll position
     */
    @GetMapping("/position")
    public ResponseEntity<?> getAllPosition() {
        return new ResponseEntity<>(positionService.positionList(), HttpStatus.OK);
    }





}
