package by.test.task.dto.article;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Schema(description = "Article request ")
public class ArticleRequest {
    @NotBlank(message = "Author is required")
    @Size(min = 2, max = 60, message = "Author must contain between {min} and {max} characters")
    @Schema(description = "The author of the article. Must contain between 2 and 60 characters.", example = "John")
    private String author;

    @NotBlank(message = "Title is required")
    @Size(max = 100, message = "Title must not exceed {max} characters")
    @Schema(description = "The title of the article. Must not exceed 100 characters.", example = "Introduction to Spring Boot")
    private String title;

    @NotBlank(message = "Content is required")
    @Schema(description = "The content of the article.")
    private String content;
}

