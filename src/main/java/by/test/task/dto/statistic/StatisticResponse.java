package by.test.task.dto.statistic;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.OffsetDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Statistic response")
public class StatisticResponse {
    @Schema(description = "The date for which the statistic is calculated", example = "2023-12-10T08:30:00Z")
    private OffsetDateTime date;

    @Schema(description = "The number of articles published on the given date", example = "10")
    private long articleCount;
}

