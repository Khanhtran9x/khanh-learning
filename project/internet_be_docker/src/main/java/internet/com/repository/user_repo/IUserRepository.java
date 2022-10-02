package internet.com.repository.user_repo;

import internet.com.entity.user.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
@Transactional
public interface IUserRepository extends JpaRepository<AppUser, Integer> {

    /**
     * Create by HoangHN
     * Date create: 08/09/2022
     * function: findByUsername
     * @param name
     * @return
     */
    @Query(value = "SELECT user_name, user_password FROM user WHERE user_name = :name", nativeQuery = true)
    Optional<AppUser> findByUsername(String name);

    /**
     * Create by HaoNH
     * Date create: 11/09/2022
     * method check username is exits
     * @param username
     * @return
     */
    @Query(value = "SELECT user_name FROM user where user_name = :username", nativeQuery = true)
    String existsByUsername(String username);

    /**
     * Create by CuongTM
     * Date create: 17/08/2022
     * method check username is exits
     * @param username
     * @return
     */
    @Query(value = "SELECT user_name FROM user WHERE user_name = :username AND user_name <> (SELECT user_name FROM customer WHERE id = :id);", nativeQuery = true)
    String existsByUsernameInEdit(@Param("username") String username, @Param("id") Integer id);

    /**
     * Create by HaoNH
     * Date create: 11/09/2022
     * method create user
     * @param username
     * @param password
     */
    @Modifying
    @Query(value = "INSERT INTO user (user_name, user_password) VALUES (:username, :password)",
            nativeQuery = true)
    void createUser(@Param("username") String username,
                    @Param("password") String password);

    /**
     * Create by CuongTM
     * Date create: 11/09/2022
     * method update user when updateC customer
     * @param passWord
     * @param userName
     */
    @Modifying
    @Query(value = "Update user set user_password=:passWord WHERE user_name=:userName", nativeQuery = true)
    void updateUser(@Param("passWord") String passWord,
                    @Param("userName") String userName);

}
