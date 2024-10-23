package com.users.algousers.service;

import com.users.algousers.dto.AlgoUsersDto;

import java.util.List;

public interface AlgoUsersServices {

    // register a user
    public AlgoUsersDto addUsers(AlgoUsersDto algoUsersDto);
    // delete user
    public String deleteUser(Long userId);
    public List<AlgoUsersDto> getAllUsers();
    public AlgoUsersDto getUser(Long userId);

}
