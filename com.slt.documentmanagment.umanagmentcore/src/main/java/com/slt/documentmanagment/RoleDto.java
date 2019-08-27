package com.slt.documentmanagment;


import lombok.Data;

import java.io.Serializable;
import java.util.List;


@Data
public class RoleDto {


    private Integer id;
    private String name;
    private List<PermissionDto> permissions;


}