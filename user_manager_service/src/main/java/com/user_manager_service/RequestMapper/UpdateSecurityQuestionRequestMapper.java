package com.user_manager_service.RequestMapper;


import com.model.SecurityQuestionVO;
import com.user_manager_service.form.UpdateUserSecurityQuestionRequest;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "default")
public interface UpdateSecurityQuestionRequestMapper {

    UpdateSecurityQuestionRequestMapper INSTANCE = Mappers.getMapper( UpdateSecurityQuestionRequestMapper.class);

    SecurityQuestionVO map(UpdateUserSecurityQuestionRequest updateUserSecurityQuestionRequest);
}
