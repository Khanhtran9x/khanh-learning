package internet.com.repository.employee_repo;

import internet.com.entity.employee.Position;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public interface  IPositionRepository extends PagingAndSortingRepository<Position, Integer> {
    @Query(value = "select id, position_name from position", nativeQuery = true)
    List<Position> findAll();
}