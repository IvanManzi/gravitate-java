package com.content_manager_service.form;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

public record UpdatePositionRequest(@NotBlank Long positionId,
                                    @NotBlank String jobId,
                                    @NotBlank String positionExperience,
                                    @NotNull String skills,
                                    @NotNull String opportunityType,
                                    @NotNull Long roleId,
                                    @NotBlank String positionKra,
                                    @NotBlank String positionType,
                                    Double referralAmount,
                                    Double incentiveAmount,
                                    Integer points,
                                    Long projectId,
                                    Date beginDate,
                                    Date endDate

                                    ) {
}
