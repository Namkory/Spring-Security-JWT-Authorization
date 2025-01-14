package com.devteria.course_spring_security.configuration;

import com.devteria.course_spring_security.entity.User;
import com.devteria.course_spring_security.enums.Role;
import com.devteria.course_spring_security.repository.UserRepo;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;

@Configuration
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class ApplicationInitConfig {

    PasswordEncoder passwordEncoder;

    @Bean
    ApplicationRunner applicationRunner(UserRepo userRepo){
        return args -> {
            if(userRepo.findByUserName("admin").isEmpty()){
                var roles = new HashSet<String>();
                roles.add(Role.ADMIN.name());

                User user = new User();
                user.setUserName("admin");
                user.setPassword(passwordEncoder.encode("admin"));
                user.setRoles(roles);

                userRepo.save(user);
                log.warn("admin user has been created with default password: admin, please change it");
            }

        };
    }
}
