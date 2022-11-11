package com.content_manager_service.form;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public record UpdateWishRequest(@NotNull Long wishId, @NotNull Long userId, @NotBlank String wishType, @NotBlank String comment) {
}
