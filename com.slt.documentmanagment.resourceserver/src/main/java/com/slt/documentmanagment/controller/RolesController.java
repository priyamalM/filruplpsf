package com.slt.documentmanagment.controller;

import com.slt.documentmanagment.RoleDto;
import com.slt.documentmanagment.service.RolesService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.List;

import static com.slt.documentmanagment.configuration.ApplicationRoles.*;

@RestController
@RequestMapping("/roles")
@Api(tags = {"Register New Roles."})
public class RolesController {

    @Autowired
    RolesService rolesService;

    @GetMapping
    @RolesAllowed({USER,ADMIN})
    public List<RoleDto> getRoles(){
        List<RoleDto> allRoles = rolesService.findAllRoles();
        return allRoles;
    }

    @PostMapping
    @RolesAllowed({ADMIN,USER})
    public ResponseEntity<RoleDto> saveRole(@RequestBody RoleDto roleDto){
        RoleDto role = rolesService.saveRole(roleDto);
        if (role!=null) return new ResponseEntity<RoleDto>(role, HttpStatus.ACCEPTED);
        return new ResponseEntity<RoleDto>(role,HttpStatus.ALREADY_REPORTED);
    }

}
