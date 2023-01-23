package com.user_manager_service.RequestMapper;

import com.model.SecurityQuestionVO;
import com.user_manager_service.form.CreateUserSecurityQuestionRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CreateSecurityQuestionRequestMapper {

    CreateSecurityQuestionRequestMapper INSTANCE = Mappers.getMapper( CreateSecurityQuestionRequestMapper.class);

    CreateUserSecurityQuestionRequest createSecurityQuestionDto(SecurityQuestionVO securityQuestionVO);
}
