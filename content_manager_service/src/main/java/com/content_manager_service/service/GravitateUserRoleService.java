package com.content_manager_service.service;

import com.model.RoleVO;
import org.springframework.http.ResponseEntity;

public interface GravitateUserRoleService {

    ResponseEntity createUserRole(RoleVO roleVO);

    ResponseEntity getAllUserRoles(Long userId);

    ResponseEntity updateUserRole(RoleVO roleVO);

    ResponseEntity deleteUserRole(Long roleId);
}
