package internet.com.services.user.impl;


import internet.com.entity.user.AppUser;
import internet.com.repository.user_repo.IUserRepository;
import internet.com.services.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    IUserRepository userRepository;
    @Autowired
    PasswordEncoder passwordEncoder;

    /**
     * Create by HoangHN
     * Date create: 10/8/2022
     * function: findByUsername
     * @param name
     * @return
     */
    @Override
    public Optional<AppUser> findByUsername(String name) {
        return userRepository.findByUsername(name);
    }

    /**
     * Create by HaoNH
     * Date create: 11/09/2022
     * method check username is exits
     * @param username
     * @return
     */
    @Override
    public Boolean existsByUsername(String username) {
        return username.equals(userRepository.existsByUsername(username));
    }

    @Override
    public Boolean existsByUsernameInEdit(String username, Integer id) {
        return username.equals(userRepository.existsByUsernameInEdit(username, id));
    }

    /**
     * Create by HaoNH
     * Date create: 11/09/2022
     * method create user
     * @param username
     * @param password
     */
    @Override
    public void createUser(String username, String password) {
        userRepository.createUser(username, password);
    }

    /**
     * Create be CuongTM
     * Date create: 11/08/2022
     * @param appUser
     */
    @Override
    public void updateUser(AppUser appUser) {
        userRepository.updateUser(appUser.getPassword(),appUser.getUsername());
    }
}
