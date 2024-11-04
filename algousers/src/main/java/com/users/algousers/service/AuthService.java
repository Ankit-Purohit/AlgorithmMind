package com.users.algousers.service;

import com.users.algousers.dto.AlgoUsersDto;
import com.users.algousers.entity.AlgoUsers;
import com.users.algousers.repository.AlgoUsersRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class AuthService {


    @Autowired
    private AlgoUsersRepository algoUsersRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;


    @Autowired
    private ModelMapper modelMapper;

    public String saveUser(AlgoUsersDto algoUsersDto) {
        AlgoUsers algoUsers=modelMapper.map(algoUsersDto,AlgoUsers.class);
        algoUsers.setPassword(passwordEncoder.encode(algoUsers.getPassword()));
        algoUsersRepository.save(algoUsers);
        return "user added to the system";
    }

    public String generateToken(String username) {
        return jwtService.generateToken(username);
    }

    public void validateToken(String token) {
        jwtService.validateToken(token);
    }





}
