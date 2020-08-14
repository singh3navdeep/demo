package com.example.demo.client_models.request;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Optional;

@Data
public class UpdatedUserDetails {

    @NotNull
    private Long id;

    private String name;

    private String password;
}
