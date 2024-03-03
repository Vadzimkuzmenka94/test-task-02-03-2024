package by.test.task.core.utils;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Utility class for converting a list to a Page object.
 */
public final class PageUtils {
    /**
     * Converts a list to a Page object based on the provided Pageable.
     *
     * @param list  the list of items to be paginated
     * @param pageable the pagination information
     * @param <T>   the type of items in the list
     * @return a Page object representing the sublist of items based on the pagination information
     */
    public static <T> Page<T> toPage(List<T> list, Pageable pageable) {
        int offset = (int) pageable.getOffset();
        int size = list.size();
        int to = offset + Math.min(pageable.getPageSize(), size - offset);
        return new PageImpl<>(list.subList(offset, to), pageable, (long) size);
    }
}

