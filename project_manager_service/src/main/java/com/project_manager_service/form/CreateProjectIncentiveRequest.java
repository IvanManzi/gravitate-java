package com.project_manager_service.form;

import javax.validation.constraints.NotNull;

public record CreateProjectIncentiveRequest(@NotNull
                                            Long projectId,
                                            @NotNull
                                            Long userId,
                                            @NotNull
                                            Double performanceBonus,
                                            @NotNull
                                            Double totalAmount,
                                            @NotNull
                                            Double employeeReferral,
                                            @NotNull
                                            Double clientReferral,
                                            @NotNull
                                            Integer month,
                                            @NotNull
                                            Double hotOpportunity) {
}
