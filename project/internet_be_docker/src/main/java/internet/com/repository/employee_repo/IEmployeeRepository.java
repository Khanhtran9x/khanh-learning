package internet.com.repository.employee_repo;
import internet.com.dto.employee_dto.IEmployeeDTO;
import internet.com.entity.employee.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
@Transactional
public interface IEmployeeRepository extends PagingAndSortingRepository<Employee, Integer> {

    /**
     * Create by TrungND
     * Date create: 11/08/2022
     * function: create employee
     */
    @Modifying
    @Query(value = "insert into employee (employee_code, employee_name, address_id, date_of_birth," +
            " email, phone, position_id, start_work, image_url, salary, user_name, delete_status) " +
            "values (:employeeCode, :employeeName, :communeId, :dateOfBirth," +
            " :email, :phone, :positionId, :startWork, :imageUrl, :salary, :userName, 0)", nativeQuery = true)
    void saveEmployee(@Param("employeeCode") String employeeCode,
                      @Param("employeeName") String employeeName,
                      @Param("communeId") int communeId,
                      @Param("dateOfBirth") String dateOfBirth,
                      @Param("email") String email,
                      @Param("phone") String phone,
                      @Param("positionId") int positionId,
                      @Param("startWork") String startWork,
                      @Param("imageUrl") String imageUrl,
                      @Param("salary") int salary,
                      @Param("userName") String userName
    );

    /**
     * Create by TrungND
     * Date create: 11/08/2022
     * function: Update employee
     */
    @Modifying
    @Query(value = "update employee set employee_code=:employeeCode," +
            " employee_name=:employeeName," +
            " address_id=:addressId," +
            "date_of_birth=:dateOfBirth, " +
            "email=:email, phone=:phone," +
            " position_id=:positionId," +
            " start_work=:startWork," +
            " image_url=:imageUrl," +
            " salary=:salary," +
            "user_name =:userName " +
            " where id=:id", nativeQuery = true)
    void updateEmployee(@Param("employeeCode") String employeeCode,
                        @Param("employeeName") String employeeName,
                        @Param("addressId") int communeId,
                        @Param("dateOfBirth") String dateOfBirth,
                        @Param("email") String email,
                        @Param("phone") String phone,
                        @Param("positionId") int positionId,
                        @Param("startWork") String startWork,
                        @Param("imageUrl") String imageUrl,
                        @Param("salary") int salary,
                        @Param("userName") String userName,
                        @Param("id") Integer id);

    /**
     * Create by TrungND
     * Date create: 11/08/2022
     * function: findById employee
     */
    @Query(value = "select id, employee_code, employee_name, \n" +
            "            address_id, date_of_birth, \n" +
            "             email, phone, position_id,\n" +
            "             start_work, image_url,\n" +
            "             salary, user_name,delete_status\n" +
            "             from employee where id =:id", nativeQuery = true)
    Employee findByIdEmployee(@Param("id") Integer id);

    /**
     *Create by LongNB
     * Date create: 09/08/2022
     * function: delete employee
     */
    @Modifying
    @Query(value = "update employee set delete_status = 1 where id = :id",nativeQuery = true)
    void deleteEmployee(@Param("id") Integer id);

    @Query(value = "select * from employee where delete_status =0", nativeQuery = true)
    Page<Employee> getAll(Pageable pageable);
    /**
     *Create by LongNB
     * Date create: 09/08/2022
     * function: findAll employee by commune
     */

