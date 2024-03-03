package by.test.task.core.exceptions;


import by.test.task.core.exceptions.abstracts.ErrorCode;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum AppException implements ErrorCode {

    NOT_FOUND("Entity %s not found"),
    ALREADY_EXIST("Entity %s already exists");

    private final String description;
    private final HttpStatus httpStatus;

    AppException(String description) {
        this(description, HttpStatus.BAD_REQUEST);
    }

    AppException(String description, HttpStatus httpStatus) {
        this.description = description;
        this.httpStatus = httpStatus;
    }
}