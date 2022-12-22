package com.content_manager_service.form;

import javax.validation.constraints.NotNull;

public record CreatePositionSelfReferralRequest(@NotNull Long positionId) {
}
