package com.content_manager_service.form;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public record CreateTopicCommentRequest(@NotNull Long topicId,@NotBlank String comment) {
}
