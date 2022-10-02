package internet.com.repository.user_repo;

import internet.com.entity.user.AppRole;
import internet.com.entity.user.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;



@Repository
@Transactional
public interface IRoleRepository extends JpaRepository<AppRole, Integer> {
   /**
    * Create by HoangHN
    * Date create: 08/09/2022
    * function: findByName
    * @param name
    * @return
    */
   @Query(value = "SELECT id, name FROM role WHERE name = :name", nativeQuery = true)
   Optional<AppRole> findByName(UserRole name);

   /**
    * Create by HaoNH
    * Date create: 11/08/2022
    * method update role of user
    * @param username
    * @param roleId
    */
   @Modifying
   @Query(value = "INSERT INTO user_role (user_name,role_id) VALUES (:username, :role)",
           nativeQuery = true)
   void addNewUserRole(@Param("username") String username,
                       @Param("role") Integer roleId);
}
