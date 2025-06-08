package com.teamtaskmanager.dto.team;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CreateTeamRequest {
    private  String name;

    private String description;

    private Long leaderId;
}
