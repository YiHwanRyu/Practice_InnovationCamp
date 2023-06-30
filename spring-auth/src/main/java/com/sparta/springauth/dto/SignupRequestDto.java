package com.sparta.springauth.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignupRequestDto {
    @NotBlank
    private String username;
    @NotBlank
    private String password;
    @Pattern(regexp = "^[A-Za-z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-z0-9-]+(\\.[a-z0-9]+)*(\\.[A-Za-z]{2,5})$")// \\는 특수문자 표시, 뒤에 * 없어도 되는 거
    @NotBlank
    private String email;
    private boolean admin = false;
    private String adminToken = "";
}