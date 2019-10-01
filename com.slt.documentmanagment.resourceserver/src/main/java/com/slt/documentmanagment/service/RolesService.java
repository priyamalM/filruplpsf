package com.slt.documentmanagment.service;

import com.slt.documentmanagment.RoleDto;
import com.slt.documentmanagment.model.Role;

import java.util.List;

public interface RolesService {
    List<RoleDto> findAllRoles();
    Role saveRole(RoleDto roleDto);
    Role getRoleFromRoleDto(RoleDto roleDto);
    RoleDto getRoleDtoFromRole(Role role);
}
