package com.content_manager_service.form;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public record CreatePositionReferralRequest(@NotNull Long positionId,
                                            @NotBlank String firstName,
                                            @NotNull String lastName,
                                            @NotNull String phoneNumber,
                                            @NotNull String email,
                                            @NotBlank String country,
                                            @NotBlank String keySkills,
                                            @NotNull String cvPath) {
}
