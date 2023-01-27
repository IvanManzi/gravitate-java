package com.user_manager_service.controller;

import com.mashape.unirest.http.exceptions.UnirestException;
import com.model.EmailDetailsV0;
import com.model.UserVO;
import com.user_manager_service.RequestMapper.CreateGravitateUserFormMapper;
import com.user_manager_service.RequestMapper.UpdateUserMapper;
import com.user_manager_service.form.CreateGravitateUserForm;
import com.user_manager_service.form.ForgetPasswordRequest;
import com.user_manager_service.form.UpdateGravitateUserForm;
import com.user_manager_service.form.UpdateGravitateUserPasswordRequest;
import com.user_manager_service.service.GravitateUserManagerService;
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
    public ResponseEntity<?> createGravitateUser(@RequestBody CreateGravitateUserForm createGravitateUserForm, HttpServletRequest request) throws IOException, UnirestException {
        UserVO userVO = CreateGravitateUserFormMapper.INSTANCE.map(createGravitateUserForm);
        userVO.setPassword(passwordEncoder.encode(createGravitateUserForm.otp()));
        String token = request.getHeader(AUTHORIZATION).substring("Bearer ".length());

        //create email details obj
        EmailDetailsV0 emailDetailsV0 = new EmailDetailsV0();
        emailDetailsV0.setRecipient(createGravitateUserForm.email());
        emailDetailsV0.setRecipientFirstName(createGravitateUserForm.firstName());
        emailDetailsV0.setRecipientLastName(createGravitateUserForm.lastName());
        emailDetailsV0.setPassword(createGravitateUserForm.otp());
        emailDetailsV0.setSubject("Welcome To Gravitate");
        return gravitateUserManagerService.createGravitateUser(userVO,createGravitateUserForm.projects(),token,emailDetailsV0);
    }
    @GetMapping(value = "/all")
    public ResponseEntity getAllGravitateUsers(@RequestParam(value = "search",required = false) String search,@RequestParam(value = "roleId",required = false) Long roleId) throws IOException{
        return gravitateUserManagerService.getAllGravitateUsers(search,roleId);
    }

    @GetMapping(value = "/{userId}")
    public ResponseEntity getGravitateUserById(@PathVariable("userId") Long userId){
        return gravitateUserManagerService.getGravitateUserById(userId);
    }

    @GetMapping(value = "/profile")
    public ResponseEntity getGravitateUsernameProfile(HttpServletRequest request) throws IOException {
        String token = request.getHeader(AUTHORIZATION).substring("Bearer ".length());
        String username = JwtUtils.getUserNameFromToken(token);
        String userLevel = JwtUtils.getUserRoleFromJwtToken(token);
        return gravitateUserManagerService.getGravitateUserInfoByUsername(username,token,userLevel);

    }

    @PutMapping(value = "/update")
    public ResponseEntity updateGravitateUser(@RequestBody UpdateGravitateUserForm updateGravitateUserForm,HttpServletRequest request) throws UnirestException {
        UserVO userVO = UpdateUserMapper.INSTANCE.map(updateGravitateUserForm);
        userVO.setPassword(passwordEncoder.encode(updateGravitateUserForm.otp()));
        String token = request.getHeader(AUTHORIZATION).substring("Bearer ".length());
        return gravitateUserManagerService.updateGravitateUser(userVO,updateGravitateUserForm.projects(),token);
    }

    @DeleteMapping(value = "/{userId}")
    public ResponseEntity deleteGravitateUser(@PathVariable("userId") Long userId){
        return gravitateUserManagerService.deleteGravitateUser(userId);
    }

    @PutMapping(value = "/password-reset")
    public ResponseEntity updateGravitateUserPassword(@RequestBody UpdateGravitateUserPasswordRequest updateGravitateUserPasswordRequest,
                                                        HttpServletRequest request) throws IOException {
        UserVO userVO = new UserVO();
        String token = request.getHeader(AUTHORIZATION).substring("Bearer ".length());
        String userId = JwtUtils.getUserIdFromJwtToken(token);
        userVO.setUserId(Long.valueOf(userId));
        userVO.setPassword(passwordEncoder.encode(updateGravitateUserPasswordRequest.password()));
        return gravitateUserManagerService.updateGravitateUserPassword(userVO,updateGravitateUserPasswordRequest.oldPassword());
    }

    @PutMapping(value = "/forget/password-reset")
    public ResponseEntity forgetPassword(@RequestBody ForgetPasswordRequest forgetPasswordRequest){
        UserVO userVO = new UserVO();
        userVO.setEmail(forgetPasswordRequest.email());
        userVO.setPassword(passwordEncoder.encode(forgetPasswordRequest.password()));
        return gravitateUserManagerService.updateGravitateUserPassword(userVO);
    }

    @GetMapping("/team/members")
    public ResponseEntity getGravitateUserTeamMembers(HttpServletRequest request) throws IOException {
        String token = request.getHeader(AUTHORIZATION).substring("Bearer ".length());
        String userId = JwtUtils.getUserIdFromJwtToken(token);
        String userLevel = JwtUtils.getUserRoleFromJwtToken(token);
        return gravitateUserManagerService.getGravitateUserTeamMembers(userLevel,Long.valueOf(userId));
    }

    @GetMapping("/managers")
    public ResponseEntity getGravitateManagers(@RequestParam(value = "search",required = false) String search){
        return gravitateUserManagerService.getGravitateManagerUsers(search);
    }

    @PutMapping("/{userId}/status/{status}")
    public ResponseEntity updateGravitateUserStatus(@PathVariable("userId") Long userId, @PathVariable("status") boolean status){
        return gravitateUserManagerService.updateGravitateUserAccountStatus(userId,status);
    }




}
