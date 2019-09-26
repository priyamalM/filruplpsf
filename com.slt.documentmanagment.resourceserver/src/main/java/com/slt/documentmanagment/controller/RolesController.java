package com.slt.documentmanagment.controller;

import com.slt.documentmanagment.RoleDto;
import com.slt.documentmanagment.model.Role;
import com.slt.documentmanagment.service.RolesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping
    public ResponseEntity<Role> saveRole(@RequestBody RoleDto roleDto){
        Role role = rolesService.saveRole(roleDto);
        if (role!=null) return new ResponseEntity<Role>(role, HttpStatus.ACCEPTED);
        return new ResponseEntity<Role>(role,HttpStatus.ALREADY_REPORTED);
    }

}