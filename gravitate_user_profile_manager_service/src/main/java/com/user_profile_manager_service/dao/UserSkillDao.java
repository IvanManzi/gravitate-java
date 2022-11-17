package com.user_profile_manager_service.dao;

import com.model.UserSkillVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserSkillDao {

    int saveSkill(UserSkillVO userSkillVO);

    List<UserSkillVO> getAllUserSkills(Long userId);

    int updateUserSkill(UserSkillVO userSkillVO);

    int deleteUserSkill(Long skillId);

}
