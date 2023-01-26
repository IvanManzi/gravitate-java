package com.user_manager_service.RequestMapper;

import com.model.UserVO;
import com.user_manager_service.form.UpdateGravitateUserForm;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "default")
public interface UpdateUserMapper {


    UpdateUserMapper INSTANCE = Mappers.getMapper(UpdateUserMapper.class);

    @Mapping(source = "dob", target = "dateOfBirth")
    @Mapping(source = "pageAccess", target = "adminPageAccess")
    @Mapping(source = "managerId", target = "managedBy")
    @Mapping(source = "joinedOn", target = "joiningDate")
    @Mapping(source = "profilePicture", target = "profilePicturePath")
    @Mapping(source = "alternativeEmail", target = "alternateEmail")
    UserVO map(UpdateGravitateUserForm updateGravitateUserForm);
}
