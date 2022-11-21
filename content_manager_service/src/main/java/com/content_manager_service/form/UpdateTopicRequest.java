package com.content_manager_service.form;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public record UpdateTopicRequest(@NotNull Long topicId,
                                 @NotBlank String title,
                                 @NotBlank String tags,
                                 @NotBlank String thumbnail,
                                 @NotBlank String description) {
}
