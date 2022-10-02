package internet.com.controller;

import internet.com.dto.game_dto.GameDTO;
import internet.com.entity.game.Game;
import internet.com.entity.game.GameCategory;
import internet.com.services.game.IGameCategoryService;
import internet.com.services.game.IGameService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/games")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class GameController {
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private IGameService gameService;
    @Autowired
    private IGameCategoryService gameCategoryService;

    /**
     * Created by: KhanhTV,
     * Date created: 09/08/2022
     * Function: to get all games
     * @param page
     * @return HttpStatus.OK
     * @return HttpStatus.NO_CONTENT
     */
    @GetMapping
    public ResponseEntity<Page<Game>> getAllGames(@RequestParam(name = "page", defaultValue = "0") int page) {
        Sort sort = Sort.by("create_date").descending();
        Page<Game> games = gameService.getAll(PageRequest.of(page, 8, sort));
        if (games.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(games, HttpStatus.OK);
    }

    /**
     * Created by: KhanhTV,
     * Date created: 09/08/2022
     * Function: to get all popular games
     * @param page
     * @return HttpStatus.OK
     * @return HttpStatus.NO_CONTENT
     */
    @GetMapping("/popular")
    public ResponseEntity<Page<Game>> getPopularGames(@RequestParam(name = "page", defaultValue = "0") int page) {
        Sort sort = Sort.by("played_times").descending();
        Page<Game> games = gameService.getPopularGames(PageRequest.of(page, 8, sort));
        if (games.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(games, HttpStatus.OK);
    }

    /**
     * Created by: KhanhTV,
     * Date created: 16/08/2022
     * Function: to get top 3 games
     * @param page
     * @return
     */
    @GetMapping("/top-3")
    public ResponseEntity<Page<Game>> getTop3Games(@RequestParam(name = "page", defaultValue = "0") int page) {
        Sort sort = Sort.by("played_times").descending();
        Page<Game> games = gameService.getPopularGames(PageRequest.of(page, 4, sort));
        if (games.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(games, HttpStatus.OK);
    }

    /**
     * Created by: KhanhTV,
     * Date created: 09/08/2022
     * Function: to get all new games
     * @param page
     * @return HttpStatus.OK
     * @return HttpStatus.NO_CONTENT
     */
    @GetMapping("/new")
    public ResponseEntity<Page<Game>> getNewGames(@RequestParam(name = "page", defaultValue = "0") int page) {
        Sort sort = Sort.by("create_date").descending();
        Page<Game> games = gameService.getNewGames(PageRequest.of(page, 8, sort));
        if (games.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(games, HttpStatus.OK);
    }

    /**
     * Created by: KhanhTV,
     * Date created: 09/08/2022
     * Function: to get all hot games
     * @param page
     * @return HttpStatus.OK
     * @return HttpStatus.NO_CONTENT
     */
    @GetMapping("/hot")
    public ResponseEntity<Page<Game>> getHotGames(@RequestParam(name = "page", defaultValue = "0") int page) {
        Sort sort = Sort.by("played_times").descending();
        Page<Game> games = gameService.getHotGames(PageRequest.of(page, 8, sort));
        if (games.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(games, HttpStatus.OK);
    }

    /**
     * Created by: KienNDT,
     * Date created: 09/08/2022
     * Function: to get all game category
     * @return HttpStatus.OK
     * @return HttpStatus.NO_CONTENT
     */
    @GetMapping("/game-categories")
    public ResponseEntity<List<GameCategory>> getAllGameCategories() {
        List<GameCategory> gameCategories = gameCategoryService.getAll();
        if (gameCategories.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(gameCategories, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Game> findById(@PathVariable Integer id){
       Game game= gameService.findById(id);
        if (game == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(game,HttpStatus.OK);
    }

    /**
     * Created by: KienNTD,
     * Date created: 09/08/2022
     * Function: to create game
     * @param gameDTO
     * @param bindingResult
     * @return HttpStatus.OK
     * @return HttpStatus.CREATED
     */
    @PostMapping
    public ResponseEntity<Object> createGame(@Valid @RequestBody GameDTO gameDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(bindingResult.getAllErrors(), HttpStatus.BAD_REQUEST);
        }
        Game game = modelMapper.map(gameDTO, Game.class);
        gameService.save(game);
        return new ResponseEntity<>(gameDTO, HttpStatus.CREATED);
    }

    /**
     * Created by: KienNDT,
     * Date created: 09/08/2022
     * Function: to update game by id
     * @param id
     * @param gameDTO
     * @param bindingResult
     * @return HttpStatus.OK
     * @return HttpStatus.NOT_FOUND
     * @return HttpStatus.BAD_REQUEST
     */
    @PutMapping("/{id}")
    public ResponseEntity<Object> updateGame(@PathVariable Integer id, @Valid @RequestBody GameDTO gameDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(bindingResult.getAllErrors(), HttpStatus.BAD_REQUEST);
        }
        Game game = modelMapper.map(gameDTO, Game.class);
        Game gameOptional = gameService.findById(id);
        if (gameOptional == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        game.setId(gameOptional.getId());
        gameService.update(game);
        return new ResponseEntity<>(gameDTO, HttpStatus.OK);
    }

    /**
     * Created by: KhanhTV,
     * Date created: 09/08/2022
     * Function: to remove game by id
     * @param id
     * @return HttpStatus.NOT_FOUND
     * @return HttpStatus.OK
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Game> deleteGame(@PathVariable Integer id) {
        Game game = gameService.findById(id);
        if (game == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        gameService.remove(game);
        return new ResponseEntity<>(game, HttpStatus.OK);
    }

    /**
     * Created by: KhanhTV,
     * Date created: 09/08/2022
     * Function: to search game by name
     * @param name
     * @return HttpStatus.BAD_REQUEST
     * @return HttpStatus.NO_CONTENT
     * @return HttpStatus.OK
     */
    @GetMapping("/search")
    public ResponseEntity<Page<Game>> searchByName(@RequestParam String name, @RequestParam(name = "page", defaultValue = "0") int page) {
        if (name.equals("null")) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        if (name.equals("")) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Sort sort = Sort.by("id").ascending();
        Page<Game> games = gameService.searchByName(name, PageRequest.of(page, 8, sort));
        if (games.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(games, HttpStatus.OK);
    }

    /**
     * Created by: KienNTD,
     * Date created: 09/08/2022
     * Function: to update game
     * @param id
     * @return HttpStatus.BAD_REQUEST
     * @return HttpStatus.BAD_OK
     */
    @GetMapping(value = "/detail/{id}")
    public ResponseEntity<Game> getInfoGame(@PathVariable Integer id) {

        Game game = this.gameService.findById(id);

        if (game == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(game, HttpStatus.OK);
    }

    @GetMapping("/checkGameName/{gameName}")
    public  ResponseEntity<?> checkUserName(@PathVariable("gameName") String gameName){
        return new ResponseEntity<>(gameService.existsGameName(gameName), HttpStatus.OK);
    }
}
