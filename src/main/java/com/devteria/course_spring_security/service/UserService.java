package com.devteria.course_spring_security.service;

import com.devteria.course_spring_security.dto.request.request.UserCreationRequest;
import com.devteria.course_spring_security.dto.request.request.UserUpdateRequest;
import com.devteria.course_spring_security.entity.User;
import com.devteria.course_spring_security.enums.Role;
import com.devteria.course_spring_security.exception.AppException;
import com.devteria.course_spring_security.exception.ErrorCode;
import com.devteria.course_spring_security.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    @Autowired
    private UserRepo userRepo;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public User createRequest(UserCreationRequest request){
        User user = new User();
        if(userRepo.existsByUserName(request.getUserName())){
            throw new AppException(ErrorCode.USER_EXISTED);
        }

        user.setUserName(request.getUserName());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setDob(request.getDob());

        HashSet<String> roles = new HashSet<>();
        roles.add(Role.USER.name());
        user.setRoles(roles);

        return userRepo.save(user);
    }

    public List<User> getUsers(){
        return userRepo.findAll();
    }

    public User getUserById(String id){
        return userRepo.findById(id).orElseThrow(() -> new RuntimeException("User id not found"));
    }

    public User updateRequest(String id, UserUpdateRequest request){
        User user = getUserById(id);
        user.setPassword(request.getPassword());
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setDob(request.getDob());

        return userRepo.save(user);
    }

    public void deleteUser(String id){
        userRepo.deleteById(id);
    }
}
