package by.test.task.service;

import by.test.task.core.utils.PageUtils;
import by.test.task.dto.article.ArticleRequest;
import by.test.task.dto.statistic.StatisticResponse;
import by.test.task.mapper.ArticleMapper;
import by.test.task.orm.ArticleEntity;
import by.test.task.orm.ArticleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;

/**
 * Service class for managing articles.
 */
@RequiredArgsConstructor
@Service
@Slf4j
@Transactional
public class ArticleService {
    private final ArticleRepository repository;
    private final ArticleMapper mapper;

    /**
     * Creates a new article based on the request.
     *
     * @param req the request to create the article
     * @return the created article
     */
    public ArticleEntity createArticle(ArticleRequest req) {
        var article = mapper.toEntity(req);
        return repository.save(article);
    }


    /**
     * Retrieves all active articles with pagination.
     *
     * @param page the page number
     * @param size the page size
     * @return a page of active articles
     */
    @Transactional(readOnly = true)
    public Page<ArticleEntity> getAllArticles(int page, int size) {
        var articles = repository.findAllActive();
        return PageUtils.toPage(articles, getPageable(page, size));
    }

    /**
     * Retrieves article statistics for the last week with pagination.
     *
     * @param page the page number
     * @param size the page size
     * @return a page of article statistics
     */
    @Transactional(readOnly = true)
    public Page<StatisticResponse> getStatistic(int page, int size) {
        var articleStatistic = repository.getDailyArticleCountForLastWeek(OffsetDateTime.now().minusDays(7), OffsetDateTime.now());
        return PageUtils.toPage(articleStatistic, getPageable(page, size));
    }

    private Pageable getPageable(int page, int size) {
        return PageRequest.of(page, size);
    }
}
