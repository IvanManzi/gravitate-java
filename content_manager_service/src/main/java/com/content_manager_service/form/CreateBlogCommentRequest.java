package com.content_manager_service.form;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public record CreateBlogCommentRequest(@NotNull Long topicId, @NotBlank String comment,Long parentBlogReplyId) {
}
