package by.test.task.core.exceptions.abstracts;


import by.test.task.core.exceptions.BaseException;
import org.springframework.http.HttpStatus;

import java.util.function.Supplier;

public interface BaseExceptionEnum extends ExceptionEnum, Supplier<BaseException> {
    HttpStatus getHttpStatus();

    default BaseException getException(String... args) {
        return new BaseException(this.getName(), this.getDescription(args), this.getHttpStatus());
    }

    default BaseException get() {
        return this.getException();
    }
}