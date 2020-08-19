package com.example.demo.DTOs.request;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Data
@Getter
@Setter
public class SignupDTO {

    private String name;

    private String email;

    private String password;
}
