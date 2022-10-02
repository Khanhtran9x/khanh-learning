package internet.com.entity.game;

import com.fasterxml.jackson.annotation.JsonBackReference;
import internet.com.entity.news.News;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by: KienTV,
 * Date created: 09/08/2022
 * Function: to create game category entity
 */
@Entity(name = "game_category")
public class GameCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "game_category_name")
    private String name;
    @OneToMany(mappedBy = "gameCategory")
    @JsonBackReference("game-category")
    private Set<Game> games;

    public Set<Game> getGames() {
        return games;
    }

    public void setGames(Set<Game> games) {
        this.games = games;
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

    @OneToMany(mappedBy = "gameCategory")
    @JsonBackReference("newsSet")
    private Set<News> newsSet;

    public Set<News> getNewsSet() {
        return newsSet;
    }

    public void setNewsSet(Set<News> newsSet) {
        this.newsSet = newsSet;
    }
}
