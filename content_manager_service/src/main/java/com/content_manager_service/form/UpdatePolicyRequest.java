package com.content_manager_service.form;

import javax.validation.constraints.NotBlank;

public record UpdatePolicyRequest(@NotBlank Long policyId,@NotBlank String policyType,@NotBlank String policyName, @NotBlank String filePath) {
}
