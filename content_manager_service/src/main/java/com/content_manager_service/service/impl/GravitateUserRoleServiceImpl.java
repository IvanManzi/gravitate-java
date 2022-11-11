package com.content_manager_service.service.impl;


import com.content_manager_service.dao.RoleDao;
import com.content_manager_service.service.GravitateUserRoleService;
import com.model.RoleVO;
import com.util.APIResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class GravitateUserRoleServiceImpl implements GravitateUserRoleService {

    private final RoleDao roleDao;

    @Override
    public ResponseEntity createUserRole(RoleVO roleVO) {
        int result = roleDao.createGravitateUserRole(roleVO);
        if(result > 0){
            return APIResponse.resultSuccess("Role successfully created. ");
        }else{
            return APIResponse.resultFail();
        }
    }

    @Override
    public ResponseEntity getAllUserRoles(Long userId) {
        List<RoleVO> roles = roleDao.getAllRoles(userId);
        if(roles.isEmpty()){
            return APIResponse.resultFail("No roles found. ");
        }else{
            Map<String,Object> data = new HashMap<>();
            data.put("ROLES",roles);
            return APIResponse.resultSuccess(data);
        }
    }

    @Override
    public ResponseEntity updateUserRole(RoleVO roleVO) {
        int result = roleDao.updateGravitateUserRole(roleVO);
        if(result > 0){
            return APIResponse.resultSuccess("Role successfully updated. ");
        }else{
            return APIResponse.resultFail();
        }
    }

    @Override
    public ResponseEntity deleteUserRole(Long roleId) {
        int result = roleDao.deleteGravitateUserRole(roleId);
        if(result > 0){
            return APIResponse.resultSuccess("Role successfully deleted. ");
        }else{
            return APIResponse.resultFail();
        }
    }
}
