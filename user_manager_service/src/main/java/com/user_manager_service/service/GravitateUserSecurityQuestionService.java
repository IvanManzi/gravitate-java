package com.user_manager_service.service;

import com.model.UserSecurityQuestionVO;
import org.springframework.http.ResponseEntity;

public interface GravitateUserSecurityQuestionService {

    ResponseEntity createUserSecurityQuestion(UserSecurityQuestionVO userSecurityQuestionVO);

    ResponseEntity getUserSecurityQuestion(Long userId);

    ResponseEntity updateUserSecurityQuestion(UserSecurityQuestionVO userSecurityQuestionVO);

    ResponseEntity deleteUserSecurityQuestion(Long securityQuestionId);
}
