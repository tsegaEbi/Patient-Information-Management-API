package com.therapy.therapy.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserEntityRepository extends JpaRepository<UserEntity,Long> {

    UserEntity findByUserNameContainingIgnoreCase(String staffId);
}