    @Query(value = "select e.id as id," +
            " e.employee_code as code," +
            " e.employee_name as name," +
            " e.date_of_birth as dob," +
            " e.image_url as img," +
            " e.email as email," +
            " e.phone as phone," +
            " e.salary as salary," +
            " e.delete_status as deleteStatus," +
            " e.start_work as workf," +
            " e.address_id as address," +
            " e.position_id as posi," +
            " e.user_name as userName," +
            " cm.id as cId," +
            " cm.commune_name as cmName," +
            " dis.district_name as disName," +
            " dis.id as dId," +
            " pro.id as pId," +
            " pro.province_name as proName," +
            " p.position_name as posiName" +
            " from employee e join commune cm on e.address_id = cm.id" +
            " join district dis on cm.district_id = dis.id" +
            " join province pro on dis.province_id = pro.id" +
            " join position p on e.position_id = p.id" +
            " where e.delete_status = 0 and (e.start_work between :from and :end) and (e.date_of_birth between :dobfrom and :dobend)" +
            " and p.id like %:pId% and cm.commune_name like %:address% and e.employee_code like %:code% and e.employee_name like %:name%", nativeQuery = true,
            countQuery="select e.id as id," +
                    " e.employee_code as code," +
                    " e.employee_name as name," +
                    " e.date_of_birth as dob," +
                    " e.image_url as img," +
                    " e.email as email," +
                    " e.phone as phone," +
                    " e.salary as salary," +
                    " e.delete_status as deleteStatus," +
                    " e.start_work as workf," +
                    " e.address_id as address," +
                    " e.position_id as posi," +
                    " e.user_name as userName," +
                    " cm.id as cId," +
                    " cm.commune_name as cmName," +
                    " dis.district_name as disName," +
                    " dis.id as dId," +
                    " pro.id as pId," +
                    " pro.province_name as proName," +
                    " p.position_name as posiName" +
                    " from employee e join commune cm on e.address_id = cm.id" +
                    " join district dis on cm.district_id = dis.id" +
                    " join province pro on dis.province_id = pro.id" +
                    " join position p on e.position_id = p.id" +
                    " where e.delete_status = 0 and (e.start_work between :from and :end) and (e.date_of_birth between :dobfrom and :dobend)" +
                    " and p.id like %:pId% and cm.commune_name like %:address% and e.employee_code like %:code% and e.employee_name like %:name%")
    Page<IEmployeeDTO> findAll(@Param("code")String code,@Param("name")String name,@Param("from") String from,
                               @Param("end")String end,@Param("dobfrom")String dobfrom,
                               @Param("dobend")String dobend,@Param("pId")String pId,@Param("address")String address, Pageable pageable);

    /**
     *Create by LongNB
     * Date create: 11/08/2022
     * function: findAll employee by district
     */
    @Query(value = "select e.id as id," +
            " e.employee_code as code," +
            " e.employee_name as name," +
            " e.date_of_birth as dob," +
            " e.image_url as img," +
            " e.email as email," +
            " e.phone as phone," +
            " e.salary as salary," +
            " e.delete_status as deleteStatus," +
            " e.start_work as workf," +
            " e.address_id as address," +
            " e.position_id as posi," +
            " e.user_name as userName," +
            " cm.id as cId," +
            " cm.commune_name as cmName," +
            " dis.district_name as disName," +
            " dis.id as dId," +
            " pro.id as pId," +
            " pro.province_name as proName," +
            " p.position_name as posiName" +
            " from employee e join commune cm on e.address_id = cm.id" +
            " join district dis on cm.district_id = dis.id" +
            " join province pro on dis.province_id = pro.id" +
            " join position p on e.position_id = p.id" +
            " where e.delete_status = 0 and (e.start_work between :from and :end) and (e.date_of_birth between :dobfrom and :dobend)" +
            " and p.id like %:pId% and dis.district_name like %:address% and e.employee_code like %:code% and e.employee_name like %:name%", nativeQuery = true,
            countQuery="select e.id as id," +
                    " e.employee_code as code," +
                    " e.employee_name as name," +
                    " e.date_of_birth as dob," +
                    " e.image_url as img," +
                    " e.email as email," +
                    " e.phone as phone," +
                    " e.salary as salary," +
                    " e.delete_status as deleteStatus," +
                    " e.start_work as workf," +
                    " e.address_id as address," +
                    " e.position_id as posi," +
                    " e.user_name as userName," +
                    " cm.id as cmId," +
                    " cm.commune_name as cmName," +
                    " dis.district_name as disName," +
                    " dis.id as disId," +
                    " pro.id as proId," +
                    " pro.province_name as proName," +
                    " p.position_name as posiName" +
                    " from employee e join commune cm on e.address_id = cm.id" +
                    " join district dis on cm.district_id = dis.id" +
                    " join province pro on dis.province_id = pro.id" +
                    " join position p on e.position_id = p.id" +
                    " where e.delete_status = 0 and (e.start_work between :from and :end) and (e.date_of_birth between :dobfrom and :dobend)" +
                    " and p.id like %:pId% and dis.district_name like %:address% and e.employee_code like %:code% and e.employee_name like %:name%")
    Page<IEmployeeDTO> findAllByDistrict(@Param("code")String code,@Param("name")String name,@Param("from") String from,
                                         @Param("end")String end,@Param("dobfrom")String dobfrom,
                                         @Param("dobend")String dobend,@Param("pId")String pId,@Param("address")String address, Pageable pageable);

