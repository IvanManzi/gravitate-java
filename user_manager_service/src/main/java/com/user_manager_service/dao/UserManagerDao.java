package com.user_manager_service.dao;

import com.model.UserManagerVO;
import com.model.UserVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserManagerDao {
    int createUserManagerRecord(UserManagerVO userManagerVO);

    List<UserVO> getGravitateUsersByManagerId(Long managerId);
}
