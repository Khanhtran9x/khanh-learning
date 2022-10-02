package internet.com.services.employee;

import internet.com.dto.employee_dto.IEmployeeDTO;
import internet.com.entity.employee.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface IEmployeeService {
    /**
     *Create by TrungND
     * Date create: 09/08/2022
     * function: save employee
     */
    void save(Employee employee);

    /**
     *Create by TrungND
     * Date create: 09/08/2022
     * function: Update employee
     */
    void update(Employee Employee);

    /**
     *Create by TrungND
     * Date create: 09/08/2022
     * function: findById employee
     */
    Employee findByIdEmployee(int id);

    /**
     *Create by LongNB
     * Date create: 09/08/2022
     * function: delete employee
     */

    void deleteEmployee( Integer id);
    /**
     *Create by LongNB
     * Date create: 09/08/2022
     * function: findAll employee
     */
    Page<IEmployeeDTO> getAll(String code,
                              String name,
                              String from,
                              String end,
                              String dobfrom,
                              String dobend,
                              String pId,
                              String address,
                              Pageable pageable);

    Page<IEmployeeDTO> getAllByDistrict(String code,
                                        String name,
                                        String from,
                                        String end,
                                        String dobfrom,
                                        String dobend,
                                        String pId,
                                        String address,
                                        Pageable pageable);

    Page<IEmployeeDTO> getAllByProvince(String code,
                                        String name,
                                        String from,
                                        String end,
                                        String dobfrom,
                                        String dobend,
                                        String pId,
                                        String address,
                                        Pageable pageable);

    String findByEmailGetUsername(String email);

    Optional<Employee> findEmployeeByUserName(String username);
}
