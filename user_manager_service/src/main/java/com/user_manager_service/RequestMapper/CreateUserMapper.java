package com.user_manager_service.RequestMapper;


import com.model.UserVO;
import com.user_manager_service.form.CreateGravitateUserForm;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CreateUserMapper {

    CreateUserMapper INSTANCE = Mappers.getMapper(CreateUserMapper.class);

    @Mapping(source = "dateOfBirth", target = "dob")
    @Mapping(source = "adminPageAccess", target = "pageAccess")
    @Mapping(source = "managedBy", target = "managerId")
    @Mapping(source = "joiningDate", target = "joinedOn")
    @Mapping(source = "profilePicturePath", target = "profilePicture")
    UserVO toUserVO(CreateGravitateUserForm createGravitateUserForm);
}

