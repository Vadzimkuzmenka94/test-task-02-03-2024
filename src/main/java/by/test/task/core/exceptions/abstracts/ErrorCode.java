package by.test.task.core.exceptions.abstracts;

public interface ErrorCode extends ThrowableExtensions, BaseExceptionEnum {
    default void doThrow(String... args) {
        throw this.getException(args);
    }
}