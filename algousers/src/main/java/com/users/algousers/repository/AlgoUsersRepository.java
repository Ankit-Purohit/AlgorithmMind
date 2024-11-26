package com.users.algousers.repository;
import com.users.algousers.entity.AlgoUsers;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AlgoUsersRepository extends JpaRepository<AlgoUsers,Long> {
    Optional<AlgoUsers> findByEmail(String username);
}
