package com.content_manager_service.form;


import javax.validation.constraints.NotBlank;

public record UpdateWishRequest(@NotBlank Long wishId,@NotBlank Long userId, @NotBlank String wishType, @NotBlank String comment) {
}
