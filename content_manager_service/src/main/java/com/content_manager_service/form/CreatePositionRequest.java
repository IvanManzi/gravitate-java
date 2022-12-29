package com.content_manager_service.form;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

public record CreatePositionRequest(@NotBlank String jobId,
                                    @NotBlank String positionExperience,
                                    @NotNull String skills,
                                    @NotNull Long roleId,
                                    String positionKra,
                                    String opportunityType,
                                    @NotBlank String positionType,
                                    Double referralAmount,
                                    Double incentiveAmount,
                                    Integer points,
                                    Long projectId,
                                    Date beginDate,
                                    Date endDate

                                    ) {
}
