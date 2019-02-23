package com.talcrafts.service;

import com.talcrafts.helper.UserDto;
import com.talcrafts.exception.UserNotFoundException;
import com.talcrafts.persistence.User;
import com.talcrafts.persistence.UserRepo;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * created by imteyaza-1lm on 2019-02-23
 **/
@Service
public class UserServiceImpl implements UserService {

  private final UserRepo userRepo;

  @Autowired
  public UserServiceImpl(UserRepo userRepo) {
    this.userRepo = userRepo;
  }

  @Override
  public UserDto createUser(UserDto userDto) {
    return User.toUserDto(userRepo.save(UserDto.toUser(userDto)));
  }

  @Override
  public UserDto getUserByUsername(String username) {
    return userRepo.findByUserName(username).map(User::toUserDto).orElseThrow(
        () -> new UserNotFoundException("No user found for " + username));
  }

  @Override
  public List<UserDto> getUsers() {
    return userRepo.findAll().stream().filter(Objects::nonNull).map(User::toUserDto)
        .collect(Collectors.toList());
  }

  @Override
  public void deleteUserByUsername(String username) {
    Optional<User> user = userRepo.findByUserName(username);
    if (user.isPresent()) {
      userRepo.delete(user.get());
    } else {
      throw new UserNotFoundException("No user found for " + username);
    }
  }

  @Override
  public UserDto updateUser(String username, final UserDto userDto) {
    Optional<User> user = userRepo.findByUserName(username);
    return user.map(user1 -> User.toUserDto(userRepo.save(updateValue(user1, userDto))))
        .orElseThrow(() -> new UserNotFoundException("No user found for " + username));
  }

  private User updateValue(User user, UserDto userDto) {
    user.setFirstName(userDto.getFirstName());
    user.setLastName(userDto.getLastName());
    user.setPassword(BCrypt.hashpw(userDto.getPassword(), BCrypt.gensalt()));
    return user;
  }
}
