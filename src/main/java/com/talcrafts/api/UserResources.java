package com.talcrafts.api;

import com.talcrafts.helper.UserDto;
import com.talcrafts.service.UserService;
import io.swagger.annotations.ApiResponse;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * created by imteyaza-1lm on 2019-02-23
 **/
@RestController
@RequestMapping(value = UserResources.BASE_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class UserResources {

  static final String BASE_URL = "/api/v1/user";

  private final UserService userService;

  @Autowired
  public UserResources(UserService userService) {
    this.userService = userService;
  }

  @PostMapping
  public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto) {
    return ResponseEntity.ok(userService.createUser(userDto));
  }

  @GetMapping
  public ResponseEntity<List<UserDto>> getUsers() {
    return ResponseEntity.ok(userService.getUsers());
  }

  @GetMapping(value = "/{username}")
  public ResponseEntity<UserDto> getUserByUsername(@PathVariable("username") String username) {
    return ResponseEntity.ok(userService.getUserByUsername(username));
  }

  @DeleteMapping(value = "/{username}")
  public void deleteUserByUsername(@PathVariable("username") String username) {
    userService.deleteUserByUsername(username);
  }

  @PutMapping(value = "/{username}")
  public ResponseEntity<UserDto> updateUser(@PathVariable("username") String username,
      @RequestBody UserDto userDto) {
    if (StringUtils.hasText(userDto.getUserName().trim()) && !username.trim()
        .equals(userDto.getUserName().trim())) {
      throw new IllegalArgumentException("Username should match to provided in path");
    }
    return ResponseEntity.ok(userService.updateUser(username, userDto));
  }
}
