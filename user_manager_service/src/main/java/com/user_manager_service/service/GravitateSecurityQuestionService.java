package com.user_manager_service.service;

import com.model.SecurityQuestionVO;
import com.model.UserVO;
import org.springframework.http.ResponseEntity;

public interface GravitateSecurityQuestionService {

    ResponseEntity createSecurityQuestion(SecurityQuestionVO securityQuestionVO);

    ResponseEntity getUserSecurityQuestion(Long userId);

    ResponseEntity updateSecurityQuestion(SecurityQuestionVO securityQuestionVO);

    ResponseEntity deleteSecurityQuestion(Long securityQuestionId);

    ResponseEntity verifySecurityQuestionAnswer(SecurityQuestionVO securityQuestionVO);

    ResponseEntity verifySecurityQuestionAnswer(UserVO userVO, SecurityQuestionVO securityQuestionVO);
}
