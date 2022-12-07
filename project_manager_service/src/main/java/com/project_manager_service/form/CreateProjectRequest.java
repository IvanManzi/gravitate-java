package com.project_manager_service.form;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public record CreateProjectRequest(@NotBlank String clientName,
                                   @NotNull Long projectLead,
                                   @NotBlank String jiraId,
                                   @NotBlank String projectName,
                                   @NotBlank String email,
                                   @NotBlank String description,
                                   @NotBlank String phoneNumber) {
}
