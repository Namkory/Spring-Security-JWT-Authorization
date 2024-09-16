package com.devteria.course_spring_security.controller;

import com.devteria.course_spring_security.dto.request.request.ApiResponse;
import com.devteria.course_spring_security.dto.request.request.UserCreationRequest;
import com.devteria.course_spring_security.dto.request.request.UserUpdateRequest;
import com.devteria.course_spring_security.entity.User;
import com.devteria.course_spring_security.service.UserService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ApiResponse<User> creatUser(@RequestBody @Valid UserCreationRequest request){
        ApiResponse<User> apiResponse = new ApiResponse<>();
        apiResponse.setMessage("Tạo user thành công");
        apiResponse.setResult(userService.createRequest(request));
        return apiResponse;
    }

    @GetMapping
    public List<User> getUsers(){
        var authentication = SecurityContextHolder.getContext().getAuthentication();

        log.info("Username: {}", authentication.getName());
        authentication.getAuthorities().forEach(grantedAuthority -> log.info(grantedAuthority.getAuthority()));

        return userService.getUsers();
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable("id") String id){
        return userService.getUserById(id);
    }

    @PutMapping("/{id}")
    public User updateUser(@PathVariable String id, @RequestBody UserUpdateRequest request){
        return userService.updateRequest(id, request);
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable String id){
        userService.deleteUser(id);
        return "User has been deleted";
    }
}
