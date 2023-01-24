package com.project_manager_service.form;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

public record UpdateTaskReportRequest(@NotNull
                                      Long reportId,
                                      @NotBlank
                                      String name,
                                      @NotNull
                                      Double hours,
                                      @NotNull
                                      Date date,
                                      @NotBlank
                                      String taskId,
                                      @NotBlank
                                      String projectId) {
}
