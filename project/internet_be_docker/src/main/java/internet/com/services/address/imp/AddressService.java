package internet.com.services.address.imp;

import internet.com.entity.customer.Commune;
import internet.com.entity.customer.District;
import internet.com.entity.customer.Province;
import internet.com.repository.address_repo.ICommuneRepo;
import internet.com.repository.address_repo.IDistrictRepo;
import internet.com.repository.address_repo.IProvinceRepo;
import internet.com.services.address.IAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressService implements IAddressService {
    @Autowired
    private ICommuneRepo addressRepo;
    @Autowired
    private IDistrictRepo districtRepo;
    @Autowired
    private IProvinceRepo provinceRepo;

    /**
     * Created by: HaoNH
     * Date Created: 14/06/2022
     * method find all province
     * @return
     */
    @Override
    public List<Province> findAllProvince() {
        return provinceRepo.findAllProvince();
    }

    /**
     * Created by: HaoNH
     * Date Created: 14/06/2022
     * method find district by province_id
     * @return
     * @param provinceId
     */
    @Override
    public List<District> findAllDistrict(Integer provinceId) {
        return districtRepo.findAllDistrict(provinceId);
    }

    /**
     * Created by: HaoNH
     * Date Created: 14/06/2022
     * method find commune by district_id
     * @return
     * @param districtId
     */
    @Override
    public List<Commune> findAllCommune(Integer districtId) {
        return addressRepo.findAllCommune(districtId);
    }
}
