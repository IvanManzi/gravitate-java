package com.user_manager_service.controller;


import com.model.UserSkillVO;
import com.user_manager_service.form.CreateUserSkillRequest;
import com.user_manager_service.service.UserSkillManagerService;
import com.util.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.IOException;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@RestController
@RequestMapping(value = "/api/v1/user/skills",produces = "application/json")
@RequiredArgsConstructor
public class UserSkillManagerController {

    private final UserSkillManagerService userSkillManagerService;

    @PostMapping(value = "/add")
    public ResponseEntity createUserSkill(@Valid @RequestBody CreateUserSkillRequest createUserSkillRequest, HttpServletRequest request) throws IOException {
        UserSkillVO userSkillVO = new UserSkillVO();
        String token = request.getHeader(AUTHORIZATION).substring("Bearer ".length());
        String userId = JwtUtils.getUserIdFromJwtToken(token);
        userSkillVO.setUserId(Long.valueOf(userId));
        userSkillVO.setTitle(createUserSkillRequest.title());
        userSkillVO.setCategory(createUserSkillRequest.category());
        userSkillVO.setExpertise(createUserSkillRequest.expertise());
        userSkillVO.setCertificatePath(createUserSkillRequest.certificateUrl());
        return userSkillManagerService.createUserSkill(userSkillVO);
    }

    @GetMapping(value = "/all")
    public ResponseEntity getAllUserSkills(HttpServletRequest request) throws IOException {
        String token = request.getHeader(AUTHORIZATION).substring("Bearer ".length());
        String userId = JwtUtils.getUserIdFromJwtToken(token);
        return userSkillManagerService.getAllUserSkills(Long.valueOf(userId));
    }

    @GetMapping(value = "/quarter")
    public ResponseEntity getGravitateUserSkillsByQuarter(@RequestParam("userId") Long userId,
                                                          @RequestParam("quarter") Integer quarter){
        return userSkillManagerService.getUserSkillsByQuarter(userId,quarter);
    }
}
