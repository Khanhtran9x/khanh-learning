package internet.com.services.game.impl;

import internet.com.repository.game_repo.IGameRepository;
import internet.com.services.game.IGameService;


import internet.com.entity.game.Game;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class GameService implements IGameService {
    @Autowired
    private IGameRepository gameRepository;

    /*
     * Created by: KienNDT,
     * Date created: 09/08/2022
     * Function: to get all games
     */
    @Override
    public Page<Game> getAll(Pageable pageble) {
        return gameRepository.getAll(pageble);
    }

    /*
     * Created by: KhanhTV,
     * Date created: 09/08/2022
     * Function: to get all popular games
     */
    @Override
    public Page<Game> getPopularGames(Pageable pageble) {
        return gameRepository.getPopularGames(pageble);
    }

    /*
     * Created by: KhanhTV,
     * Date created: 09/08/2022
     * Function: to get all new games
     */
    @Override
    public Page<Game> getNewGames(Pageable pageble) {
        return gameRepository.getNewGames(pageble);
    }

    /*
     * Created by: KhanhTV,
     * Date created: 09/08/2022
     * Function: to get all hot games
     */
    @Override
    public Page<Game> getHotGames(Pageable pageble) {
        return gameRepository.getHotGames(pageble);
    }

    /*
     * Created by: KienNTD,
     * Date created: 09/08/2022
     * Function: to create game
     */
    @Override
    public void save(Game game) {
        gameRepository.saveGame(game.getName(),
                game.getGameCategory().getId(),
                game.getCreateDate(),
                game.getPlayedTimes(),
                game.getTrailerUrl(),
                game.getImageUrl(),
                game.getContent());
    }

    /*
     * Created by: KhanhTV,
     * Date created: 09/08/2022
     * Function: to find game by id
     */
    @Override
    public Game findById(Integer id) {
        return gameRepository.findGameById(id);
    }

    /*
     * Created by: KienNDT,
     * Date created: 09/08/2022
     * Function: to update game by id
     */
    @Override
    public void update(Game game) {
        gameRepository.updateGame(game.getName(),
                game.getGameCategory().getId(),
                game.getCreateDate(),
                game.getPlayedTimes(),
                game.getTrailerUrl(),
                game.getImageUrl(),
                game.getContent(),
                game.getId());
    }

    /*
     * Created by: KhanhTV,
     * Date created: 09/08/2022
     * Function: to remove game by id
     */
    @Override
    public void remove(Game game) {
        gameRepository.delete(game.getId());
    }

    /*
     * Created by: KhanhTV,
     * Date created: 09/08/2022
     * Function: to search game by name
     */
    @Override
    public Page<Game> searchByName(String gameName, Pageable pageble) {
        return gameRepository.searchByName("%" + gameName + "%", pageble);
    }

    @Override
    public Boolean existsGameName(String name) {
        return name.equals(gameRepository.existsGameName(name));
    }
}
