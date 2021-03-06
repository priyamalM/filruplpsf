package com.slt.documentmanagment.service;

import com.slt.documentmanagment.PermissionDto;
import com.slt.documentmanagment.RoleDto;
import com.slt.documentmanagment.model.Permission;
import com.slt.documentmanagment.model.Role;
import com.slt.documentmanagment.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RolesServiceImpl implements RolesService{

    @Autowired
    RoleRepository roleRepository;

    public List<RoleDto> findAllRoles(){
        List<Role> roleList = roleRepository.findAll();
        List<RoleDto> roleDtos = roleList.stream().map(role -> getRoleDtoFromRole(role)).collect(Collectors.toList());
        return roleDtos;
    }

    public RoleDto saveRole(RoleDto roleDto){
        Role roleByName = roleRepository.findByName(roleDto.getName());
        if (roleByName==null){
            Role roleFromRoleDto = getRoleFromRoleDto(roleDto);
            Role savedRole = roleRepository.save(roleFromRoleDto);
            RoleDto roleDtoFromRole = getRoleDtoFromRole(savedRole);
            return roleDtoFromRole;
        }
        return null;
    }

    public Role getRoleFromRoleDto(RoleDto roleDto){
        Role role = new Role();
        role.setId(roleDto.getId());
        role.setName(roleDto.getName());
        if (roleDto.getPermissions()!=null) {
            List<Permission> permissionList = roleDto.getPermissions().stream().map(permissionDto -> {
                Permission permission = new Permission();
                permission.setId(permissionDto.getId());
                permission.setName(permissionDto.getName());
                return permission;
            }).collect(Collectors.toList());
            role.setPermissions(permissionList);
        }
        return role;
    }

    public RoleDto getRoleDtoFromRole(Role role){
        RoleDto roleDto = new RoleDto();
        roleDto.setId(role.getId());
        roleDto.setName(role.getName());
        if (role.getPermissions()!=null) {
            List<PermissionDto> permissionList = role.getPermissions().stream().map(permission -> {
                PermissionDto permissionDto = new PermissionDto();
                permissionDto.setId(permission.getId());
                permissionDto.setName(permission.getName());
                return permissionDto;
            }).collect(Collectors.toList());
            roleDto.setPermissions(permissionList);
        }
        return roleDto;
    }


}
