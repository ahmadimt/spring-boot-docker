package com.talcrafts.service;

import com.talcrafts.helper.UserDto;
import java.util.List;

/**
 * created by imteyaza-1lm on 2019-02-23
 **/
public interface UserService {

  UserDto createUser(UserDto userDto);

  UserDto getUserByUsername(String username);

  List<UserDto> getUsers();

  void deleteUserByUsername(String username);

  UserDto updateUser(String username, UserDto userDto);
}
