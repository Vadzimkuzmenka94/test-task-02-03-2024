package by.test.task.dto.article;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Schema(description = "Article response")
public class ArticleResponse {
    @Schema(description = "The author of the article", example = "John")
    private String author;

    @Schema(description = "The title of the article", example = "Introduction to Java Programming")
    private String title;

    @Schema(description = "The content of the article", example = "Lorem ipsum dolor sit amet, consectetur adipiscing elit...")
    private String content;
}