package com.content_manager_service.form;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public record CreateClientReferralRequest(@NotBlank String organisationName,
                                          @NotBlank String clientName,
                                          @NotNull String email,
                                          @NotNull String clientDescription,
                                          @NotNull String projectDescription) {
}
