package com.user_manager_service.dao;

import com.model.UserVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface UserDao {
    int createGravitateUser(UserVO userVO);

    int updateGravitateUser(UserVO userVO);
    int checkIfUsernameExists(String username);
    UserVO getGravitateUserByUsername(String username);
    int deleteGravitateUser(Long userId);
    int updateGravitateUserPassword(UserVO userVO);
    List<UserVO> getAllGravitateUsers(@Param("search") String search, @Param("roleId") Long roleId );
    List<Map> getGravitateUserTeamMembers();
    List<UserVO> getGravitateManagerUsers(String search);
    int updateUserStatus(Long userId);
}
