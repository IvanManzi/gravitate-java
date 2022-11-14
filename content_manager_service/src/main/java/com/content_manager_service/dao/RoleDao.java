package com.content_manager_service.dao;

import com.model.RoleVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RoleDao {

    int createGravitateUserRole(RoleVO roleVO);
    List<RoleVO> getAllRoles(Long adminId);
    int updateGravitateUserRole(RoleVO roleVO);
    int deleteGravitateUserRole(Long roleId);
}
