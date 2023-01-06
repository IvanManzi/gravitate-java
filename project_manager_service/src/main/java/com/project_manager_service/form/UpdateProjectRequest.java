package com.project_manager_service.form;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

public record UpdateProjectRequest(@NotNull Long projectId,
                                   @NotNull Long projectLead,
                                   @NotBlank String jiraAccountId,
                                   @NotBlank String jiraProjectKey,
                                   @NotNull Date startDate,
                                   @NotBlank String technologies,
                                   @NotNull Integer status,
                                   @NotBlank String clientName,
                                   @NotBlank String projectName,
                                   @NotBlank String email,
                                   @NotBlank String description,
                                   @NotBlank String phoneNumber) {
}
