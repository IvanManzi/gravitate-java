package com.user_manager_service.service;


import com.model.UserVO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface GravitateUserManagerService {

    ResponseEntity createGravitateUser(UserVO userVO, List<Long> projects, String token);
    ResponseEntity getAllGravitateUsers(String search,Long roleId);
    ResponseEntity getGravitateUserById(Long userId);
    ResponseEntity updateGravitateUser(UserVO userVO,List<Long> projects, String token);
    ResponseEntity deleteGravitateUser(Long userId);
    ResponseEntity getGravitateUserByUsername(String username);
    ResponseEntity updateGravitateUserPassword(UserVO userVO);
    ResponseEntity getGravitateUserTeamMembers();
    ResponseEntity getGravitateManagerUsers(String search);
    ResponseEntity disableGravitateUserAccount(Long userId);

}
