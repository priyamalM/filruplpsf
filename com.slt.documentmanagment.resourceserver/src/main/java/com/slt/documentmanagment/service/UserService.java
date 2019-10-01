package com.slt.documentmanagment.service;

import com.slt.documentmanagment.UserDto;
import com.slt.documentmanagment.exceptions.EmailSendException;
import com.slt.documentmanagment.model.User;

public interface UserService{
    UserDto saveUser(UserDto userDto) throws EmailSendException;
    UserDto editUser(UserDto userDto,boolean passwordChanged);
    UserDto getUser(int userID);
    UserDto getUserDtoFromUser(User user);
    User getUserFromUserDto(UserDto userDto);
}
