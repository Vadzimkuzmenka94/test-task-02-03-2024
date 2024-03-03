package by.test.task.core.orm;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;

import java.time.OffsetDateTime;

@MappedSuperclass
public abstract class AbstractEntity {
    @Column(
            name = "created_date",
            updatable = false
    )
    OffsetDateTime createdDate;
    @Column(
            name = "updated_date"
    )
    OffsetDateTime updatedDate;

    @PrePersist
    protected void prePersist() {
        this.onCreate();
    }

    @PreUpdate
    protected void preUpdate() {
        this.onUpdate();
    }

    protected void onCreate() {
        this.createdDate = OffsetDateTime.now();
    }

    protected void onUpdate() {
        this.updatedDate = OffsetDateTime.now();
    }

    protected AbstractEntity() {
    }

    public OffsetDateTime getCreatedDate() {
        return this.createdDate;
    }

    public OffsetDateTime getUpdatedDate() {
        return this.updatedDate;
    }

}

