package internet.com.services.game.impl;

import internet.com.entity.game.GameCategory;
import internet.com.repository.game_repo.IGameCategoryRepository;
import internet.com.services.game.IGameCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GameCategoryService implements IGameCategoryService {
    @Autowired
    private IGameCategoryRepository gameCategoryRepository;

    /*
     * Created by: KienNDT,
     * Date created: 09/08/2022
     * Function: to get all game categories
     */
    @Override
    public List<GameCategory> getAll() {
        return gameCategoryRepository.getAll();
    }
}
