package com.user_manager_service.dao;


import com.model.RoleVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Collection;

@Mapper
public interface RoleDao {

    int createRole(RoleVO roleVO);
    Collection<RoleVO> getGravitateUserRole(@Param("userId") Long userId);

    RoleVO getRoleById(@Param("uuid") Long roleId);
}
