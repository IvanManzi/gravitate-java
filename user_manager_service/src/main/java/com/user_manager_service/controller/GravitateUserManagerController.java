package com.user_manager_service.controller;

import com.model.UserVO;
import com.user_manager_service.form.CreateGravitateUserForm;
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
    public ResponseEntity<?> createGravitateUser(@RequestBody CreateGravitateUserForm createGravitateUserForm,HttpServletRequest request){
        UserVO userVO = new UserVO();
        String token = request.getHeader(AUTHORIZATION).substring("Bearer ".length());
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
        userVO.setUserLevel(createGravitateUserForm.userLevel());
        userVO.setRoleId(createGravitateUserForm.roleId());
        userVO.setManagedBy(createGravitateUserForm.managerId());
        return gravitateUserManagerService.createGravitateUser(userVO,createGravitateUserForm.projects(),token);
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
        return gravitateUserManagerService.getGravitateUserByUsername(username);
    }

    @PutMapping(value = "/update")
    public ResponseEntity updateGravitateUser(@RequestBody UpdateGravitateUserForm updateGravitateUserForm,HttpServletRequest request){
        UserVO userVO = new UserVO();
        String token = request.getHeader(AUTHORIZATION).substring("Bearer ".length());

        userVO.setUserId(updateGravitateUserForm.userId());
        userVO.setUserType(updateGravitateUserForm.userType());
        userVO.setEmail(updateGravitateUserForm.email());
        //userVO.setPassword(passwordEncoder.encode(updateGravitateUserForm.password()));
        userVO.setAlternateEmail(updateGravitateUserForm.alternativeEmail());
        userVO.setFirstName(updateGravitateUserForm.firstName());
        userVO.setLastName(updateGravitateUserForm.lastName());
        userVO.setCountry(updateGravitateUserForm.country());
        userVO.setDateOfBirth(updateGravitateUserForm.dob());
        userVO.setJoiningDate(updateGravitateUserForm.joinedOn());
        userVO.setPhoneNumber(updateGravitateUserForm.phoneNumber());
        userVO.setProfilePicturePath(updateGravitateUserForm.profilePicture());
        userVO.setContractPath(updateGravitateUserForm.contractPath());
        userVO.setEmploymentStatus(updateGravitateUserForm.employmentStatus());
        userVO.setBankName(updateGravitateUserForm.bankName());
        userVO.setAccountNumber(updateGravitateUserForm.accountNumber());
        userVO.setBilling(updateGravitateUserForm.billing());
        userVO.setUserLevel(updateGravitateUserForm.userLevel());
        userVO.setRoleId(updateGravitateUserForm.roleId());
        userVO.setManagedBy(updateGravitateUserForm.managerId());
        return gravitateUserManagerService.updateGravitateUser(userVO,updateGravitateUserForm.projects(),token);
    }

    @DeleteMapping(value = "/{userId}")
    public ResponseEntity deleteGravitateUser(Long userId){
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
        return gravitateUserManagerService.updateGravitateUserPassword(userVO);
    }

    @GetMapping("/team/members")
    public ResponseEntity getGravitateUserTeamMembers(){
        return gravitateUserManagerService.getGravitateUserTeamMembers();
    }

    @GetMapping("/managers")
    public ResponseEntity getGravitateManagers(@RequestParam(value = "search",required = false) String search){
        return gravitateUserManagerService.getGravitateManagerUsers(search);
    }

    @PutMapping("/{userId}/status")
    public ResponseEntity updateGravitateUserStatus(@PathVariable("userId") Long userId){
        return gravitateUserManagerService.disableGravitateUserAccount(userId);
    }




}
