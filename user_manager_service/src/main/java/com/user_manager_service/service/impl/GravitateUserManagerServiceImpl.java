package com.user_manager_service.service.impl;

import com.util.APIResponse;
import com.model.UserManagerVO;
import com.model.UserVO;
import com.user_manager_service.dao.UserDao;
import com.user_manager_service.dao.UserManagerDao;
import com.user_manager_service.service.GravitateUserManagerService;
import com.util.UserDetailsService;
import com.util.ValidationUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

import static com.util.Constants.*;

@Service
@RequiredArgsConstructor
public class GravitateUserManagerServiceImpl implements GravitateUserManagerService,org.springframework.security.core.userdetails.UserDetailsService {

    private final UserDao userDao;
    private final UserManagerDao userManagerDao;


    @Override
    @Transactional
    public UserDetailsService loadUserByUsername(String username) throws UsernameNotFoundException {
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        UserVO user = userDao.getGravitateUserByUsername(username);
        if(ValidationUtil.isNullObject(user)){
            throw new UsernameNotFoundException("Invalid username " + username);
        }
        /*Collection<RoleVO> roles = roleDao.getGravitateUserRole(user.getUserId());
        roles.forEach(role -> authorities.add(new SimpleGrantedAuthority(role.getRoleName())));*/
        switch (user.getIsAdmin()) {
            case 1 -> authorities.add(new SimpleGrantedAuthority(ADMIN_USER));
            default -> authorities.add(new SimpleGrantedAuthority(CLIENT_USER));
        }
        return UserDetailsService.build(user,authorities);
    }


    @Override
    public ResponseEntity createGravitateUser(UserVO userVO, UserManagerVO userManagerVO) {
        //check if email is unique
        int check = userDao.checkIfUsernameExists(userVO.getEmail());
        if(check == 1){
            return APIResponse.resultFail("Email already taken.");
        }
        int result = userDao.createGravitateUser(userVO);
        if(result > 0){
            if(userVO.getIsAdmin() != 1) {
                userManagerVO.setUserId(userVO.getUserId());
                userManagerDao.createUserManagerRecord(userManagerVO);
            }
            return  APIResponse.resultSuccess("User successfully created.");
        }else{
            return  APIResponse.resultFail();
        }
    }

    @Override
    public ResponseEntity getAllGravitateUsersByManagerId(Long managerId) {
        List<UserVO> users = userManagerDao.getGravitateUsersByManagerId(managerId);
        if(users.isEmpty()){
            return  APIResponse.resultFail("No users found.");
        }else{
            Map<String,Object> data = new HashMap<>();
            data.put("USERS",users);
            return APIResponse.resultSuccess(data);
        }
    }

    @Override
    public ResponseEntity updateGravitateUser(UserVO userVO) {
        return null;
    }

    @Override
    public ResponseEntity deleteGravitateUser(Long userId) {
        int result = userDao.deleteGravitateUser(userId);
        if(result > 0){
            return APIResponse.resultSuccess("User successfully deleted.");
        }else{
            return  APIResponse.resultFail();
        }
    }

    @Override
    public ResponseEntity getGravitateUserByUsername(String username) {
        UserVO user = userDao.getGravitateUserByUsername(username);
        if(ValidationUtil.isNullObject(user)){
            return  APIResponse.resultFail("User not found");
        }
        Map<String,Object> data = new HashMap<>();
        data.put("GRAVITATE_USER",user);
        return APIResponse.resultSuccess(data);
    }

    @Override
    public ResponseEntity updateGravitateUserPassword(UserVO userVO) {
        int result = userDao.updateGravitateUserPassword(userVO);
        if(result > 0){
            return APIResponse.resultSuccess("Password successfully updated. ");
        }else{
            return APIResponse.resultFail();
        }
    }
}
