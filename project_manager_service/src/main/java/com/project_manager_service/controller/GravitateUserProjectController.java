package com.project_manager_service.controller;


import com.project_manager_service.form.AssignProjectsToUserRequest;
import com.project_manager_service.service.GravitateUserProjectService;
import com.util.JwtUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Date;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@RestController
@RequestMapping(value = "/api/v1/project/",produces = "application/json")
@RequiredArgsConstructor
@Slf4j
public class GravitateUserProjectController {

    private final GravitateUserProjectService gravitateUserProjectService;

    @PostMapping(value = "/assign")
    public boolean assignProjectToGravitateUser(@RequestBody AssignProjectsToUserRequest assignProjectsToUserRequest){
        return gravitateUserProjectService.assignUserToProject(assignProjectsToUserRequest.userId(), assignProjectsToUserRequest.projects());
    }

    @GetMapping(value = "/billing-info/all")
    public ResponseEntity getAssignedProjectsBillingInformation(){
        return gravitateUserProjectService.getAllAssignedProjectBillingInformation();
    }

    @GetMapping(value = "/user/billing-info")
    public ResponseEntity getUserBillingInformation(@RequestParam("from")@DateTimeFormat(pattern="yyyy-MM-dd")Date from,
                                                    @DateTimeFormat(pattern="yyyy-MM-dd") @RequestParam("to") Date to, HttpServletRequest request) throws IOException {
        String token = request.getHeader(AUTHORIZATION).substring("Bearer ".length());
        String userId = JwtUtils.getUserIdFromJwtToken(token);
        log.info("from date {} to date {}",from,to);
        return gravitateUserProjectService.getUserBillingInformation(Long.valueOf(userId),from,to);
    }

    @GetMapping(value = "/")
    public ResponseEntity getGravitateUserProjects(@RequestParam("userId") Long userId){
        return gravitateUserProjectService.getGravitateUserProjects(userId);
    }
}
