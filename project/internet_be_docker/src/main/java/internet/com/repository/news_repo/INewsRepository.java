package internet.com.repository.news_repo;

import internet.com.entity.news.News;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import javax.transaction.Transactional;
import org.springframework.data.domain.Pageable;

@Transactional
public interface INewsRepository extends JpaRepository<News, Integer> {

    @Query(value = "select id,category_id,title,image_url,content,create_date,views,author from news where title like %:title% order by create_date desc", nativeQuery = true,
        countQuery = "select id,category_id,title,image_url,content,create_date,views,author from news where title like %:title% order by create_date desc")
    Page<News> findAll(Pageable pageable , @Param("title") String title);

    @Query(value = "select id,category_id,title,image_url,content,create_date,views,author from news where id = :id", nativeQuery = true)
    News findByIdNews(@Param("id") Integer id);

    @Modifying
    @Query(value = "INSERT INTO news (category_id,title,image_url,content,create_date,views,author) VALUES (:category_id,:title,:image_url,:content,:create_date,:views,:author)", nativeQuery = true)
    void save(@Param("category_id") Integer categoryId,
              @Param("title") String title,
              @Param("image_url") String imageUrl,
              @Param("content") String content,
              @Param("create_date") String dateCreate,
              @Param("views") Integer views,
              @Param("author") String author);

    @Modifying
    @Query(value = "UPDATE news SET views = views+1 WHERE id = :id", nativeQuery = true)
    void updateViews(@Param("id") Integer id);
}
