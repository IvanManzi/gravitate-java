package com.content_manager_service.form;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public record CreateDiscussionForumAnswer(@NotNull Long forumId, @NotBlank String comment, Long parent) {
}
