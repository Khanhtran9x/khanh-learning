package internet.com.services.employee.impl;
import internet.com.dto.employee_dto.IEmployeeDTO;
import internet.com.entity.employee.Employee;
import internet.com.repository.employee_repo.IEmployeeRepository;
import internet.com.services.employee.IEmployeeService;
import internet.com.services.user.IRoleService;
import internet.com.services.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmployeeService implements IEmployeeService {

    @Autowired
    private IEmployeeRepository employeeRepository;

    @Autowired
    private IUserService userService;
    @Autowired
    private IRoleService roleService;


    /**
     *Create by TrungND
     * Date create: 10/08/2022
     * function: create employee
     */
    public void save(Employee employee) {
        userService.createUser(employee.getAppUser().getUsername(),employee.getAppUser().getPassword());
        roleService.addNewEmployeeUserRole(employee.getAppUser().getUsername());
        employeeRepository.saveEmployee(employee.getCode(),
                employee.getName(),
                employee.getCommune().getId(),
                employee.getDob(),
                employee.getEmail(),
                employee.getPhone(),
                employee.getPosition().getId(),
                employee.getStartWork(),
                employee.getImage(),
                employee.getSalary(),
                employee.getAppUser().getUsername());
    }
    /**
     *Create by TrungND
     * Date create: 10/08/2022
     * function: update employee
     */
    @Override
    public void update(Employee Employee) {
        employeeRepository.updateEmployee(Employee.getCode(),
                Employee.getName(),
                Employee.getCommune().getId(),
                Employee.getDob(),
                Employee.getEmail(),
                Employee.getPhone(),
                Employee.getPosition().getId(),
                Employee.getStartWork(),
                Employee.getImage(),
                Employee.getSalary(),
                Employee.getAppUser().getUsername(),
                Employee.getId());
    }
    /**
     *Create by TrungND
     * Date create: 10/08/2022
     * function: findByID employee
     */
    @Override
    public Employee findByIdEmployee(int id) {
        return employeeRepository.findByIdEmployee(id);
    }

    /**
     *Create by LongNB
     * Date create: 09/08/2022
     * function: delete employee
     */
    @Override
    public void deleteEmployee(Integer id) {
        employeeRepository.deleteEmployee(id);
    }
    /**
     *Create by LongNB
     * Date create: 09/08/2022
     * function: findAll employee by commune
     */
    @Override
    public Page<IEmployeeDTO> getAll(String code,
                                     String name,
                                     String from,
                                     String end,
                                     String dobfrom,
                                     String dobend,
                                     String pId,
                                     String address,
                                     Pageable pageable) {

        return employeeRepository.findAll(code, name, from, end ,dobfrom, dobend,pId,address, pageable);
    }
    /**
     *Create by LongNB
     * Date create: 11/08/2022
     * function: findAll employee by district
     */
    @Override
    public Page<IEmployeeDTO> getAllByDistrict(String code, String name, String from, String end, String dobfrom, String dobend, String pId, String address, Pageable pageable) {
        return employeeRepository.findAllByDistrict(code, name, from, end, dobfrom, dobend, pId, address, pageable);
    }
    /**
     *Create by LongNB
     * Date create: 11/08/2022
     * function: findAll employee by province
     */
    @Override
    public Page<IEmployeeDTO> getAllByProvince(String code, String name, String from, String end, String dobfrom, String dobend, String pId, String address, Pageable pageable) {
        return employeeRepository.findAllByProvince(code, name, from, end, dobfrom, dobend, pId, address, pageable);
    }

    /**
     * Create by HoangHN
     * Date create: 13/08/2022
     * method find Email get username
     * @param email
     * @return
     */
    @Override
    public String findByEmailGetUsername(String email) {
        return employeeRepository.findByEmailGetUsername(email);
    }

    /**
     * Create by HoangHN
     * Date create: 13/08/2022
     * method find Employee By UserName
     * @param username
     * @return
     */
    @Override
    public Optional<Employee> findEmployeeByUserName(String username) {
        System.out.println(username);
        return employeeRepository.findEmployeeByUserName(username);
    }
}