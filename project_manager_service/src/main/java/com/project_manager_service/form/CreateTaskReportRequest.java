package com.project_manager_service.form;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

public record CreateTaskReportRequest(@NotNull
                                      String projectId,
                                      @NotBlank
                                      String taskId,
                                      @NotBlank
                                      String name,
                                      @NotNull
                                      Double hours,
                                      @NotBlank
                                      Date date) {
}
