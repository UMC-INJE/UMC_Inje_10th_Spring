package com.example.umc10th.domain.user.Repository;

import com.example.umc10th.domain.user.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
