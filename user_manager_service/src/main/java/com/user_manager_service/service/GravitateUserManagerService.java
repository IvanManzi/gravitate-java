package com.user_manager_service.service;


import com.model.UserVO;
import com.util.APIResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface GravitateUserManagerService {

    ResponseEntity createGravitateUser(UserVO userVO, List<Long> projects, String token);
    ResponseEntity getAllGravitateUsers(String search,Long roleId);
    ResponseEntity getGravitateUserById(Long userId);
    ResponseEntity updateGravitateUser(UserVO userVO,List<Long> projects, String token);
    ResponseEntity deleteGravitateUser(Long userId);
    ResponseEntity getGravitateUserInfoByUsername(String username, String token,String userLevel);
    ResponseEntity updateGravitateUserPassword(UserVO userVO, String oldPassword);
    ResponseEntity<APIResponse> updateGravitateUserPassword(UserVO userVO);
    ResponseEntity getGravitateUserTeamMembers(String userLevel,Long userId);
    ResponseEntity getGravitateManagerUsers(String search);
    ResponseEntity updateGravitateUserAccountStatus(Long userId, boolean status);

}
