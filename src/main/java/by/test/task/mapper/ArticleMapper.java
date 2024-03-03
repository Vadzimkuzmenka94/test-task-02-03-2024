package by.test.task.mapper;

import by.test.task.core.utils.SecurityUtils;
import by.test.task.dto.article.ArticleRequest;
import by.test.task.dto.article.ArticleResponse;
import by.test.task.orm.ArticleEntity;
import by.test.task.orm.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.function.Function;
/**
 * Mapper class for converting between ArticleRequest, ArticleEntity, and ArticleResponse objects.
 */
@RequiredArgsConstructor
@Component
@Slf4j
public class ArticleMapper implements Function<ArticleEntity, ArticleResponse> {
    private final UserRepository userRepository;

    public ArticleEntity toEntity(ArticleRequest req) {
        var principal = SecurityUtils.getPrincipal();
        ArticleEntity e = new ArticleEntity();
        e.setContent(req.getContent());
        e.setTitle(req.getTitle());
        e.setUser(userRepository.findByEmail(principal.getEmail()).orElse(null));
        if (req.getAuthor() != null) {
            e.setAuthor(req.getAuthor());
        } else {
            e.setAuthor(principal.getUsername());
        }
        return e;
    }

    private ArticleResponse toResponse(ArticleEntity article) {
        return ArticleResponse.builder()
                .author(article.getAuthor())
                .title(article.getTitle())
                .content(article.getContent())
                .build();
    }

    @Override
    public ArticleResponse apply(ArticleEntity article) {
        return toResponse(article);
    }
}
