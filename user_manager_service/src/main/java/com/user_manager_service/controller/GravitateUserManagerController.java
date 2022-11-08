package com.user_manager_service.controller;

import com.util.APIResponse;
import com.model.UserManagerVO;
import com.model.UserVO;
import com.user_manager_service.form.CreateGravitateUserForm;
import com.user_manager_service.service.GravitateUserManagerService;
import com.util.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@RestController
@RequestMapping(value = "api/v1/user", produces = "application/json")
@RequiredArgsConstructor
public class GravitateUserManagerController {

    private final GravitateUserManagerService gravitateUserManagerService;

    private final PasswordEncoder passwordEncoder;

    @PostMapping(value = "/create")
    public ResponseEntity<APIResponse> createGravitateUser(@RequestBody CreateGravitateUserForm createGravitateUserForm){
        UserVO userVO = new UserVO();
        UserManagerVO userManagerVO = new UserManagerVO();
        //create user obj
        userVO.setUserType(createGravitateUserForm.userType());
        userVO.setEmail(createGravitateUserForm.email());
        userVO.setPassword(passwordEncoder.encode(createGravitateUserForm.password()));
        userVO.setAlternateEmail(createGravitateUserForm.alternativeEmail());
        userVO.setFirstName(createGravitateUserForm.firstName());
        userVO.setLastName(createGravitateUserForm.lastName());
        userVO.setCountry(createGravitateUserForm.country());
        userVO.setDateOfBirth(createGravitateUserForm.dob());
        userVO.setJoiningDate(createGravitateUserForm.joinedOn());
        userVO.setPhoneNumber(createGravitateUserForm.phoneNumber());
        userVO.setProfilePicturePath(createGravitateUserForm.profilePicture());
        userVO.setContractPath(createGravitateUserForm.contractPath());
        userVO.setEmploymentStatus(createGravitateUserForm.employmentStatus());
        userVO.setBankName(createGravitateUserForm.bankName());
        userVO.setAccountNumber(createGravitateUserForm.accountNumber());
        userVO.setRoleId(createGravitateUserForm.roleId());
        userVO.setBilling(createGravitateUserForm.billing());
        //create user manager object
        userManagerVO.setManagerId(createGravitateUserForm.managerId());
        APIResponse apiResponse = gravitateUserManagerService.createGravitateUser(userVO,userManagerVO);
        return ResponseEntity.ok(
                APIResponse.builder()
                        .statusCode(apiResponse.getStatusCode())
                        .data(apiResponse.getData())
                        .build()
        );
    }
    @GetMapping(value = "/")
    public ResponseEntity<APIResponse> getAllUsersByManagerId(@RequestParam("managerId") Long managerId){
        APIResponse apiResponse = gravitateUserManagerService.getAllGravitateUsersByManagerId(managerId);
        return ResponseEntity.ok(
                APIResponse.builder()
                        .statusCode(apiResponse.getStatusCode())
                        .data(apiResponse.getData())
                        .build()
        );
    }

    @GetMapping(value = "/profile")
    public ResponseEntity<APIResponse> getGravitateUsernameProfile(HttpServletRequest request){
        String token = request.getHeader(AUTHORIZATION).substring("Bearer ".length());
        String username = JwtUtils.getUserNameFromJwtToken(token);
        APIResponse apiResponse = gravitateUserManagerService.getGravitateUserByUsername(username);
        return ResponseEntity.ok(
                APIResponse.builder()
                        .statusCode(apiResponse.getStatusCode())
                        .data(apiResponse.getData())
                        .build()
        );
    }

    @PutMapping(value = "/update")
    public ResponseEntity<APIResponse> updateGravitateUser(){
        return null;
    }

    @DeleteMapping(value = "/{userId}")
    public ResponseEntity<APIResponse> deleteGravitateUser(Long userId){
        APIResponse apiResponse = gravitateUserManagerService.deleteGravitateUser(userId);
        return ResponseEntity.ok(
                APIResponse.builder()
                        .statusCode(apiResponse.getStatusCode())
                        .data(apiResponse.getData())
                        .build()
        );
    }


}
