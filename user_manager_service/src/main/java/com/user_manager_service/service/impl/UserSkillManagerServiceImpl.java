package com.user_manager_service.service.impl;

import com.model.UserSkillVO;
import com.user_manager_service.dao.UserSkillDao;
import com.user_manager_service.service.UserSkillManagerService;
import com.util.APIResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class UserSkillManagerServiceImpl implements UserSkillManagerService {

    private final UserSkillDao userSkillDao;

    @Override
    public ResponseEntity createUserSkill(UserSkillVO userSkillVO) {
        int result = userSkillDao.saveUserSkill(userSkillVO);
        if(result > 0){
            return APIResponse.resultSuccess("Skill successfully saved.");
        }else{
            return APIResponse.resultFail();
        }
    }

    @Override
    public ResponseEntity getAllUserSkills(Long userId) {
        List<UserSkillVO> userSkills = userSkillDao.getAllUserSkills(userId);
        if(userSkills.isEmpty()){
            return APIResponse.resourceNotFound();
        }else{
            Map<String,Object> data = new HashMap<>();
            data.put("USER_SKILLS",userSkills);
            return APIResponse.resultSuccess(data);
        }
    }

    @Override
    public ResponseEntity getUserSkillsByQuarter(Long userId,Integer year, Integer quarter) {
        List<UserSkillVO> userSkills = userSkillDao.getSkillsByQuarter(userId,year,quarter);
        if(userSkills.isEmpty()){
            return APIResponse.resourceNotFound();
        }
        Map<String,Object> data = new HashMap<>();
        data.put("USER_SKILLS", userSkills);
        return APIResponse.resultSuccess(data);
    }

    @Override
    public ResponseEntity updateUserSkill(UserSkillVO userSkillVO) {
        int result = userSkillDao.updateUserSkill(userSkillVO);
        if(result > 0){
            return APIResponse.resultSuccess("User skill successfully updated. ");
        }else{
            return APIResponse.resultFail();
        }
    }

    @Override
    public ResponseEntity deleteUserSkill(Long skillId) {
        int result = userSkillDao.deleteUserSkill(skillId);
        if(result > 0){
            return APIResponse.resultSuccess("Skill successfully deleted.");
        }else{
            return APIResponse.resultFail();
        }
    }
}
