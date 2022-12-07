package com.project_manager_service.form;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public record UpdateProjectRequest(@NotNull Long projectId,
                                   @NotNull Long projectLead,
                                   @NotBlank String jiraId,
                                   @NotNull Integer status,
                                   @NotBlank String clientName,
                                   @NotBlank String projectName,
                                   @NotBlank String email,
                                   @NotBlank String description,
                                   @NotBlank String phoneNumber) {
}
