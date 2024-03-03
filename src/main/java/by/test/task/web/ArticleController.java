package by.test.task.web;

import by.test.task.dto.article.ArticleRequest;
import by.test.task.dto.article.ArticleResponse;
import by.test.task.dto.statistic.StatisticResponse;
import by.test.task.mapper.ArticleMapper;
import by.test.task.service.ArticleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Articles")
@RequiredArgsConstructor
@RestController
@RequestMapping("/articles")
@Validated
@Slf4j
public class ArticleController {
    private final ArticleService service;
    private final ArticleMapper mapper;

    @Operation(summary = "Create a new article")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Article created successfully"),
            @ApiResponse(responseCode = "403", description = "Forbidden")
    })
    @PostMapping()
    public ArticleResponse create(
            @Valid @RequestBody ArticleRequest req) {
        var article = service.createArticle(req);
        return mapper.apply(article);
    }

    @Operation(summary = "Get all articles")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Articles retrieved successfully"),
            @ApiResponse(responseCode = "403", description = "Forbidden")
    })
    @GetMapping()
    public Page<ArticleResponse> getAll(@RequestParam(value = "page", defaultValue = "0") int page,
                                        @RequestParam(value = "size", defaultValue = "10") int size) {
        var articles = service.getAllArticles(page, size);
        return articles.map(mapper);
    }

    @Operation(summary = "Get article statistics")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Article statistics retrieved successfully"),
            @ApiResponse(responseCode = "403", description = "Forbidden")
    })
    @GetMapping("/statistic")
    public Page<StatisticResponse> getStatistic(@RequestParam(value = "page", defaultValue = "0") int page,
                                                @RequestParam(value = "size", defaultValue = "10") int size) {
        return service.getStatistic(page, size);
    }
}
