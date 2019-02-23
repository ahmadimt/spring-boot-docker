package com.talcrafts.helper;

import com.talcrafts.persistence.User;
import org.mindrot.jbcrypt.BCrypt;

/**
 * created by imteyaza-1lm on 2019-02-23
 **/
public class UserDto {

  private String userName;

  private String firstName;

  private String lastName;

  private String password;

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  @Override
  public String toString() {
    return "UserDto{" +
        "userName='" + userName + '\'' +
        ", firstName='" + firstName + '\'' +
        ", lastName='" + lastName + '\'' +
        '}';
  }

  public static User toUser(UserDto userDto) {
    User user = new User();
    user.setFirstName(userDto.getFirstName());
    user.setLastName(userDto.getLastName());
    user.setUserName(userDto.getUserName());
    user.setPassword(hashPwd(userDto.getPassword()));
    return user;
  }

  private static String hashPwd(String password) {
    return BCrypt.hashpw(password, BCrypt.gensalt());
  }
}
