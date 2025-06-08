package com.teamtaskmanager.mapper;

import com.teamtaskmanager.dto.user.UserRequest;
import com.teamtaskmanager.dto.user.UserResponse;
import com.teamtaskmanager.entity.User;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserResponse toDTO(User user);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntity(UserRequest userRequest, @MappingTarget User user);
}
