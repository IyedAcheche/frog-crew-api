package edu.tcu.cs.frogcrew.service;

import edu.tcu.cs.frogcrew.dto.GameDto;
import edu.tcu.cs.frogcrew.entity.CrewMember;
import edu.tcu.cs.frogcrew.entity.Game;
import edu.tcu.cs.frogcrew.mapper.GameMapper;
import edu.tcu.cs.frogcrew.repository.CrewMemberRepository;
import edu.tcu.cs.frogcrew.repository.GameRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

// Service class that handles game-related business logic
@Service
public class GameService {

    private final GameMapper gameMapper;
    private final GameRepository gameRepository;
    private final CrewMemberRepository crewMemberRepository;

    // Create multiple games at once - implements Use Case 18
    @Transactional
    public List<GameDto> createGameSchedule(List<GameDto> games) {
        return games.stream()
                .map(gameDto -> {
                    Game game = gameMapper.toEntity(gameDto, new HashSet<>());
                    Game savedGame = gameRepository.save(game);
                    return gameMapper.toDto(savedGame);
                })
                .collect(Collectors.toList());
    }

    // Constructor injection of repositories and mapper
    public GameService(GameRepository gameRepository, CrewMemberRepository crewMemberRepository,
                               GameMapper gameMapper) {
        this.gameRepository = gameRepository;
        this.crewMemberRepository = crewMemberRepository;
        this.gameMapper = gameMapper;
    }

    public void initializeSchedule() {
        // Optional: Initialize default entries or log action
    }

    // Assign crew members to a game - implements Use Case 23
    @Transactional
    public GameDto assignCrew(Long gameId, List<Long> crewMemberIds) {
        Game game = gameRepository.findById(gameId)
                .orElseThrow(() -> new RuntimeException("Game not found"));

        Set<CrewMember> crewMembers = new HashSet<>(crewMemberRepository.findAllById(crewMemberIds));
        game.setAssignedCrew(crewMembers);

        Game savedGame = gameRepository.save(game);
        return gameMapper.toDto(savedGame);
    }

    // Add a single game - implements Use Case 20
    // @Transactional ensures DB operations are atomic
    @Transactional
    public GameDto addGame(GameDto gameDto) {
        Game game = gameMapper.toEntity(gameDto, new HashSet<>());
        Game savedGame = gameRepository.save(game);
        return gameMapper.toDto(savedGame);
    }

    // Get all games - implements Use Case 5
    public List<GameDto> findAllGames() {
        return gameRepository.findAll()
                .stream()
                .map(gameMapper::toDto)
                .collect(Collectors.toList());
    }
}
