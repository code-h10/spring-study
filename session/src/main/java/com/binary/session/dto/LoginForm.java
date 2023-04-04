package com.binary.session.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
public class LoginForm {
    private String email;
    private String password;
}
