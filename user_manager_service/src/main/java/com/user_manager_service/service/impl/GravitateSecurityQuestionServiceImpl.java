package com.user_manager_service.service.impl;

import com.model.SecurityQuestionVO;
import com.model.UserVO;
import com.user_manager_service.dao.SecurityQuestionDao;
import com.user_manager_service.dao.UserDao;
import com.user_manager_service.service.GravitateSecurityQuestionService;
import com.util.APIResponse;
import com.util.ValidationUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class GravitateSecurityQuestionServiceImpl implements GravitateSecurityQuestionService {

    private final SecurityQuestionDao securityQuestionDao;
    private final UserDao userDao;

    @Override
    public ResponseEntity createSecurityQuestion(SecurityQuestionVO securityQuestionVO) {
        //check if user already has a security question
        int check = securityQuestionDao.checkIfUserHasSecurityQuestion(securityQuestionVO.getUserId());
        if(check == 1){
            return APIResponse.resultFail("User already has security question. ");
        }
        int result = securityQuestionDao.saveSecurityQuestion(securityQuestionVO);
        if(result > 0){
            return APIResponse.resultSuccess("Security question successfully created. ");
        }else{
            return APIResponse.resultFail();
        }
    }

    @Override
    public ResponseEntity getUserSecurityQuestion(Long userId) {
        SecurityQuestionVO securityQuestion = securityQuestionDao.getSecurityQuestionByUserId(userId);
        if(ValidationUtil.isNullObject(securityQuestion)) {
            return APIResponse.resourceNotFound();
        }else{
            Map<String,Object> data = new HashMap<>();
            data.put("SECURITY_QUESTION",securityQuestion);
            return APIResponse.resultSuccess(data);
        }
    }

    @Override
    public ResponseEntity updateSecurityQuestion(SecurityQuestionVO securityQuestionVO) {
        int result = securityQuestionDao.updateSecurityQuestion(securityQuestionVO);
        if(result > 0){
            return APIResponse.resultSuccess("Security question successfully updated.");
        }else{
            return APIResponse.resultFail();
        }
    }

    @Override
    public ResponseEntity deleteSecurityQuestion(Long securityQuestionId) {
        int result = securityQuestionDao.deleteSecurityQuestion(securityQuestionId);
        if(result > 0){
            return APIResponse.resultSuccess("Security question successfully deleted.");
        }else{
            return APIResponse.resultFail();
        }
    }

    @Override
    public ResponseEntity verifySecurityQuestionAnswer(SecurityQuestionVO securityQuestionVO) {
        int check = securityQuestionDao.verifySecurityQuestionAnswer(securityQuestionVO);
        if(check == 1){
            return APIResponse.resultSuccess("Verification success.");
        }else{
            return APIResponse.resultFail("Verification failed.");
        }
    }

    @Override
    public ResponseEntity verifySecurityQuestionAnswer(UserVO userVO, SecurityQuestionVO securityQuestionVO) {
        UserVO user = userDao.getGravitateUserByUsername(userVO.getEmail());
        if(ValidationUtil.isNullObject(user)){
            return APIResponse.resultFail("Invalid Email ID.");
        }
        SecurityQuestionVO securityQuestion = securityQuestionDao.getSecurityQuestionByUserId(user.getUserId());
        if(ValidationUtil.isNullObject(securityQuestion)){
            return APIResponse.resultFail("User doesn't have security question. ");
        }
        //check if answers match
        if(securityQuestion.getAnswer().equals(securityQuestionVO.getAnswer())){
            return APIResponse.resultSuccess("Security question successfully verified. ");
        }
        return APIResponse.resultFail("Invalid security question answer. ");
    }
}
