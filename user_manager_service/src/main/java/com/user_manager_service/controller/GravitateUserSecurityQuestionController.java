package com.user_manager_service.controller;

import com.model.UserSecurityQuestionVO;
import com.user_manager_service.form.CreateUserSecurityQuestionRequest;
import com.user_manager_service.form.UpdateUserSecurityQuestionRequest;
import com.user_manager_service.service.GravitateUserSecurityQuestionService;
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

    private final GravitateUserSecurityQuestionService gravitateUserSecurityQuestionService;

    @PostMapping(value = "/create")
    public ResponseEntity createUserSecurityQuestion(@Valid @RequestBody CreateUserSecurityQuestionRequest createUserSecurityQuestionRequest,
                                                HttpServletRequest request) throws IOException {
        UserSecurityQuestionVO userSecurityQuestionVO = new UserSecurityQuestionVO();
        String token = request.getHeader(AUTHORIZATION).substring("Bearer ".length());
        String userId = JwtUtils.getUserIdFromJwtToken(token);
        userSecurityQuestionVO.setUserId(Long.valueOf(userId));
        userSecurityQuestionVO.setQuestion(createUserSecurityQuestionRequest.question());
        userSecurityQuestionVO.setAnswer(createUserSecurityQuestionRequest.answer());
        return gravitateUserSecurityQuestionService.createUserSecurityQuestion(userSecurityQuestionVO);
    }

    @GetMapping(value = "/all")
    public ResponseEntity getAllUserSecurityQuestions(HttpServletRequest request) throws IOException {
        String token = request.getHeader(AUTHORIZATION).substring("Bearer ".length());
        String userId = JwtUtils.getUserIdFromJwtToken(token);
        return gravitateUserSecurityQuestionService.getUserSecurityQuestion(Long.valueOf(userId));
    }

    @PutMapping(value = "/")
    public ResponseEntity updateUserSecurityQuestion(@Valid @RequestBody UpdateUserSecurityQuestionRequest updateUserSecurityQuestionRequest,
                                       HttpServletRequest request) throws IOException {
        UserSecurityQuestionVO userSecurityQuestionVO = new UserSecurityQuestionVO();
        String token = request.getHeader(AUTHORIZATION).substring("Bearer ".length());
        String userId = JwtUtils.getUserIdFromJwtToken(token);
        userSecurityQuestionVO.setUserId(Long.valueOf(userId));
        userSecurityQuestionVO.setUserSecurityQuestionId(updateUserSecurityQuestionRequest.securityQuestionId());
        userSecurityQuestionVO.setQuestion(updateUserSecurityQuestionRequest.question());
        userSecurityQuestionVO.setAnswer(updateUserSecurityQuestionRequest.answer());
        return gravitateUserSecurityQuestionService.updateUserSecurityQuestion(userSecurityQuestionVO);
    }
    @DeleteMapping(value = "/{questionId}")
    public ResponseEntity deleteUserSecurityQuestion(@PathVariable("questionId") Long questionId){
        return gravitateUserSecurityQuestionService.deleteUserSecurityQuestion(questionId);
    }

}
