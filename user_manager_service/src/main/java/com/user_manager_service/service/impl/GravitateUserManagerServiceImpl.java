package com.user_manager_service.service.impl;

import com.util.APIResponse;
import com.model.RoleVO;
import com.model.UserManagerVO;
import com.model.UserVO;
import com.user_manager_service.dao.RoleDao;
import com.user_manager_service.dao.UserDao;
import com.user_manager_service.dao.UserManagerDao;
import com.user_manager_service.service.GravitateUserManagerService;
import com.util.UserDetailsService;
import com.util.ValidationUtil;
import lombok.RequiredArgsConstructor;
import org.apache.http.HttpStatus;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@RequiredArgsConstructor
public class GravitateUserManagerServiceImpl implements GravitateUserManagerService,org.springframework.security.core.userdetails.UserDetailsService {

    private final UserDao userDao;
    private final UserManagerDao userManagerDao;
    private final RoleDao roleDao;


    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        UserVO user = userDao.getGravitateUserByUsername(username);
        if(ValidationUtil.isNullObject(user)){
            throw new UsernameNotFoundException("Invalid username " + username);
        }
        Collection<RoleVO> roles = roleDao.getGravitateUserRole(user.getUserId());
        roles.forEach(role -> authorities.add(new SimpleGrantedAuthority(role.getRoleName())));
        return UserDetailsService.build(user,authorities);
    }


    @Override
    public APIResponse createGravitateUser(UserVO userVO, UserManagerVO userManagerVO) {
        //check if email is unique
        int check = userDao.checkIfUsernameExists(userVO.getEmail());
        if(check == 1){
            return new APIResponse(HttpStatus.SC_BAD_REQUEST,"Email already taken.");
        }
        int result = userDao.createGravitateUser(userVO);
        if(result > 0){
            userManagerVO.setUserId(userVO.getUserId());
            userManagerDao.createUserManagerRecord(userManagerVO);
            return new APIResponse(HttpStatus.SC_CREATED,"User successfully created.");
        }else{
            return new APIResponse(HttpStatus.SC_BAD_REQUEST);
        }
    }

    @Override
    public APIResponse getAllGravitateUsersByManagerId(Long managerId) {
        List<UserVO> users = userManagerDao.getGravitateUsersByManagerId(managerId);
        if(users.isEmpty()){
            return new APIResponse(HttpStatus.SC_NOT_FOUND,"No users found.");
        }else{
            Map<String,Object> data = new HashMap<>();
            data.put("USERS",users);
            return new APIResponse(HttpStatus.SC_BAD_REQUEST,users);
        }
    }

    @Override
    public APIResponse updateGravitateUser(UserVO userVO) {
        return null;
    }

    @Override
    public APIResponse deleteGravitateUser(Long userId) {
        int result = userDao.deleteGravitateUser(userId);
        if(result > 0){
            return new APIResponse(200,"User successfully deleted.");
        }else{
            return new APIResponse(400);
        }
    }

    @Override
    public APIResponse getGravitateUserByUsername(String username) {
        UserVO user = userDao.getGravitateUserByUsername(username);
        if(ValidationUtil.isNullObject(user)){
            return new APIResponse(404);
        }
        return new APIResponse(200,user);
    }
}
