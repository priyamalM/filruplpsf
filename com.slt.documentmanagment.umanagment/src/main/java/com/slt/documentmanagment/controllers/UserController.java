package com.slt.documentmanagment.controllers;

import com.slt.documentmanagment.RoleDto;
import com.slt.documentmanagment.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Controller
public class UserController {

    @Autowired
    RestTemplate restTemplate;

    @RequestMapping(method = RequestMethod.POST, value = "/create/user")
    public String createUser(@ModelAttribute("userob") UserDto userDto, Model model) {
        HttpEntity<UserDto> userDtoHttpEntity = new HttpEntity<>(userDto);
        ResponseEntity<UserDto> exchange = restTemplate.exchange(
                "http://localhost:8082/spring-security-oauth-resource/user"
                , HttpMethod.POST
                , userDtoHttpEntity
                , UserDto.class);
        UserDto savedUser = exchange.getBody();
        return "redirect:/search";
    }

    @GetMapping(value ="/edit/{id}")
    public String editUser(@PathVariable("id") int userId,Model model){
        ResponseEntity<UserDto> userDtoResponseEntity = restTemplate.getForEntity("http://localhost:8082/spring-security-oauth-resource/user/{id}", UserDto.class, userId);
        if (userDtoResponseEntity.getBody()==null) return "error";
        model.addAttribute("userob",userDtoResponseEntity.getBody());
        return "edituser";
    }

    @PostMapping(value = "edit/user/{id}")
    public String saveEditedUser(@PathVariable("id") int id, @ModelAttribute("userob") UserDto userDto){
        userDto.setId(id);
        HttpEntity<UserDto> userDtoHttpEntity = new HttpEntity<>(userDto);
        ResponseEntity<UserDto> exchange = restTemplate.exchange(
                "http://localhost:8082/spring-security-oauth-resource/user/edit/{id}"
                , HttpMethod.POST
                , userDtoHttpEntity
                , UserDto.class
                , id);
        UserDto savedUser = exchange.getBody();
        return "redirect:/search";
    }

    @ModelAttribute("AllRoles")
    public List<RoleDto> getAllRoles(){
        ResponseEntity<List<RoleDto>> rolesList = restTemplate.exchange("http://localhost:8082/spring-security-oauth-resource/roles"
                , HttpMethod.GET
                , null
                , new ParameterizedTypeReference<List<RoleDto>>() {
                });
        return rolesList.getBody();
    }


}