package edu.tcu.cs.frogcrew.service;

import edu.tcu.cs.frogcrew.dto.CrewMemberDto;
import edu.tcu.cs.frogcrew.entity.CrewMember;
import edu.tcu.cs.frogcrew.exceptions.ResourceNotFoundException;
import edu.tcu.cs.frogcrew.mapper.CrewMemberMapper;
import edu.tcu.cs.frogcrew.repository.CrewMemberRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Service for managing crew member operations.
 * Provides methods for creating, retrieving, and managing crew members.
 * Implements use cases related to crew member management.
 */
@Service
public class CrewMemberService {

    private final CrewMemberRepository crewMemberRepository;
    private final CrewMemberMapper mapper;

    /**
     * Constructor for CrewMemberService.
     * 
     * @param repo The repository for crew member data access
     * @param mapper The mapper for converting between entities and DTOs
     */
    public CrewMemberService(CrewMemberRepository repo, CrewMemberMapper mapper) {
        this.crewMemberRepository = repo;
        this.mapper = mapper;
    }

    /**
     * Creates a new crew member.
     * Implements Use Case 1: Crew Member Creates Crew Member Profile.
     * 
     * @param dto The DTO containing crew member information
     * @return The created crew member as a DTO
     */
    public CrewMemberDto create(CrewMemberDto dto) {
        CrewMember member = mapper.toEntity(dto);
        return mapper.toDto(crewMemberRepository.save(member));
    }

    /**
     * Retrieves all crew members.
     * Implements Use Case 6 and 16: Crew Member and Admin Views Crew List.
     * 
     * @return A list of all crew members as DTOs
     */
    public List<CrewMemberDto> findAll() {
        return crewMemberRepository.findAll()
                .stream().map(mapper::toDto)
                .collect(Collectors.toList());
    }

    /**
     * Finds a crew member by ID.
     * Implements Use Case 3: Crew Member Views A Crew Member's Profile.
     * 
     * @param id The ID of the crew member to find
     * @return The crew member as a DTO
     * @throws ResourceNotFoundException if the crew member is not found
     */
    public CrewMemberDto findById(Long id) {
        return mapper.toDto(
                crewMemberRepository.findById(id)
                        .orElseThrow(() -> new ResourceNotFoundException("CrewMember", id)));
    }

    /**
     * Deletes a crew member.
     * Implements Use Case 15: Admin Deletes A Crew Member.
     * 
     * @param id The ID of the crew member to delete
     */
    public void delete(Long id) {
        crewMemberRepository.deleteById(id);
    }

    /**
     * Invites a crew member via email.
     * Implements Use Case 14: Admin Invites Crew Member.
     * 
     * @param email The email of the crew member to invite
     * @throws ResourceNotFoundException if no crew member with the email is found
     */
    public void invite(String email) {
        CrewMember member = crewMemberRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("CrewMember with email", email));
        member.setInvited(true);
        crewMemberRepository.save(member);
    }
}


