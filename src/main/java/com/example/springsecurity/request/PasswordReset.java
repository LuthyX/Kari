package com.example.springsecurity.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PasswordReset {
    private String password;
    private String tokenReset;
}
