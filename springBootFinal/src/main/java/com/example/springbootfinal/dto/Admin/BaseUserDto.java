package com.example.springbootfinal.dto.Admin;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BaseUserDto {
    @NotBlank
    @Size(min = 2, max = 20)
    private String firstName;
    @NotBlank
    @Size(min = 2, max = 30)
    private String lastName;
    @NotBlank
    @Size(min = 2, max = 20)
    private String subDuty;
    @NotBlank
    private Integer minRating;
    @NotBlank
    private Integer maxRating;
}
