package com.content_manager_service.form;

import javax.validation.constraints.NotBlank;

public record CreateWishRequest(@NotBlank Long userId,@NotBlank String wishType,@NotBlank String comment) {
}
