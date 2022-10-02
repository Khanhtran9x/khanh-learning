package internet.com.repository.address_repo;

import internet.com.entity.customer.Province;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface IProvinceRepo extends CrudRepository<Province, Integer> {

    /**
     * Created by: HaoNH
     * Date Created: 14/08/2022
     * method find all province
     * @return
     */
    @Query(value="select id, province_name from province", nativeQuery = true)
    List<Province> findAllProvince();
}
