package com.user_manager_service.RequestMapper;


import com.model.UserSkillVO;
import com.user_manager_service.form.CreateUserSkillRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "default")
public interface CreateSkillRequestMapper {

    CreateSkillRequestMapper INSTANCE = Mappers.getMapper(CreateSkillRequestMapper.class);

    @Mapping(source = "certificateUrl", target = "certificatePath")
    UserSkillVO map(CreateUserSkillRequest createUserSkillRequest);
}
