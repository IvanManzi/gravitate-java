package com.project_manager_service.form;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

public record CreateProjectRequest(@NotBlank String clientName,
                                   @NotNull Long projectLead,
                                   @NotBlank String jiraAccountId,
                                   @NotNull Date startDate,
                                   @NotBlank String technologies,
                                   @NotBlank String projectName,
                                   @NotBlank String email,
                                   @NotBlank String description,
                                   @NotBlank String phoneNumber) {
}
