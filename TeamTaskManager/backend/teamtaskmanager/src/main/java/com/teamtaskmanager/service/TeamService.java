package com.teamtaskmanager.service;

import com.teamtaskmanager.dto.team.CreateTeamRequest;
import com.teamtaskmanager.entity.Team;
import com.teamtaskmanager.entity.User;
import com.teamtaskmanager.exception.NotFoundException;
import com.teamtaskmanager.mapper.TeamMapper;
import com.teamtaskmanager.repository.TeamRepository;
import com.teamtaskmanager.repository.UserRepository;
import com.teamtaskmanager.util.MessageHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TeamService {
    private final TeamRepository teamRepository;
    private  final UserRepository userRepository;
    private final MessageHelper messageHelper;
    private final TeamMapper teamMapper;

    public void add(CreateTeamRequest createTeamRequest) {
        Team team = teamMapper.toEntity(createTeamRequest);
        User leader = userRepository.findById(createTeamRequest.getLeaderId())
                .orElseThrow(() -> new NotFoundException(messageHelper.get("user.notFound")));
        team.setLeader(leader);
        teamRepository.save(team);
    }
}
