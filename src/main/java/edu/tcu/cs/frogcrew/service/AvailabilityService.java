package edu.tcu.cs.frogcrew.service;

import edu.tcu.cs.frogcrew.dto.AvailabilityDto;
import edu.tcu.cs.frogcrew.entity.Availability;
import edu.tcu.cs.frogcrew.entity.CrewMember;
import edu.tcu.cs.frogcrew.entity.Game;
import edu.tcu.cs.frogcrew.mapper.AvailabilityMapper;
import edu.tcu.cs.frogcrew.repository.AvailabilityRepository;
import edu.tcu.cs.frogcrew.repository.CrewMemberRepository;
import edu.tcu.cs.frogcrew.repository.GameRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

// Service that manages crew member availability for games
@Service
public class AvailabilityService {

    private final AvailabilityRepository availabilityRepository;
    private final CrewMemberRepository crewMemberRepository;
    private final GameRepository gameRepository;
    private final AvailabilityMapper availabilityMapper;

    // Constructor injection of all required repositories and mapper
    public AvailabilityService(AvailabilityRepository availabilityRepository,
                               CrewMemberRepository crewMemberRepository,
                               GameRepository gameRepository,
                               AvailabilityMapper availabilityMapper) {
        this.availabilityRepository = availabilityRepository;
        this.crewMemberRepository = crewMemberRepository;
        this.gameRepository = gameRepository;
        this.availabilityMapper = availabilityMapper;
    }

    // Submit crew member availability for a game - implements Use Case 7
    // @Transactional ensures all DB operations succeed or fail together
    @Transactional
    public void submitAvailability(AvailabilityDto dto) {
        CrewMember crewMember = crewMemberRepository.findById(dto.getCrewMemberId())
                .orElseThrow(() -> new IllegalArgumentException("Crew member not found."));

        Game game = gameRepository.findById(dto.getGameId())
                .orElseThrow(() -> new IllegalArgumentException("Game not found."));

        // Use the mapper to create the entity, then set the relationships
        Availability availability = availabilityMapper.toEntity(dto);
        availability.setCrewMember(crewMember);
        availability.setGame(game);

        availabilityRepository.save(availability);
    }

    // Get all submitted availability records
    public List<AvailabilityDto> getAllAvailability() {
        return availabilityRepository.findAll().stream()
                .map(availabilityMapper::toDto)
                .collect(Collectors.toList());
    }
}
