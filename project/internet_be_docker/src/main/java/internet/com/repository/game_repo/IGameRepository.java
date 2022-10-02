package internet.com.repository.game_repo;

import internet.com.entity.game.Game;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface IGameRepository extends PagingAndSortingRepository<Game, Integer> {
    /**
     * Created by: KhanhTV,
     * Date created: 09/08/2022
     * Function: to get all games
     * @param pageable
     * @return
     */
    @Query(value = "select * from game where delete_status =0", nativeQuery = true)
    Page<Game> getAll(Pageable pageable);

    /**
     * Created by: KhanhTV,
     * Date created: 09/08/2022
     * Function: to get all popular games
     * @param pageable
     * @return
     */
    @Query(value = "select * from game WHERE game_category_id = 1 and delete_status =0", nativeQuery = true)
    Page<Game> getPopularGames(Pageable pageable);

    /**
     * Created by: KhanhTV,
     * Date created: 09/08/2022
     * Function: to get all new games
     * @param pageable
     * @return
     */
    @Query(value = "select * from game WHERE game_category_id = 2 and delete_status =0", nativeQuery = true)
    Page<Game> getNewGames(Pageable pageable);

    /**
     * Created by: KhanhTV,
     * Date created: 09/08/2022
     * Function: to get all hot games
     * @param pageable
     * @return
     */
    @Query(value = "select * from game WHERE game_category_id = 3 and delete_status =0", nativeQuery = true)
    Page<Game> getHotGames(Pageable pageable);

    /**
     * Created by: KienNTD,
     * Date created: 09/08/2022
     * Function: to save game
     * @param name
     * @param gameCategoryId
     * @param createDate
     * @param playedTimes
     * @param trailerUrl
     * @param imageUrl
     * @param content
     */
    @Modifying
    @Query(value = "insert into game(game_name, game_category_id, create_date, played_times, trailer_url, image_url, content,delete_status) " +
            "values(:game_name, :game_category_id, :create_date, :played_times, " +
            ":trailer_url, :image_url, :content , 0) ", nativeQuery = true)
    void saveGame(@Param("game_name") String name,
                  @Param("game_category_id") Integer gameCategoryId,
                  @Param("create_date") String createDate,
                  @Param("played_times") Integer playedTimes,
                  @Param("trailer_url") String trailerUrl,
                  @Param("image_url") String imageUrl,
                  @Param("content") String content);

    /**
     * Created by: KienNTD,
     * Date created: 09/08/2022
     * Function: to update game by id
     * @param name
     * @param gameCategoryId
     * @param createDate
     * @param playedTimes
     * @param trailerUrl
     * @param imageUrl
     * @param content
     * @param id
     */
    @Modifying
    @Query(value = "update game set game_name =:game_name, game_category_id =:game_category_id, create_date =:create_date, " +
            "played_times =:played_times, trailer_url =:trailer_url, image_url =:image_url, content =:content where id =:id", nativeQuery = true)
    void updateGame(@Param("game_name") String name,
                    @Param("game_category_id") Integer gameCategoryId,
                    @Param("create_date") String createDate,
                    @Param("played_times") Integer playedTimes,
                    @Param("trailer_url") String trailerUrl,
                    @Param("image_url") String imageUrl,
                    @Param("content") String content,
                    @Param("id") Integer id);

    /**
     * Created by: KhanhTV,
     * Date created: 09/08/2022
     * Function: to delete game
     * @param id
     */
    @Modifying
    @Query(value = "UPDATE game SET delete_status =1 WHERE id =:id", nativeQuery = true)
    void delete(@Param("id") Integer id);

    /**
     * Created by: KhanhTV,
     * Date created: 09/08/2022
     * Function: to find game by id
     * @param id
     * @return
     */
    @Query(value = "SELECT * FROM game WHERE id = :id", nativeQuery = true)
    Game findGameById(@Param("id") Integer id);

    /**
     * Created by: KhanhTV,
     * Date created: 09/08/2022
     * Function: to search game by name
     * @param name
     * @param pageable
     * @return
     */
    @Query(value = "SELECT * FROM game WHERE game_name LIKE :game_name AND delete_status = 0", nativeQuery = true)
    Page<Game> searchByName(@Param("game_name") String name, Pageable pageable);

    @Query(value = "SELECT game_name FROM game where game_name = :name", nativeQuery = true)
    String existsGameName(@Param("name") String name);
}
