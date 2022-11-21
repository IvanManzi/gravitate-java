package com.content_manager_service.form;

import javax.validation.constraints.NotBlank;

public record CreateTopicRequest(@NotBlank String title,@NotBlank String tags,@NotBlank String description,@NotBlank String thumbnail) {
}
