package by.test.task.core.exceptions.abstracts;

import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import java.util.Collection;
import java.util.Objects;
import java.util.Optional;

public interface ThrowableExtensions {
    void doThrow(final String... args);

    default void throwOnException(final RunnableThrows block, final String... args) {
        try {
            block.run();
        } catch (Exception var4) {
            this.doThrow(args);
        }

    }

    default void throwIf(boolean condition, final String... args) {
        if (condition) {
            this.doThrow(args);
        }

    }

    default void throwIfNot(boolean condition, final String... args) {
        this.throwIf(!condition, args);
    }

    default <T> T throwIfNull(final T nullable, final String... args) {
        this.throwIf(Objects.isNull(nullable), args);
        return nullable;
    }

    default <T> T throwIfNonNull(final T nonNullable, final String... args) {
        this.throwIf(Objects.nonNull(nonNullable), args);
        return nonNullable;
    }

    @Nullable
    default <T> T throwIfEmpty(@NonNull final Optional<T> optional, final String... args) {
        if (optional.isEmpty()) {
            this.doThrow(args);
            return null;
        } else {
            return optional.get();
        }
    }

    default <T> void throwIfPresent(@NonNull final Optional<T> optional, final String... args) {
        this.throwIf(optional.isPresent(), args);
    }

    default <E> Collection<E> throwIfNullOrEmpty(final Collection<E> collection, final String... args) {
        this.throwIf(collection == null || collection.isEmpty(), args);
        return collection;
    }

    @NonNull
    default String throwIfEmpty(@NonNull final String stringTest, final String... args) {
        this.throwIf(stringTest.isEmpty(), args);
        return stringTest;
    }

    @NonNull
    default String throwIfNotEmpty(@NonNull final String stringTest, final String... args) {
        this.throwIfNot(stringTest.isEmpty(), args);
        return stringTest;
    }

    @NonNull
    default String throwIfBlank(@NonNull final String stringTest, final String... args) {
        this.throwIf(stringTest.isBlank(), args);
        return stringTest;
    }

    @NonNull
    default String throwIfBlankOrEmpty(@NonNull final String stringTest, final String... args) {
        this.throwIf(stringTest.isEmpty() || stringTest.isBlank(), args);
        return stringTest;
    }

    default String throwIfNullOrEmpty(final String stringTest, final String... args) {
        this.throwIf(stringTest == null || stringTest.isEmpty(), args);
        return stringTest;
    }

    default String throwIfNullOrEmptyOrBlank(final String stringTest, final String... args) {
        this.throwIf(stringTest == null || stringTest.isEmpty() || stringTest.isBlank(), args);
        return stringTest;
    }

    @NonNull
    default String throwIfMatches(@NonNull final String stringTest, @NonNull final String regex, final String... args) {
        this.throwIf(stringTest.matches(regex), args);
        return stringTest;
    }

    @NonNull
    default String throwIfNotMatches(@NonNull final String stringTest, @NonNull final String regex, final String... args) {
        this.throwIfNot(stringTest.matches(regex), args);
        return stringTest;
    }
}
