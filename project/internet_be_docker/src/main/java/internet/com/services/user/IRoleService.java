package internet.com.services.user;


import internet.com.entity.user.AppRole;
import internet.com.entity.user.UserRole;

import java.util.Optional;

public interface IRoleService {
    Optional<AppRole> findByName(UserRole name);

    void addNewCustomerUserRole(String username);

    void addNewEmployeeUserRole(String username);

}
