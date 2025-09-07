package com.giatrong.learning.learnspringapi.mapper;

import com.giatrong.learning.learnspringapi.dto.request.User.UserCreateRequest;
import com.giatrong.learning.learnspringapi.dto.request.User.UserUpdateRequest;
import com.giatrong.learning.learnspringapi.dto.dtos.User.UserDto;
import com.giatrong.learning.learnspringapi.entity.User;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", uses = {RoleMapper.class})
public interface UserMapper {

    /* =====================
       ENTITY → DTO
       ===================== */
    UserDto toDto(User user);

    List<UserDto> toDtoList(List<User> users);

    /* =====================
       DTO → ENTITY
       ===================== */
    @Mapping(target = "password", ignore = true) // password handle in service
    User toEntity(UserDto userDto);

    /* =====================
       CREATE REQUEST → ENTITY
       ===================== */
    @Mapping(target = "roles", ignore = true) // set default role in service
    @Mapping(target = "password", ignore = true) // encode in service
    @Mapping(target = "username", ignore = true) // set in service
    User toEntity(UserCreateRequest request);

    /* =====================
       UPDATE REQUEST → ENTITY
       ===================== */
    @Mapping(target = "password", ignore = true)
    @Mapping(target = "username", ignore = true) // username not update
    @Mapping(target = "roles", ignore = true) // roles handle separately
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromDto(UserUpdateRequest request, @MappingTarget User user);
}
