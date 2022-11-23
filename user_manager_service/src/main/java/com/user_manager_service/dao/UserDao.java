package com.user_manager_service.dao;

import com.model.UserVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface UserDao {
    int createGravitateUser(UserVO userVO);
    int checkIfUsernameExists(String username);
    UserVO getGravitateUserByUsername(String username);
    int deleteGravitateUser(Long userId);
    int updateGravitateUserPassword(UserVO userVO);
    List<Map> getAllGravitateUsers(@Param("search") String search, @Param("roleId") Long roleId );
}
