package com.user_manager_service.service;


import com.model.SecurityQuestionVO;
import com.model.UserVO;
import com.util.APIResponse;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.util.List;

public interface GravitateUserManagerService {

    ResponseEntity createGravitateUser(UserVO userVO, List<Long> projects, String token);
    ResponseEntity getAllGravitateUsers(String search,Long roleId);
    ResponseEntity getGravitateUserById(Long userId);
    ResponseEntity updateGravitateUser(UserVO userVO,List<Long> projects, String token);
    ResponseEntity deleteGravitateUser(Long userId);
    ResponseEntity getGravitateUserByInfo(String username);
    ResponseEntity updateGravitateUserPassword(UserVO userVO, String oldPassword);
    ResponseEntity<APIResponse> updateGravitateUserPassword(UserVO userVO);
    ResponseEntity getGravitateUserTeamMembers(Long userId);
    ResponseEntity getGravitateManagerUsers(String search);
    ResponseEntity updateGravitateUserAccountStatus(Long userId, boolean status);

}
