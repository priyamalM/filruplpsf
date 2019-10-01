package com.slt.documentmanagment.controllers;

import com.slt.documentmanagment.RoleDto;
import com.slt.documentmanagment.UserDto;
import com.slt.documentmanagment.config.ResourceServerPropConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Controller
public class UserController extends AbstractController{

    @Autowired
    public UserController(RestTemplate restService, ResourceServerPropConfig resourceServerPropConfig) {
        super(restService, resourceServerPropConfig);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/create/user")
    public String createUser(@ModelAttribute("userob") UserDto userDto, Model model) {
        HttpEntity<UserDto> userDtoHttpEntity = new HttpEntity<>(userDto);
        ResponseEntity<UserDto> exchange = restService.exchange(
                BASERURI+"/user"
                , HttpMethod.POST
                , userDtoHttpEntity
                , UserDto.class);
        if (exchange.getStatusCode()== HttpStatus.ALREADY_REPORTED){
            model.addAttribute("formErrors",true);
            model.addAttribute("userstatus","Already Existing User");
            return "search";
        }else if (exchange.getStatusCode()==HttpStatus.BAD_REQUEST){
            model.addAttribute("formErrors",true);
            model.addAttribute("emailStatus","Email Verification Sending Failed");
            return "search";
        }
        else if (exchange.getStatusCode()==HttpStatus.ACCEPTED){
            return "redirect:/search";
        }
        return "redirect:/search";
    }

    @GetMapping(value ="/edit/{id}")
    public String editUser(@PathVariable("id") int userId,Model model){
        ResponseEntity<UserDto> userDtoResponseEntity = restService.getForEntity(BASERURI+"/user/{id}", UserDto.class, userId);
        if (userDtoResponseEntity.getBody()==null) return "error";
        model.addAttribute("userob",userDtoResponseEntity.getBody());
        return "edituser";
    }

    @GetMapping(value ="/view/{id}")
    public String viewUser(@PathVariable("id") int userId,Model model){
        ResponseEntity<UserDto> userDtoResponseEntity = restService.getForEntity(BASERURI+"/user/{id}", UserDto.class, userId);
        if (userDtoResponseEntity.getBody()==null) return "error";
        model.addAttribute("userob",userDtoResponseEntity.getBody());
        return "view";
    }

    @PostMapping(value = "edit/user/{id}")
    public String saveEditedUser(@PathVariable("id") int id, @ModelAttribute("userob") UserDto userDto){
        userDto.setId(id);
        HttpEntity<UserDto> userDtoHttpEntity = new HttpEntity<>(userDto);
        ResponseEntity<UserDto> exchange = restService.exchange(
                BASERURI+"/user/edit/{id}"
                , HttpMethod.POST
                , userDtoHttpEntity
                , UserDto.class
                , id);
        UserDto savedUser = exchange.getBody();
        return "redirect:/search";
    }

    @ModelAttribute("AllRoles")
    public List<RoleDto> getAllRoles(){
        ResponseEntity<List<RoleDto>> rolesList = restService.exchange(BASERURI+"/roles"
                , HttpMethod.GET
                , null
                , new ParameterizedTypeReference<List<RoleDto>>() {
                });
        return rolesList.getBody();
    }


}