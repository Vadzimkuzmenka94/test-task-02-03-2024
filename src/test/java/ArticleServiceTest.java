import by.test.task.dto.article.ArticleRequest;
import by.test.task.mapper.ArticleMapper;
import by.test.task.orm.ArticleEntity;
import by.test.task.orm.ArticleRepository;
import by.test.task.service.ArticleService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class ArticleServiceTest {

    @Mock
    private ArticleRepository articleRepository;

    @Mock
    private ArticleMapper articleMapper;

    @InjectMocks
    private ArticleService articleService;

    @Test
    void createArticle_ValidRequest_ReturnsCreatedArticle() {

        ArticleRequest request = new ArticleRequest();
        ArticleEntity createdArticle = new ArticleEntity();

        Mockito.when(articleMapper.toEntity(request)).thenReturn(createdArticle);
        Mockito.when(articleRepository.save(createdArticle)).thenReturn(createdArticle);


        ArticleEntity actualArticle = articleService.createArticle(request);

        assertEquals(createdArticle, actualArticle);
    }
}
