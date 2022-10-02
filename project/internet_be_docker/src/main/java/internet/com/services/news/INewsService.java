package internet.com.services.news;

import internet.com.entity.news.News;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface INewsService {
    Page<News> findAllNews(Pageable pageable, String title);

    void save(News news);

    News findById(Integer id);

    void updateViews(Integer id);
}
