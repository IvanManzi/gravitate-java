package com.content_manager_service.form;

import javax.validation.constraints.NotBlank;

public record CreateRolePerformanceRequest(@NotBlank Long roleId,@NotBlank String criteria,@NotBlank String description) {
}
