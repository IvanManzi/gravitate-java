package com.content_manager_service.form;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public record CreateWishReplyRequest(@NotNull Long wishId, @NotBlank String comment) {
}
