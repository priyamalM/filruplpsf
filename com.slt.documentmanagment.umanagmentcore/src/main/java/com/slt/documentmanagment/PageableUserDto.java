package com.slt.documentmanagment;

import lombok.Data;

import java.util.List;

@Data
public class PageableUserDto {
    public List<Integer> totalPageSize;
    public List<UserDto> userDtoList;
}
