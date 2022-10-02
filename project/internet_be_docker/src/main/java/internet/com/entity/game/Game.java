package internet.com.entity.game;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

/**
 * Created by: KhanhTV,
 * Date created: 09/08/2022
 * Function: to create game entity
 */
@Entity(name = "game")
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "game_name")
    private String name;
    @Column(columnDefinition = "DATE", name = "create_date")
    private String createDate;
    @Column(name = "played_times")
    private Integer playedTimes;
    @Column(name = "trailer_url")
    private String trailerUrl;
    @Column(name = "image_url")
    private String imageUrl;
    @Column(name = "content", columnDefinition = "longtext")
    private String content;
    @Column(name = "delete_status")
    private Boolean delete_status;
    @ManyToOne()
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name="game_category_id", nullable=false)
    private GameCategory gameCategory;

    public Boolean getDelete_status() {
        return delete_status;
    }

    public void setDelete_status(Boolean delete_status) {
        this.delete_status = delete_status;
    }

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

    public Integer getPlayedTimes() {
        return playedTimes;
    }

    public void setPlayedTimes(Integer playedTimes) {
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
