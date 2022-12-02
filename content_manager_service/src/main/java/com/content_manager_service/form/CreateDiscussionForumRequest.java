package com.content_manager_service.form;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public record CreateDiscussionForumRequest(@NotNull
                                           String title,
                                           @NotBlank
                                           String body,
                                           @NotBlank
                                           String tags) {
}
