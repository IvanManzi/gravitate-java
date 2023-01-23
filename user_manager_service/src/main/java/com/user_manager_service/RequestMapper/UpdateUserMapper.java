package com.user_manager_service.RequestMapper;

import com.model.UserVO;
import com.user_manager_service.form.UpdateGravitateUserForm;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UpdateUserMapper {


    UpdateUserMapper INSTANCE = Mappers.getMapper(UpdateUserMapper.class);

    @Mapping(source = "dateOfBirth", target = "dob")
    @Mapping(source = "adminPageAccess", target = "pageAccess")
    @Mapping(source = "managedBy", target = "managerId")
    @Mapping(source = "joiningDate", target = "joinedOn")
    @Mapping(source = "profilePicturePath", target = "profilePicture")
    UpdateGravitateUserForm updateGravitateUserDto(UserVO userVO);
}
