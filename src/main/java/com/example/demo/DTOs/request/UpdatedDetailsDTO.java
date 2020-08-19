package com.example.demo.DTOs.request;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Optional;

@Data
public class UpdatedDetailsDTO {

    private Long id;

    private String name;

    private String password;
}
