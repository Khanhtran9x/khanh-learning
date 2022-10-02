package internet.com.repository.computer_repo;

import internet.com.entity.computer.ComputerType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface IComputerTypeRepository extends JpaRepository<ComputerType, Integer> {

    /**
     * Created by: TuanHD
     * Date created: 09/08/2022
     * Function: findAllComputerType
     */
    @Query(value = "SELECT id,computer_type_name FROM computer_type", nativeQuery = true)
    List<ComputerType> findAllComputerType();
}
