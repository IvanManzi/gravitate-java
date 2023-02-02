package com.user_manager_service.form;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public record CreateAdditionalPointRequest(@NotNull Long userId,
                                           @NotNull Integer quarter,
                                           @NotNull Integer points,
                                           @NotBlank String comment,
                                           @NotBlank String category) {
}
