package com.user_manager_service.controller;

import com.model.UserManagerVO;
import com.model.UserVO;
import com.user_manager_service.form.CreateGravitateUserForm;
import com.user_manager_service.service.GravitateUserManagerService;
import com.util.APIResponse;
import com.util.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

import java.io.IOException;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@RestController
@RequestMapping(value = "api/v1/user", produces = "application/json")
@RequiredArgsConstructor
public class GravitateUserManagerController {

    private final GravitateUserManagerService gravitateUserManagerService;

    private final PasswordEncoder passwordEncoder;

    @PostMapping(value = "/create")
    public ResponseEntity<?> createGravitateUser(@RequestBody CreateGravitateUserForm createGravitateUserForm){
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
        userVO.setBilling(createGravitateUserForm.billing());
        userVO.setIsAdmin(createGravitateUserForm.isAdmin());
        if(userVO.getIsAdmin() != 1){
            userVO.setRoleId(createGravitateUserForm.roleId());
            userManagerVO.setAdminId(createGravitateUserForm.managerId());
        }
        //create user manager object
        return gravitateUserManagerService.createGravitateUser(userVO,userManagerVO);
    }
    @GetMapping(value = "/all")
    public ResponseEntity getAllUsersByManagerId(HttpServletRequest request) throws IOException{
        String token = request.getHeader(AUTHORIZATION).substring("Bearer ".length());
        String userId = JwtUtils.getUserIdFromJwtToken(token);
        return gravitateUserManagerService.getAllGravitateUsersByManagerId(Long.valueOf(userId));
    }

    @GetMapping(value = "/profile")
    public ResponseEntity getGravitateUsernameProfile(HttpServletRequest request) throws IOException {
        String token = request.getHeader(AUTHORIZATION).substring("Bearer ".length());
        String username = JwtUtils.getUserNameFromToken(token);
        return gravitateUserManagerService.getGravitateUserByUsername(username);
    }

    @PutMapping(value = "/update")
    public ResponseEntity updateGravitateUser(){
        return null;
    }

    @DeleteMapping(value = "/{userId}")
    public ResponseEntity deleteGravitateUser(Long userId){
        return gravitateUserManagerService.deleteGravitateUser(userId);
    }


}
