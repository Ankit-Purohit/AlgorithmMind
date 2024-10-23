package com.users.algousers.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AlgoUsersDto {
    private Long userId;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String mobileNumber;
    private String address;
}
