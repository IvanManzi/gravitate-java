package com.content_manager_service.form;

import javax.validation.constraints.NotNull;

public record AwardPerformancePointsRequest(@NotNull Long userId,
                                            @NotNull Long performanceEvaluationId,
                                            @NotNull Integer quarter,

                                            @NotNull Integer sprint,

                                            @NotNull Integer year,

                                            @NotNull Integer points
                                            ) {
}
