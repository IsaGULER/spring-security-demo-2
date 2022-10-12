package com.isaguler.springsecuritydemo2.dto;

import com.isaguler.springsecuritydemo2.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    private String username;

    private String  email;

    private Role role;
}
