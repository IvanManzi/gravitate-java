package com.user_manager_service.form;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

public record CreateAdditionalPointRequest(@NotNull Long userId,
                                           @NotNull Integer quarter,
                                           Long userSkillId,
                                           List<Long> blogIds,
                                           List<Long> forumAnswerIds,
                                           @NotNull Integer points,
                                           @NotBlank String comment,
                                           @NotBlank String category) {
}
