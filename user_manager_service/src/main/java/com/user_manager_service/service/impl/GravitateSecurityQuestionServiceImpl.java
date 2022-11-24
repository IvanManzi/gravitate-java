package com.user_manager_service.service.impl;

import com.model.SecurityQuestionVO;
import com.user_manager_service.dao.SecurityQuestionDao;
import com.user_manager_service.service.GravitateSecurityQuestionService;
import com.util.APIResponse;
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

    @Override
    public ResponseEntity createSecurityQuestion(SecurityQuestionVO securityQuestionVO) {
        int result = securityQuestionDao.saveSecurityQuestion(securityQuestionVO);
        if(result > 0){
            return APIResponse.resultSuccess("Security question successfully created. ");
        }else{
            return APIResponse.resultFail();
        }
    }

    @Override
    public ResponseEntity getUserSecurityQuestion(Long userId) {
        List<SecurityQuestionVO> securityQuestions = securityQuestionDao.getSecurityQuestionByUserId(userId);
        if(securityQuestions.isEmpty()){
            return APIResponse.resourceNotFound();
        }else{
            Map<String,Object> data = new HashMap<>();
            data.put("SECURITY_QUESTION",securityQuestions);
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
}
