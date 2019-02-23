package com.talcrafts.persistence;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * created by imteyaza-1lm on 2019-02-23
 **/
public interface UserRepo extends JpaRepository<User, Long> {

  Optional<User> findByUserName(String username);
}
