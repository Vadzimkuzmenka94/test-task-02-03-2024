package by.test.task.core.orm;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;

import java.util.UUID;

@MappedSuperclass
public class UuidEntity extends AbstractEntity {
    @Id
    @Column(
            name = "id",
            nullable = false
    )
    UUID id;

    @Override
    protected void onCreate() {
        super.onCreate();
        if (this.id == null) {
            this.id = UUID.randomUUID();
        }

    }

    public UuidEntity(final UUID id) {
        this.id = id;
    }

    public UuidEntity() {
    }

    public UUID getId() {
        return this.id;
    }

    public void setId(final UUID id) {
        this.id = id;
    }
}
