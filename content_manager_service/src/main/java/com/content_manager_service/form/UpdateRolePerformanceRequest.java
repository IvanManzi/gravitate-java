package com.content_manager_service.form;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public record UpdateRolePerformanceRequest(@NotNull Long performanceId, @NotNull Long roleId, @NotBlank String criteria, @NotBlank String description) {
}
