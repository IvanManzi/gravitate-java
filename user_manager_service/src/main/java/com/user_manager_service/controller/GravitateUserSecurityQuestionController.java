package com.user_manager_service.controller;

import com.model.SecurityQuestionVO;
import com.model.UserVO;
import com.user_manager_service.RequestMapper.CreateSecurityQuestionRequestMapper;
import com.user_manager_service.RequestMapper.UpdateSecurityQuestionRequestMapper;
import com.user_manager_service.form.CreateUserSecurityQuestionRequest;
import com.user_manager_service.form.UpdateUserSecurityQuestionRequest;
import com.user_manager_service.service.GravitateSecurityQuestionService;
import com.util.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.IOException;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@RestController
@RequestMapping("/api/v1/user/security-question")
@RequiredArgsConstructor
public class GravitateUserSecurityQuestionController {

    private final GravitateSecurityQuestionService gravitateSecurityQuestionService;

    @PostMapping(value = "/create")
    public ResponseEntity createUserSecurityQuestion(@Valid @RequestBody CreateUserSecurityQuestionRequest createUserSecurityQuestionRequest,
                                                HttpServletRequest request) throws IOException {
        SecurityQuestionVO securityQuestionVO = CreateSecurityQuestionRequestMapper.INSTANCE.map(createUserSecurityQuestionRequest);
        String token = request.getHeader(AUTHORIZATION).substring("Bearer ".length());
        String userId = JwtUtils.getUserIdFromJwtToken(token);
        securityQuestionVO.setUserId(Long.valueOf(userId));
        return gravitateSecurityQuestionService.createSecurityQuestion(securityQuestionVO);
    }

    @GetMapping(value = "/all")
    public ResponseEntity getAllUserSecurityQuestions(HttpServletRequest request) throws IOException {
        String token = request.getHeader(AUTHORIZATION).substring("Bearer ".length());
        String userId = JwtUtils.getUserIdFromJwtToken(token);
        return gravitateSecurityQuestionService.getUserSecurityQuestion(Long.valueOf(userId));
    }

    @PutMapping(value = "/")
    public ResponseEntity updateUserSecurityQuestion(@Valid @RequestBody UpdateUserSecurityQuestionRequest updateUserSecurityQuestionRequest,
                                       HttpServletRequest request) throws IOException {
        SecurityQuestionVO securityQuestionVO = UpdateSecurityQuestionRequestMapper.INSTANCE.map(updateUserSecurityQuestionRequest);
        String token = request.getHeader(AUTHORIZATION).substring("Bearer ".length());
        String userId = JwtUtils.getUserIdFromJwtToken(token);
        securityQuestionVO.setUserId(Long.valueOf(userId));
        return gravitateSecurityQuestionService.updateSecurityQuestion(securityQuestionVO);
    }
    @DeleteMapping(value = "/{questionId}")
    public ResponseEntity deleteUserSecurityQuestion(@PathVariable("questionId") Long questionId){
        return gravitateSecurityQuestionService.deleteSecurityQuestion(questionId);
    }

    @GetMapping(value = "/{questionId}/verify/")
    public ResponseEntity verifyUserSecurityQuestion(@PathVariable("questionId")Long questionId,@RequestParam("answer") String answer){
        SecurityQuestionVO securityQuestionVO = new SecurityQuestionVO();
        securityQuestionVO.setSecurityQuestionId(questionId);
        securityQuestionVO.setAnswer(answer);
        return gravitateSecurityQuestionService.verifySecurityQuestionAnswer(securityQuestionVO);
    }

    @GetMapping(value = "/verify")
    public ResponseEntity verifySecurityQuestion(@RequestParam(value = "answer",required = true) String answer,
                                                 @RequestParam(value = "email",required = true) String email){
        SecurityQuestionVO securityQuestionVO = new SecurityQuestionVO();
        UserVO userVO = new UserVO();
        securityQuestionVO.setAnswer(answer);
        userVO.setEmail(email);
        return gravitateSecurityQuestionService.verifySecurityQuestionAnswer(userVO,securityQuestionVO);
    }



}
