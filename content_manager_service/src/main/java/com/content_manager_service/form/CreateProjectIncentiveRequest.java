package com.content_manager_service.form;

import javax.validation.constraints.NotNull;

public record CreateProjectIncentiveRequest(@NotNull
                                            Long projectId,
                                            @NotNull
                                            Long userId,
                                            @NotNull
                                            Integer totalHours,
                                            @NotNull
                                            Double performanceBonus,
                                            @NotNull
                                            Double employeeReferral,
                                            @NotNull
                                            Double clientReferral,
                                            @NotNull
                                            Double hotOpportunity) {
}
