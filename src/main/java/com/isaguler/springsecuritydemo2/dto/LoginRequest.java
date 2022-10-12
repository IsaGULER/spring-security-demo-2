package com.isaguler.springsecuritydemo2.dto;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@Builder
@ToString
public class LoginRequest {

    private String username;

    private String  password;
}
