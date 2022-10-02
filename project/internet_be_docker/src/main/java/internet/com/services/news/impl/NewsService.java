package internet.com.services.news.impl;

import internet.com.entity.news.News;
import internet.com.repository.news_repo.INewsRepository;
import internet.com.services.news.INewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class NewsService implements INewsService {

    @Autowired
    private INewsRepository iNewsRepository;

    @Override
    public Page<News> findAllNews(Pageable pageable, String title) {
        return iNewsRepository.findAll(pageable, title);
    }

//    @Override
//    public Page<News> searchByTitle(Pageable pageable, String title) {
//        return iNewsRepository.searchByTitle(pageable,title);
//    }

    @Override
    public void save(News news) {
        iNewsRepository.save(news.getGameCategory().getId(),news.getTitle(),news.getImageUrl(),news.getContent(),news.getCreateDate(),news.getViews(),news.getAuthor());
    }

    @Override
    public News findById(Integer id) {
        return iNewsRepository.findByIdNews(id);
    }

    @Override
    public void updateViews(Integer id) {
        iNewsRepository.updateViews(id);
    }
}
