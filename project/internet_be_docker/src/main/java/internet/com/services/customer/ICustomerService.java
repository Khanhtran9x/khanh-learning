package internet.com.services.customer;

import internet.com.dto.customer_dto.ICustomerDTO;
import internet.com.entity.customer.Customer;
import internet.com.entity.user.AppUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import internet.com.dto.customer_dto.CustomerDTO;
import org.springframework.data.repository.query.Param;


import java.util.Optional;

public interface ICustomerService {

    /**
     * Created by: DuyNT
     * Date Created: 10/08/2022
     * @param id
     */
    Optional<Customer> findCustomerById(Integer id);


    /**
     * Created by: TrungTHQ
     * Date Created: 10/08/2022
     */
    Page<ICustomerDTO> searchCustomerByProvince(String province,String name,String activeStatus,String starDay,String endDay,Pageable pageable);
    Page<ICustomerDTO> searchCustomerByDistrict(String district,String name,String activeStatus,String starDay,String endDay,Pageable pageable);
    Page<ICustomerDTO> searchCustomerByCommune( String commune,String name,String activeStatus,String starDay,String endDay,Pageable pageable);
    /**
     * Created by: TrungTHQ
     * Date Created: 13/08/2022
     */
    void deleteCustomerById(Integer id);


    Boolean existsEmail(String email);

    Boolean existsPhoneNumber(String phone);

    Boolean matchesPassword(String password, Integer id);

    void saveCustomer(CustomerDTO customerDTO);

    /**
     * Created by: CuongTM
     * Date Created: 11/08/2022
     * @param customer
     */
    void update(Customer customer);

    String findByEmailGetUsername(String email);

    Optional<Customer> findCustomerByUserName(String username);

    Integer getRemainingTime(Integer id);

    void setOutOfTime(Integer id, Integer remaining);
}


