package com.slt.documentmanagment.controller;

import com.slt.documentmanagment.RoleDto;
import com.slt.documentmanagment.service.RolesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/roles")
public class RolesController {

    @Autowired
    RolesService rolesService;

    @GetMapping
    public List<RoleDto> getRoles(){
        List<RoleDto> allRoles = rolesService.findAllRoles();
        return allRoles;
    }

}