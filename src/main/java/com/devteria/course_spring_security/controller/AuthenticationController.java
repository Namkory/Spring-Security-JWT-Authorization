package com.devteria.course_spring_security.controller;

import com.devteria.course_spring_security.dto.request.request.ApiResponse;
import com.devteria.course_spring_security.dto.request.request.AuthenticationRequest;
import com.devteria.course_spring_security.dto.request.request.introSpectRequest;
import com.devteria.course_spring_security.dto.request.response.AuthenticationResponse;
import com.devteria.course_spring_security.dto.request.response.introSpectResponse;
import com.devteria.course_spring_security.service.AuthenticationService;
import com.nimbusds.jose.JOSEException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthenticationController {

    AuthenticationService authenticationService;

    @PostMapping("/token")
    ApiResponse<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request){
        var result = authenticationService.authenticate(request);
        return ApiResponse.<AuthenticationResponse>builder()
                .result(result)
                .build();
    }

    @PostMapping("/introspect")
    ApiResponse<introSpectResponse> authenticate(@RequestBody introSpectRequest request) throws ParseException, JOSEException {
        var result = authenticationService.introSpect(request);
        return ApiResponse.<introSpectResponse>builder()
                .result(result)
                .build();
    }
}
