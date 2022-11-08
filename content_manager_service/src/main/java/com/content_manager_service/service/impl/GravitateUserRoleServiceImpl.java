package com.content_manager_service.service.impl;


import com.content_manager_service.dao.RoleDao;
import com.content_manager_service.service.GravitateUserRoleService;
import com.model.RoleVO;
import com.util.APIResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GravitateUserRoleServiceImpl implements GravitateUserRoleService {

    private final RoleDao roleDao;

    @Override
    public APIResponse createUserRole(RoleVO roleVO) {
        return null;
    }

    @Override
    public APIResponse getAllUserRoles(Long userId) {
        return null;
    }

    @Override
    public APIResponse updateUserRole(RoleVO roleVO) {
        return null;
    }

    @Override
    public APIResponse deleteUserRole(Long roleId) {
        return null;
    }
}
