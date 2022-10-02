package internet.com.repository.game_repo;

import internet.com.entity.game.GameCategory;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface IGameCategoryRepository extends PagingAndSortingRepository<GameCategory, Integer> {
    /**
     * Created by: KienNDT,
     * Date created: 09/08/2022
     * Function: to get all game categories
     * @return
     */
    @Query(value = "SELECT id, game_category_name FROM game_category", nativeQuery = true)
    List<GameCategory> getAll();
}
