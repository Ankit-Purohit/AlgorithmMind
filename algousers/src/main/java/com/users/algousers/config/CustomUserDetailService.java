package com.users.algousers.config;

import com.users.algousers.entity.AlgoUsers;
import com.users.algousers.repository.AlgoUsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;


@Component
public class CustomUserDetailService implements UserDetailsService {
    @Autowired
    private AlgoUsersRepository algoUsersRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<AlgoUsers> credential = algoUsersRepository.findByEmail(username);
        return credential.map(CustomUserDetails::new).orElseThrow(() -> new UsernameNotFoundException("user not found with name :" + username));
    }
}
