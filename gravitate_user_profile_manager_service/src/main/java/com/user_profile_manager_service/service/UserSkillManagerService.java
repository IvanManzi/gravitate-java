package com.user_profile_manager_service.service;

import com.model.UserSkillVO;
import org.springframework.http.ResponseEntity;

public interface UserSkillManagerService {

    ResponseEntity createUserSkill(UserSkillVO userSkillVO);

    ResponseEntity getAllUserSkills(Long userId);

    ResponseEntity updateUserSkill(UserSkillVO userSkillVO);

    ResponseEntity deleteUserSkill(Long skillId);
}
