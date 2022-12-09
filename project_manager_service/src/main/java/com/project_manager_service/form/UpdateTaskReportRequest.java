package com.project_manager_service.form;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public record UpdateTaskReportRequest(@NotNull
                                      Long reportId,
                                      @NotBlank
                                      String taskId,
                                      @NotBlank
                                      String projectId) {
}
