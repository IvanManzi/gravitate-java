package com.content_manager_service.form;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public record CreateClientReferralRequest(@NotBlank String organisationName,
                                          @NotNull String referencable,
                                          @NotBlank String clientName,
                                          @NotBlank String number,
                                          @NotNull String email,
                                          @NotNull String clientDescription,
                                          @NotNull String projectDescription) {
}
