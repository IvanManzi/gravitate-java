package com.user_manager_service.dao;

import com.model.SecurityQuestionVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SecurityQuestionDao {

    int saveSecurityQuestion(SecurityQuestionVO securityQuestionVO);
    int checkIfUserHasSecurityQuestion(Long userId);
    SecurityQuestionVO getSecurityQuestionByUserId(Long userId);
    int updateSecurityQuestion(SecurityQuestionVO securityQuestionVO);
    int deleteSecurityQuestion(Long securityQuestionId);
    int verifySecurityQuestionAnswer(SecurityQuestionVO securityQuestionVO);
}
