package internet.com.services.game;

import internet.com.entity.game.GameCategory;

import java.util.List;

public interface IGameCategoryService {
    /*
     * Created by: KienNDT,
     * Date created: 09/08/2022
     * Function: to get all game categories
     */
    List<GameCategory> getAll();
}
