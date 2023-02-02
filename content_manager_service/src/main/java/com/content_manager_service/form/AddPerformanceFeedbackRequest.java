package com.content_manager_service.form;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public record AddPerformanceFeedbackRequest(@NotNull Long userId,
                                            @NotNull Integer year,
                                            Integer quarter,
                                            Integer sprint,
                                            @NotBlank String feedback ) {
}
