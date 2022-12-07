package com.content_manager_service.form;

import javax.validation.constraints.NotBlank;

public record UpdateTaskReportRequest(@NotBlank
                                      Long reportId,
                                      @NotBlank
                                      String taskId,
                                      @NotBlank
                                      String projectId) {
}
