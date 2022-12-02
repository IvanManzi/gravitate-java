package com.content_manager_service.form;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public record UpdatePolicyRequest(@NotNull Long policyId, @NotBlank String policyType,@NotBlank String policyName, @NotBlank String filePath) {
}
