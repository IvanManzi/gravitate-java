package com.user_manager_service.service;


import com.model.UserVO;
import org.springframework.http.ResponseEntity;

public interface GravitateUserManagerService {

    ResponseEntity createGravitateUser(UserVO userVO);
    ResponseEntity getAllGravitateUsersByManagerId(Long managerId);
    ResponseEntity updateGravitateUser(UserVO userVO);
    ResponseEntity deleteGravitateUser(Long userId);
    ResponseEntity getGravitateUserByUsername(String username);
    ResponseEntity updateGravitateUserPassword(UserVO userVO);

}
