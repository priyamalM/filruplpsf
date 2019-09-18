package com.slt.documentmanagment.service;

import com.slt.documentmanagment.model.Role;
import com.slt.documentmanagment.repository.RoleRepository;
import com.slt.documentmanagment.repository.UserDetailRepository;
import com.slt.documentmanagment.UserDto;
import com.slt.documentmanagment.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserDetailRepository userDetailRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private EmailServiceImpl emailService;

    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public UserDto saveUser(UserDto userDto){
        if(userDetailRepository.findByUsername(userDto.getUserName()).isPresent()){
            return null;
        }
        emailService.sendMessage(userDto.getEmail(),"password : ",userDto.getPassword());
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
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setUsername(userDto.getUserName());
        return user;
    }

}