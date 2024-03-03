package by.test.task.core.orm;

import org.springframework.data.repository.NoRepositoryBean;

import java.util.UUID;

@NoRepositoryBean
public interface UuidRepository<E extends UuidEntity> extends AbstractRepository<E, UUID> {
}