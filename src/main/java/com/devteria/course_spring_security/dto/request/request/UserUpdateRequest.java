package com.devteria.course_spring_security.dto.request.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserUpdateRequest {

     String password;
     String firstName;
     String lastName;
     LocalDate dob;
}
