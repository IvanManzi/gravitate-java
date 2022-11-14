package com.content_manager_service.form;

import javax.validation.constraints.NotBlank;

public record CreateUserRoleRequest(@NotBlank String name,@NotBlank String kra) {
}
