package internet.com.controller;


import internet.com.dto.news_dto.NewsDTO;
import internet.com.entity.news.News;
import internet.com.services.news.INewsService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/news")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class NewsController {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private INewsService iNewsService;

    /**
     * Create by: HieuHk
     * Date create: 10/08/2022
     * function: get all news
     * @param page
     * @return pageNews, HttpStatus.OK
     */

    @GetMapping
    public ResponseEntity<Page<News>> findAllNews(@RequestParam(defaultValue = "0") int page,
                                                  @RequestParam(defaultValue = "") String title) {
        Page<News> pageNews;
        pageNews = iNewsService.findAllNews(PageRequest.of(page, 3),title);
        if (pageNews.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(pageNews, HttpStatus.OK);
    }

    /**
     * Create by: HieuHk
     * Date create: 10/08/2022
     * function: create news
     * @param
     * @return news, HttpStatus.CREATED
     */

    @PostMapping
    public ResponseEntity<News> createNew(@Valid @RequestBody NewsDTO newsDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity(bindingResult.getAllErrors(), HttpStatus.BAD_REQUEST);
        }
        News news = modelMapper.map(newsDTO, News.class);
        iNewsService.save(news);
        return new ResponseEntity<>(news, HttpStatus.CREATED);
    }

    /**
     * Create by: HieuHk
     * Date create: 10/08/2022
     * function: find news by id
     * @return news, HttpStatus.OK
     */

    @GetMapping("/detail/{id}")
    public ResponseEntity<News> findById(@PathVariable Integer id) {
        News news = iNewsService.findById(id);
        if (news == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(news, HttpStatus.OK);
    }

    /**
     * Create by: HieuHk
     * Date create: 13/08/2022
     * function: update views
     * @param id
     * @return news, HttpStatus.OK
     */

    @GetMapping("/update-views")
    public ResponseEntity<News> updateViews(@RequestParam Integer id) {
        News news = iNewsService.findById(id);
        if (news == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        iNewsService.updateViews(id);
        return new ResponseEntity<>(news, HttpStatus.OK);
    }
}
