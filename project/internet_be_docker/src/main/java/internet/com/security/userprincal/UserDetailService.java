package internet.com.security.userprincal;

import internet.com.entity.user.AppUser;
import internet.com.repository.user_repo.IUserRepository;
import internet.com.services.user.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class UserDetailService implements UserDetailsService {
    @Autowired
    IUserRepository userRepository;
    @Autowired
    UserServiceImpl userService;

    /**
     * Create by HoangHN
     * Date create: 08/09/2022
     * function: loadUserByUsername
     * @param username the username identifying the user whose data is required.
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser appUser = userRepository.findByUsername(username).orElseThrow(()-> new UsernameNotFoundException("User not found: "+username));
        return UserPrinciple.build(appUser);
    }
}
