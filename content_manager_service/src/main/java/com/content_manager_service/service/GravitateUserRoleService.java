package com.content_manager_service.service;

import com.model.RoleVO;
import com.util.APIResponse;

public interface GravitateUserRoleService {

    APIResponse createUserRole(RoleVO roleVO);

    APIResponse getAllUserRoles(Long userId);

    APIResponse updateUserRole(RoleVO roleVO);

    APIResponse deleteUserRole(Long roleId);
}
