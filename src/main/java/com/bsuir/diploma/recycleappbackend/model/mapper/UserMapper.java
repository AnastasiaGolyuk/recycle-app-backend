package com.bsuir.diploma.recycleappbackend.model.mapper;

import com.bsuir.diploma.recycleappbackend.model.dto.UserDto;
import com.bsuir.diploma.recycleappbackend.model.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDto toDto(User user);

    User toEntity(UserDto userDto);
}
