package com.user_manager_service.service;

import com.util.APIResponse;
import com.model.UserManagerVO;
import com.model.UserVO;

public interface GravitateUserManagerService {

    APIResponse createGravitateUser(UserVO userVO, UserManagerVO userManagerVO);
    APIResponse getAllGravitateUsersByManagerId(Long managerId);
    APIResponse updateGravitateUser(UserVO userVO);
    APIResponse deleteGravitateUser(Long userId);
    APIResponse getGravitateUserByUsername(String username);

}
