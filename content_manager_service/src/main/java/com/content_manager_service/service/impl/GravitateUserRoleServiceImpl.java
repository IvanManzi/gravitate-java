package com.content_manager_service.service.impl;


import com.content_manager_service.dao.RoleDao;
import com.content_manager_service.service.GravitateUserRoleService;
import com.model.RoleVO;
import com.util.APIResponse;
import com.util.Constants;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.util.Constants.*;

@Service
@RequiredArgsConstructor
public class GravitateUserRoleServiceImpl implements GravitateUserRoleService {

    private final RoleDao roleDao;

    @Override
    public APIResponse createUserRole(RoleVO roleVO) {
        int result = roleDao.createGravitateUserRole(roleVO);
        if(result > 0){
            return new APIResponse(RESOURCE_CREATED,"Role successfully created. ");
        }else{
            return new APIResponse(BAD_REQUEST);
        }
    }

    @Override
    public APIResponse getAllUserRoles(Long userId) {
        List<RoleVO> roles = roleDao.getAllRoles(userId);
        if(roles.isEmpty()){
            return new APIResponse(NOT_FOUND,"No roles found. ");
        }else{
            Map<String,Object> data = new HashMap<>();
            data.put("ROLES",roles);
            return new APIResponse(REQUEST_OK,data);
        }
    }

    @Override
    public APIResponse updateUserRole(RoleVO roleVO) {
        int result = roleDao.updateGravitateUserRole(roleVO);
        if(result > 0){
            return new APIResponse(REQUEST_OK,"Role successfully created. ");
        }else{
            return new APIResponse(BAD_REQUEST);
        }
    }

    @Override
    public APIResponse deleteUserRole(Long roleId) {
        int result = roleDao.deleteGravitateUserRole(roleId);
        if(result > 0){
            return new APIResponse(REQUEST_OK,"Role successfully deleted. ");
        }else{
            return new APIResponse(BAD_REQUEST);
        }
    }
}
