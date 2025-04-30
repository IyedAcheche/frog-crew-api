package edu.tcu.cs.frogcrew.service;

import edu.tcu.cs.frogcrew.dto.CrewMemberDto;
import edu.tcu.cs.frogcrew.entity.CrewMember;
import edu.tcu.cs.frogcrew.exceptions.ResourceNotFoundException;
import edu.tcu.cs.frogcrew.mapper.EntityDtoMapper;
import edu.tcu.cs.frogcrew.repository.CrewMemberRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CrewMemberService {

    private final CrewMemberRepository crewMemberRepository;
    private final EntityDtoMapper mapper;

    public CrewMemberService(CrewMemberRepository repo, EntityDtoMapper mapper) {
        this.crewMemberRepository = repo;
        this.mapper = mapper;
    }

    public CrewMemberDto create(CrewMemberDto dto) {
        CrewMember member = mapper.toEntity(dto);
        return mapper.toDto(crewMemberRepository.save(member));
    }

    public List<CrewMemberDto> findAll() {
        return crewMemberRepository.findAll()
                .stream().map(mapper::toDto)
                .collect(Collectors.toList());
    }

    public CrewMemberDto findById(Long id) {
        return mapper.toDto(
                crewMemberRepository.findById(id)
                        .orElseThrow(() -> new ResourceNotFoundException("CrewMember", id)));
    }

    public void delete(Long id) {
        crewMemberRepository.deleteById(id);
    }

    public void invite(String email) {
        CrewMember member = crewMemberRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("CrewMember with email", email));
        member.setInvited(true);
        crewMemberRepository.save(member);
    }
}


