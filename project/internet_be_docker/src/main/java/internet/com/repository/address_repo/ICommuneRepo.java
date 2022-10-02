package internet.com.repository.address_repo;

import internet.com.entity.customer.Commune;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public interface ICommuneRepo extends CrudRepository<Commune, Integer> {

    /**
     * Created by: HaoNH
     * Date Created: 14/08/2022
     * method find commune by district_id
     * @return
     * @param districtId
     */
    @Query(value="select id, commune_name, district_id from commune where district_id = :districtId", nativeQuery = true)
    List<Commune> findAllCommune(@Param("districtId") Integer districtId);
}