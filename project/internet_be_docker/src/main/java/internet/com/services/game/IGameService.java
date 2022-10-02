package internet.com.services.game;

import internet.com.entity.game.Game;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IGameService {
    /*
     * Created by: KienNDT,
     * Date created: 09/08/2022
     * Function: to get all games
     */
    Page<Game> getAll(Pageable pageble);

    Boolean existsGameName(String name);

    /*
     * Created by: KhanhTV,
     * Date created: 09/08/2022
     * Function: to get all popular games
     */
    Page<Game> getPopularGames(Pageable pageble);

    /*
     * Created by: KhanhTV,
     * Date created: 09/08/2022
     * Function: to get all new games
     */
    Page<Game> getNewGames(Pageable pageble);

    /*
     * Created by: KhanhTV,
     * Date created: 09/08/2022
     * Function: to get all hot games
     */
    Page<Game> getHotGames(Pageable pageble);

    /*
     * Created by: KienNTD,
     * Date created: 09/08/2022
     * Function: to create game if id is null, if not, it will update game
     */
    void save(Game game);

    /*
     * Created by: KhanhTV,
     * Date created: 09/08/2022
     * Function: to find game by id
     */
    Game findById(Integer id);

    /*
     * Created by: KienNTD,
     * Date created: 09/08/2022
     * Function: to update game
     */
    void update(Game game);

    /*
     * Created by: KhanhTV,
     * Date created: 09/08/2022
     * Function: to delete game
     */
    void remove(Game game);

    /*
     * Created by: KhanhTV,
     * Date created: 09/08/2022
     * Function: to search game by name
     */
    Page<Game> searchByName(String gameName, Pageable pageble);
}
