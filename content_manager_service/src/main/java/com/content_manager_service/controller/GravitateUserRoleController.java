package com.content_manager_service.controller;


import com.content_manager_service.form.CreateUserRoleRequest;
import com.content_manager_service.form.UpdateUserRoleRequest;
import com.content_manager_service.service.GravitateUserRoleService;
import com.model.RoleVO;
import com.util.APIResponse;
import com.util.JwtUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import java.io.IOException;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@RestController
@RequestMapping("api/v1/content/role")
@RequiredArgsConstructor
public class GravitateUserRoleController {

    private final GravitateUserRoleService gravitateUserRoleService;

    @PostMapping(value = "/create")
    public ResponseEntity<APIResponse> createRole(@Valid @RequestBody CreateUserRoleRequest createUserRoleRequest,HttpServletRequest request) throws IOException {
        RoleVO roleVO = new RoleVO();
        String token = request.getHeader(AUTHORIZATION).substring("Bearer ".length());
        String userId = JwtUtils.getUserIdFromJwtToken(token);
        roleVO.setAdminId(Long.valueOf(userId));
        roleVO.setRoleName(createUserRoleRequest.name());
        roleVO.setRoleKRA(createUserRoleRequest.kra());
        return gravitateUserRoleService.createUserRole(roleVO);
    }

    @GetMapping(value = "/all")
    public ResponseEntity<APIResponse> getAllRoles(HttpServletRequest request) throws IOException {
        String token = request.getHeader(AUTHORIZATION).substring("Bearer ".length());
        String userId = JwtUtils.getUserIdFromJwtToken(token);
        return gravitateUserRoleService.getAllUserRoles(Long.valueOf(userId));
    }

    @PutMapping(value = "/")
    public ResponseEntity<APIResponse> updateRole(@Valid @RequestBody UpdateUserRoleRequest updateUserRoleRequest,HttpServletRequest request) throws IOException {
        RoleVO roleVO = new RoleVO();
        String token = request.getHeader(AUTHORIZATION).substring("Bearer ".length());
        String userId = JwtUtils.getUserIdFromJwtToken(token);
        roleVO.setAdminId(Long.valueOf(userId));
        roleVO.setRoleId(updateUserRoleRequest.roleId());
        roleVO.setRoleName(updateUserRoleRequest.name());
        roleVO.setRoleKRA(updateUserRoleRequest.kra());
        return gravitateUserRoleService.updateUserRole(roleVO);
    }

    @DeleteMapping(value = "/{roleId}")
    public ResponseEntity<APIResponse> deleteRole(@PathVariable("roleId") Long roleId){
        return gravitateUserRoleService.deleteUserRole(roleId);
    }


}
