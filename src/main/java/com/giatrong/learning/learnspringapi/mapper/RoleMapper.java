package com.giatrong.learning.learnspringapi.mapper;

import com.giatrong.learning.learnspringapi.dto.dtos.Role.RoleDto;
import com.giatrong.learning.learnspringapi.entity.Role;
import org.mapstruct.Mapper;

import java.util.Set;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    
    RoleDto toDto(Role role);
    
    Set<RoleDto> toDtoSet(Set<Role> roles);
    
    Role toEntity(RoleDto roleDto);
}
