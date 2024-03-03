package by.test.task.orm;

import by.test.task.core.orm.UuidRepository;
import by.test.task.dto.statistic.StatisticResponse;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.OffsetDateTime;
import java.util.List;
/**
 * Repository interface for managing ArticleEntity entities.
 */
@Repository
public interface ArticleRepository extends UuidRepository<ArticleEntity> {
    /**
     * Retrieves all active articles.
     *
     * @return a list of active ArticleEntity objects
     */
    @Query("""
            select a
            from ArticleEntity a
            where a.deletedDate is null
            """)
    List<ArticleEntity> findAllActive();
    /**
     * Retrieves daily article count for the last week within the specified date range.
     *
     * @param startDate the start date of the range
     * @param endDate   the end date of the range
     * @return a list of StatisticResponse objects representing daily article count for the last week
     */
    @Query("""
        select NEW by.test.task.dto.statistic.StatisticResponse(DATE_TRUNC('day', a.createdDate), COUNT(a))
        from ArticleEntity a
        where a.deletedDate is null 
        and a.createdDate >= :startDate
        and a.createdDate < :endDate
        group by DATE_TRUNC('day', a.createdDate)
        order by DATE_TRUNC('day', a.createdDate) desc 
        """)
    List<StatisticResponse> getDailyArticleCountForLastWeek(@Param("startDate") OffsetDateTime startDate,
                                                            @Param("endDate") OffsetDateTime endDate);



}