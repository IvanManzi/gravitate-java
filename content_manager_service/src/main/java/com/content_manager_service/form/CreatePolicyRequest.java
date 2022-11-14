package com.content_manager_service.form;

import javax.validation.constraints.NotBlank;

public record CreatePolicyRequest(@NotBlank String policyType,@NotBlank String policyName,@NotBlank String filePath,@NotBlank String description) {
}
