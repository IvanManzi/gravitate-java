package com.project_manager_service.form;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

public record CreateJiraIssueRequest(@NotBlank String projectKey,
                                     @NotBlank String issueTypeKey,
                                     @NotBlank String summary,
                                     @NotBlank String description,
                                     @NotBlank String assignee,
                                     @NotNull List<String> labels) {
}
