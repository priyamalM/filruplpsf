package com.slt.documentmanagment.controllers;

import com.slt.documentmanagment.PageableUserDto;
import com.slt.documentmanagment.RoleDto;
import com.slt.documentmanagment.UserDto;
import com.slt.documentmanagment.config.ResourceServerPropConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Controller
public class DashboardController extends AbstractController{

    @Autowired
    public DashboardController(RestTemplate restService, ResourceServerPropConfig resourceServerPropConfig) {
        super(restService, resourceServerPropConfig);
    }

    @RequestMapping(value = "/search",method = RequestMethod.GET)
    public String createUserController(@ModelAttribute("userob") UserDto userDto
            , Model model
            ,@RequestParam("page") Optional<Integer> page
            ,@RequestParam("size") Optional<Integer> size){
        int currentPage = page.orElse(1);
        int pageSize = size.orElse(10);
        ResponseEntity<PageableUserDto> getEntity = restService.exchange(BASERURI+"/users?page="+currentPage+"&size="+pageSize
                , HttpMethod.GET
                , null
                , PageableUserDto.class);
        PageableUserDto body = getEntity.getBody();
        PageImpl<UserDto> userDtos = new PageImpl<>(body.getUserDtoList(), PageRequest.of(currentPage,pageSize), body.getTotalPages().size());
        model.addAttribute("paginatedUser",userDtos);
        model.addAttribute("pageNumbers", body.getTotalPages());
        model.addAttribute("pageSize",body.getTotalPages().size());
        model.addAttribute("psize",10);
        return "search";
    }

    @RequestMapping(value = "/searchby",method = RequestMethod.GET)
    public String searchByName(@RequestParam("name") String name
            ,@ModelAttribute("userob") UserDto userDto
            ,Model model
            ,@RequestParam("page") Optional<Integer> page
            ,@RequestParam("size") Optional<Integer> size){
        int currentPage = page.orElse(1);
        int pageSize = size.orElse(1);
        if (name==null || name.isEmpty()){
            ResponseEntity<PageableUserDto> getEntity = restService.exchange(BASERURI+"/users?page="+currentPage+"&size="+pageSize
                    , HttpMethod.GET
                    , null
                    , PageableUserDto.class);
            PageableUserDto body = getEntity.getBody();
            PageImpl<UserDto> userDtos = new PageImpl<>(body.getUserDtoList(), PageRequest.of(currentPage,pageSize), body.getTotalPages().size());
            model.addAttribute("paginatedUser",userDtos);
            model.addAttribute("pageNumbers", body.getTotalPages());
            model.addAttribute("pageSize",body.getTotalPages().size());
        }else{
            ResponseEntity<PageableUserDto> getEntity = restService.exchange(BASERURI+"/usersByName?name="+name+"&page="+currentPage+"&size="+pageSize
                    , HttpMethod.GET
                    , null
                    , PageableUserDto.class);
            PageableUserDto body = getEntity.getBody();
            int totalPageSize = 0;
            if (body.getTotalPages()==null){
                totalPageSize = 0;
            }else{
                totalPageSize = body.getTotalPages().size();
            }
            PageImpl<UserDto> userDtos = new PageImpl<>(body.getUserDtoList(), PageRequest.of(currentPage,pageSize), totalPageSize);
            model.addAttribute("paginatedUser",userDtos);
            model.addAttribute("pageNumbers", body.getTotalPages());
            model.addAttribute("pageSize",totalPageSize);
        }
        return "search";
    }


    @ModelAttribute("userob")
    private UserDto userDto(){
        UserDto userDto = new UserDto();
        return userDto;
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