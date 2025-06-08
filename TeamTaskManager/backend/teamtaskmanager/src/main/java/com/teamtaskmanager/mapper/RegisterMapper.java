package com.teamtaskmanager.mapper;

import com.teamtaskmanager.dto.user.RegisterRequest;
import com.teamtaskmanager.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RegisterMapper {
    User toEntity(RegisterRequest registerRequest);
}
