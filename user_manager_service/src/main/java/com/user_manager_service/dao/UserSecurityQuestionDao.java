package com.user_manager_service.dao;

import com.model.UserSecurityQuestionVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserSecurityQuestionDao {

    int saveUserSecurityQuestion(UserSecurityQuestionVO userSecurityQuestionVO);

    List<UserSecurityQuestionVO> getSecurityQuestionByUserId(Long userId);

    int updateUserSecurityQuestion(UserSecurityQuestionVO userSecurityQuestionVO);

    int deleteUserSecurityQuestion(Long securityQuestionId);
}
