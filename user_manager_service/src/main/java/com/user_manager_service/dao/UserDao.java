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
    Map getGravitateUserInfo(String username);
    int deleteGravitateUser(Long userId);
    int updateGravitateUserPassword(UserVO userVO);
    List<Map> getAllGravitateUsers(@Param("search") String search, @Param("roleId") Long roleId );
    UserVO getGravitateUserById(Long userId);
    List<Map> getGravitateUserTeamMembers(@Param("userLevel") String userLevel,
                                          @Param("userId") Long userId,@Param("manager") Long manager);
    List<UserVO> getGravitateManagerUsers(String search);
    int updateUserStatus(@Param("userId") Long userId,@Param("status") boolean status);
    int getUserTotalEmployeeReferrals(Long userId);

    int getUserTotalHotOpportunities(Long userId);

    int getUserTotalBilling(Object userId);

    List<Map> getUserWishes(Long userId);

    List<Map> getUserPerformance(Long userId);
}
