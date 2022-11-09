package com.content_manager_service.form;

import javax.validation.constraints.NotBlank;

public record UpdateUserRoleRequest(@NotBlank Long roleId,@NotBlank String name, @NotBlank String kra) {
}
