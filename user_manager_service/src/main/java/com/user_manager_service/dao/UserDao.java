package com.user_manager_service.dao;

import com.model.UserVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserDao {
    int createGravitateUser(UserVO userVO);
    int checkIfUsernameExists(String username);
    UserVO getGravitateUserByUsername(String username);
    int deleteGravitateUser(Long userId);
    int updateGravitateUserPassword(UserVO userVO);


}
