package internet.com.services.user;

import internet.com.entity.user.AppUser;

import java.util.Optional;

public interface IUserService {
    Optional<AppUser> findByUsername(String name);


    Boolean existsByUsername(String username);

    Boolean existsByUsernameInEdit(String username, Integer id);

    void createUser(String username, String password);

    /**
     * Create by CuongTM
     * Date create: 11/08/2022
     * Method update user when update customer
     * @param appUser
     */
    void updateUser(AppUser appUser);
}
