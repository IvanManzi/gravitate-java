package com.content_manager_service.controller;


import com.content_manager_service.form.CreateUserRoleRequest;
import com.content_manager_service.form.UpdateUserRoleRequest;
import com.content_manager_service.service.GravitateUserRoleService;
import com.model.RoleVO;
import com.util.APIResponse;
import com.util.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@RestController
@RequestMapping("api/v1/content/role")
@RequiredArgsConstructor
public class GravitateUserRoleController {

    private final GravitateUserRoleService gravitateUserRoleService;

    @PostMapping(value = "/create")
    public ResponseEntity<APIResponse> createRole(@Valid @RequestBody CreateUserRoleRequest createUserRoleRequest,HttpServletRequest request){
        RoleVO roleVO = new RoleVO();
        String token = request.getHeader(AUTHORIZATION).substring("Bearer ".length());
        String userId = JwtUtils.getUserIdFromJwtToken(token);
        roleVO.setAdminId(Long.valueOf(userId));
        roleVO.setRoleName(createUserRoleRequest.name());
        roleVO.setRoleKRA(createUserRoleRequest.kra());
        APIResponse apiResponse = gravitateUserRoleService.createUserRole(roleVO);
        return ResponseEntity.ok(
                APIResponse.builder()
                        .statusCode(apiResponse.getStatusCode())
                        .data(apiResponse.getData())
                        .build()
        );
    }

    @GetMapping(value = "/all")
    public ResponseEntity<APIResponse> getAllRoles(HttpServletRequest request){
        String token = request.getHeader(AUTHORIZATION).substring("Bearer ".length());
        String userId = JwtUtils.getUserIdFromJwtToken(token);
        APIResponse apiResponse = gravitateUserRoleService.getAllUserRoles(Long.valueOf(userId));
        return ResponseEntity.ok(
                APIResponse.builder()
                        .statusCode(apiResponse.getStatusCode())
                        .data(apiResponse.getData())
                        .build()
        );
    }

    @PutMapping(value = "/")
    public ResponseEntity<APIResponse> updateRole(@Valid @RequestBody UpdateUserRoleRequest updateUserRoleRequest,HttpServletRequest request){
        RoleVO roleVO = new RoleVO();
        String token = request.getHeader(AUTHORIZATION).substring("Bearer ".length());
        String userId = JwtUtils.getUserIdFromJwtToken(token);
        roleVO.setAdminId(Long.valueOf(userId));
        roleVO.setRoleId(updateUserRoleRequest.roleId());
        roleVO.setRoleName(updateUserRoleRequest.name());
        roleVO.setRoleKRA(updateUserRoleRequest.kra());
        APIResponse apiResponse = gravitateUserRoleService.updateUserRole(roleVO);
        return ResponseEntity.ok(
                APIResponse.builder()
                        .statusCode(apiResponse.getStatusCode())
                        .data(apiResponse.getData())
                        .build()
        );
    }

    @DeleteMapping(value = "/{roleId}")
    public ResponseEntity<APIResponse> deleteRole(@PathVariable("roleId") Long roleId){
        APIResponse apiResponse = gravitateUserRoleService.deleteUserRole(roleId);
        return ResponseEntity.ok(
                APIResponse.builder()
                        .statusCode(apiResponse.getStatusCode())
                        .data(apiResponse.getData())
                        .build()
        );
    }


}
