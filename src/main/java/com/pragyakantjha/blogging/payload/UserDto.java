package com.pragyakantjha.blogging.payload;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserDto {

    private Integer id;

    @NotEmpty
    @Size(min = 4, message = "User name cannot be less than 4 characters")
    private String name;

    @Pattern(regexp = "^([a-zA-Z0-9._%-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6})*$",
            message = "Email address is not valid!")
    private String email;

    @NotEmpty
    @Size(min = 8, max = 20, message = "password must be between 8 and 20 characters")
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[^\\w\\s]).{8,}$",
            message = "Should have at least one uppercase letter, one lowercase letter, one digit, one special character, and a minimum length of 8 characters")
    private String password;

    @NotEmpty
    private String about;
}