    /**
     *Create by LongNB
     * Date create: 11/08/2022
     * function: findAll employee by province
     */
    @Query(value = "select e.id as id," +
            " e.employee_code as code," +
            " e.employee_name as name," +
            " e.date_of_birth as dob," +
            " e.image_url as img," +
            " e.email as email," +
            " e.phone as phone," +
            " e.salary as salary," +
            " e.delete_status as deleteStatus," +
            " e.start_work as workf," +
            " e.address_id as address," +
            " e.position_id as posi," +
            " e.user_name as userName," +
            " cm.id as cId," +
            " cm.commune_name as cmName," +
            " dis.district_name as disName," +
            " dis.id as dId," +
            " pro.id as pId," +
            " pro.province_name as proName," +
            " p.position_name as posiName" +
            " from employee e join commune cm on e.address_id = cm.id" +
            " join district dis on cm.district_id = dis.id" +
            " join province pro on dis.province_id = pro.id" +
            " join position p on e.position_id = p.id" +
            " where e.delete_status = 0 and (e.start_work between :from and :end) and (e.date_of_birth between :dobfrom and :dobend)" +
            " and p.id like %:pId% and pro.province_name like %:address% and e.employee_code like %:code% and e.employee_name like %:name%", nativeQuery = true,
            countQuery="select e.id as id," +
                    " e.employee_code as code," +
                    " e.employee_name as name," +
                    " e.date_of_birth as dob," +
                    " e.image_url as img," +
                    " e.email as email," +
                    " e.phone as phone," +
                    " e.salary as salary," +
                    " e.delete_status as deleteStatus," +
                    " e.start_work as workf," +
                    " e.address_id as address," +
                    " e.position_id as posi," +
                    " e.user_name as userName," +
                    " cm.id as cId," +
                    " cm.commune_name as cmName," +
                    " dis.district_name as disName," +
                    " dis.id as dId," +
                    " pro.id as pId," +
                    " pro.province_name as proName," +
                    " p.position_name as posiName" +
                    " from employee e join commune cm on e.address_id = cm.id" +
                    " join district dis on cm.district_id = dis.id" +
                    " join province pro on dis.province_id = pro.id" +
                    " join position p on e.position_id = p.id" +
                    " where e.delete_status = 0 and (e.start_work between :from and :end) and (e.date_of_birth between :dobfrom and :dobend)" +
                    " and p.id like %:pId% and pro.province_name like %:address% and e.employee_code like %:code% and e.employee_name like %:name%")
    Page<IEmployeeDTO> findAllByProvince(@Param("code")String code, @Param("name")String name, @Param("from") String from,
                                         @Param("end")String end, @Param("dobfrom")String dobfrom,
                                         @Param("dobend")String dobend, @Param("pId")String pId, @Param("address")String address, Pageable pageable);

    /**
     * Create by HoangHN
     * Date create: 13/08/2022
     * method find Email get username
     * @param email
     * @return
     */
    @Query(value = "SELECT user_name FROM employee where email = :email", nativeQuery = true)
    String findByEmailGetUsername(@Param("email") String email);

    /**
     * Create by HoangHN
     * Date create: 13/08/2022
     * method find Employee By UserName
     * @param username
     * @return
     */
    @Query(value = "SELECT id, employee_code, date_of_birth,email, image_url, employee_name, phone, " +
            "salary, start_work, delete_status, user_name,address_id,position_id FROM employee WHERE user_name = :username", nativeQuery = true)
    Optional<Employee> findEmployeeByUserName(@Param("username") String username);
}
