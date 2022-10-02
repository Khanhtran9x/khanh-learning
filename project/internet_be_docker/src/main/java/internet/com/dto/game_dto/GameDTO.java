package internet.com.dto.game_dto;

import internet.com.entity.game.GameCategory;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

/**
 * Created by: KhanhTV,
 * Date created: 09/08/2022
 * Function: to create game dto class for transfer data and validation
 */
public class GameDTO {
    private Integer id;
    @NotBlank
    private String name;

    @NotEmpty(message = "create date is not empty")
    private String createDate;

    @Pattern(regexp = "^[0-9]+$", message = "Invalid played times")
    private String playedTimes;

    @NotEmpty(message = "trailer url is not empty")
    @Pattern(regexp = "https?:\\/\\/(www\\.)?[-a-zA-Z0-9@:%._\\+~#=]{1,256}\\.[a-zA-Z0-9()]{1,6}\\b([-a-zA-Z0-9()@:%_\\+.~#?&\\/{2}=]*)",
            message = "Invalid url")
    private String trailerUrl;

    @NotEmpty(message = "image url is not empty")
    @Pattern(regexp = "https?:\\/\\/(www\\.)?[-a-zA-Z0-9@:%._\\+~#=]{1,256}\\.[a-zA-Z0-9()]{1,6}\\b([-a-zA-Z0-9()@:%_\\+.~#?&\\/{2}=]*)",
            message = "Invalid url")
    private String imageUrl;

    @NotEmpty(message = "content is not empty")
    private String content;
    private GameCategory gameCategory;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getPlayedTimes() {
        return playedTimes;
    }

    public void setPlayedTimes(String playedTimes) {
        this.playedTimes = playedTimes;
    }

    public String getTrailerUrl() {
        return trailerUrl;
    }

    public void setTrailerUrl(String trailerUrl) {
        this.trailerUrl = trailerUrl;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public GameCategory getGameCategory() {
        return gameCategory;
    }

    public void setGameCategory(GameCategory gameCategory) {
        this.gameCategory = gameCategory;
    }
}
