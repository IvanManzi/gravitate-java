package com.user_manager_service.service.impl;

import com.model.UserSecurityQuestionVO;
import com.user_manager_service.dao.UserSecurityQuestionDao;
import com.user_manager_service.service.GravitateUserSecurityQuestionService;
import com.util.APIResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class GravitateUserSecurityQuestionServiceImpl implements GravitateUserSecurityQuestionService {

    private final UserSecurityQuestionDao userSecurityQuestionDao;

    @Override
    public ResponseEntity createUserSecurityQuestion(UserSecurityQuestionVO userSecurityQuestionVO) {
        int result = userSecurityQuestionDao.saveUserSecurityQuestion(userSecurityQuestionVO);
        if(result > 0){
            return APIResponse.resultSuccess("Security question successfully created. ");
        }else{
            return APIResponse.resultFail();
        }
    }

    @Override
    public ResponseEntity getUserSecurityQuestion(Long userId) {
        List<UserSecurityQuestionVO> securityQuestions = userSecurityQuestionDao.getSecurityQuestionByUserId(userId);
        if(securityQuestions.isEmpty()){
            return APIResponse.resourceNotFound();
        }else{
            Map<String,Object> data = new HashMap<>();
            data.put("SECURITY_QUESTION",securityQuestions);
            return APIResponse.resultSuccess(data);
        }
    }

    @Override
    public ResponseEntity updateUserSecurityQuestion(UserSecurityQuestionVO userSecurityQuestionVO) {
        int result = userSecurityQuestionDao.updateUserSecurityQuestion(userSecurityQuestionVO);
        if(result > 0){
            return APIResponse.resultSuccess("Security question successfully created.");
        }else{
            return APIResponse.resultFail();
        }
    }

    @Override
    public ResponseEntity deleteUserSecurityQuestion(Long securityQuestionId) {
        int result = userSecurityQuestionDao.deleteUserSecurityQuestion(securityQuestionId);
        if(result > 0){
            return APIResponse.resultSuccess("Security question successfully deleted.");
        }else{
            return APIResponse.resultFail();
        }
    }
}
