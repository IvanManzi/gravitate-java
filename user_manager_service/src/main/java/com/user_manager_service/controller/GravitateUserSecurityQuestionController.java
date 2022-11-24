package com.user_manager_service.controller;

import com.model.SecurityQuestionVO;
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
        SecurityQuestionVO securityQuestionVO = new SecurityQuestionVO();
        String token = request.getHeader(AUTHORIZATION).substring("Bearer ".length());
        String userId = JwtUtils.getUserIdFromJwtToken(token);
        securityQuestionVO.setUserId(Long.valueOf(userId));
        securityQuestionVO.setQuestion(createUserSecurityQuestionRequest.question());
        securityQuestionVO.setAnswer(createUserSecurityQuestionRequest.answer());
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
        SecurityQuestionVO securityQuestionVO = new SecurityQuestionVO();
        String token = request.getHeader(AUTHORIZATION).substring("Bearer ".length());
        String userId = JwtUtils.getUserIdFromJwtToken(token);
        securityQuestionVO.setUserId(Long.valueOf(userId));
        securityQuestionVO.setSecurityQuestionId(updateUserSecurityQuestionRequest.securityQuestionId());
        securityQuestionVO.setQuestion(updateUserSecurityQuestionRequest.question());
        securityQuestionVO.setAnswer(updateUserSecurityQuestionRequest.answer());
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



}
