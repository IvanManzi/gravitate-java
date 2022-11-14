package com.content_manager_service.form;

import javax.validation.constraints.NotBlank;

public record UpdateProjectRequest(@NotBlank Long projectId,
                                   @NotBlank String clientName,
                                   @NotBlank String projectName,
                                   @NotBlank String email,
                                   @NotBlank String description,
                                   @NotBlank String phoneNumber) {
}
