package com.slt.documentmanagment.service;

import com.slt.documentmanagment.UserDto;
import com.slt.documentmanagment.exceptions.EmailSendException;
import com.slt.documentmanagment.model.Role;
import com.slt.documentmanagment.model.User;
import com.slt.documentmanagment.repository.RoleRepository;
import com.slt.documentmanagment.repository.UserDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDetailRepository userDetailRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private EmailService emailService;
    @Autowired
    private EmailMessageCreator emailMessageCreator;

    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public UserDto saveUser(UserDto userDto) throws EmailSendException {
        if(userDetailRepository.findByUsername(userDto.getUserName()).isPresent()){
            return null;
        }
        String verificationMessage = emailMessageCreator.createVerificationMessage(userDto.getUserName(), userDto.getPassword());
        emailService.sendMessage(userDto.getEmail(), "User Account Created : ",verificationMessage);
        User user = getUserFromUserDto(userDto);
        User savedUser = userDetailRepository.save(user);
        return  getUserDtoFromUser(savedUser);
    }

    public UserDto editUser(UserDto userDto,boolean passwordChanged){
        if (passwordChanged){
            String message = emailMessageCreator.createPasswordChangedMessage(userDto.getUserName(), userDto.getPassword());
            emailService.sendMessage(userDto.getEmail(),"password Changed : ",message);
        }else{
            Optional<User> byId = userDetailRepository.findById(userDto.getId());
            if (byId.isPresent()){
                User existingUser = byId.get();
                String password = existingUser.getPassword();
                userDto.setPassword(password);
            }
        }
        User user = getUserFromUserDto(userDto);
        User savedUser = userDetailRepository.save(user);
        return  getUserDtoFromUser(savedUser);
    }

    public UserDto getUser(int userID){
        Optional<User> userDetail = userDetailRepository.findById(userID);
        if (userDetail.isPresent()) return getUserDtoFromUser(userDetail.get());
        return null;
    }

    public UserDto getUserDtoFromUser(User user){
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setIdcol(user.getId()+"");
        userDto.setUserName(user.getUsername());
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setEmail(user.getEmail());
        userDto.setAccountNonExpired(user.isAccountNonExpired());
        userDto.setAccountNonLocked(user.isAccountNonLocked());
        userDto.setCredentialsNonExpired(user.isCredentialsNonExpired());
        userDto.setEnabled(user.isEnabled());
        Object[] roles =  user.getRoles().stream().map(role -> {
            String name = role.getName();
            return name;
        }).collect(Collectors.toList()).toArray();
        String[] stringRoles = Arrays.copyOf(roles, roles.length, String[].class);
        userDto.setRoles(stringRoles);
        return userDto;
    }

    public User getUserFromUserDto(UserDto userDto){
        User user = new User();
        if (userDto.getId()!=null){
            user.setId(userDto.getId());
        }
        user.setAccountNonExpired(userDto.isAccountNonExpired());
        user.setAccountNonLocked(userDto.isAccountNonLocked());
        user.setCredentialsNonExpired(userDto.isCredentialsNonExpired());
        user.setEmail(userDto.getEmail());
        user.setEnabled(userDto.isEnabled());
        List<String> rolesArr = Arrays.asList(userDto.getRoles());
        rolesArr.forEach(role -> {
            Role byName = roleRepository.findByName(role);
            if (byName!=null)user.getRoles().add(byName);
        });
        if (userDto.getPassword()!=null){
            user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        }
        user.setUsername(userDto.getUserName());
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        return user;
    }

}