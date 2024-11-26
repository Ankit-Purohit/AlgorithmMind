package com.users.algousers.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AlgoUsersDto {
    private Long userId;

    @NotBlank(message = "First name should not be null or blank")
    @Size(max = 50, message = "First name must not exceed 50 characters")
    @Pattern(regexp = "^[a-zA-Z\\s]+$", message = "First name can only contain alphabetic characters and spaces")
    private String firstName;

    @Size(max = 50, message = "Last name must not exceed 50 characters")
    @Pattern(regexp = "^[a-zA-Z\\s]+$", message = "First name can only contain alphabetic characters and spaces")
    private String lastName;

    @NotBlank(message = "Email should not be null or blank")
    @Email(message = "Email should be valid")
    private String email;

    @NotBlank(message = "Password should not be null or blank")
    @Size(min = 8, max = 20, message = "Password must be between 8 and 20 characters")
    private String password;

    @Pattern(regexp = "^\\d{10}$", message = "Mobile number should be a valid 10-digit number")
    private String mobileNumber;

    @NotBlank(message = "Address should not be null or blank")
    @Size(min=4,max=100,message = "address should be valid")
    private String address;
}
