package com.content_manager_service.form;

import javax.validation.constraints.NotBlank;

public record UpdateRolePerformanceRequest(@NotBlank Long performanceId,@NotBlank Long roleId, @NotBlank String criteria, @NotBlank String description) {
}
