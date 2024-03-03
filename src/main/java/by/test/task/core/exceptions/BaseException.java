package by.test.task.core.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.lang.Nullable;

public class BaseException extends RuntimeException {
    private final String error;
    @Nullable
    private final String description;
    private final HttpStatus httpStatus;

    public BaseException(String error, @Nullable String description, HttpStatus httpStatus) {
        super(description);
        this.error = error;
        this.description = description;
        this.httpStatus = httpStatus;
    }

    public BaseException(String error, @Nullable String description) {
        this(error, description, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public BaseException(String error) {
        this(error, (String)null);
    }

    public String getError() {
        return this.error;
    }

    @Nullable
    public String getDescription() {
        return this.description;
    }

    public HttpStatus getHttpStatus() {
        return this.httpStatus;
    }
}
