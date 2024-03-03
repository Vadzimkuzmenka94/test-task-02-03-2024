package by.test.task.orm;

import by.test.task.core.orm.UuidEntity;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.OffsetDateTime;

import static java.time.OffsetDateTime.now;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "articles", schema = "test-task")
public class ArticleEntity extends UuidEntity {
    @ManyToOne
    @JoinColumn(name = "users")
    @Nullable
    private UserEntity user;
    @Column
    private String author;
    @Column(nullable = false, length = 100)
    private String title;
    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;
    @Column(name = "deleted_date")
    private OffsetDateTime deletedDate;

    public void delete() {
        deletedDate = now();
    }

}