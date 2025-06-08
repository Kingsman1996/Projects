package com.teamtaskmanager.mapper;

import com.teamtaskmanager.dto.team.CreateTeamRequest;
import com.teamtaskmanager.entity.Team;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TeamMapper {
    @Mapping(target = "leader", ignore = true)
    Team toEntity(CreateTeamRequest createTeamRequest);
}
