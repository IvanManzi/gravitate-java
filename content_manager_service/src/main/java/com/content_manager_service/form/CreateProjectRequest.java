package com.content_manager_service.form;

import javax.validation.constraints.NotBlank;

public record CreateProjectRequest(@NotBlank String clientName,

                                   @NotBlank String jiraId,
                                   @NotBlank String projectName,
                                   @NotBlank String email,
                                   @NotBlank String description,
                                   @NotBlank String phoneNumber) {
}
