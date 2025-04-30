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

    private final GameRepository gameRepository;
    private final CrewMemberRepository crewMemberRepository;
    private final GameMapper gameMapper;

    // Constructor injection of repositories and mapper
    public GameService(GameRepository gameRepository, CrewMemberRepository crewMemberRepository,
                               GameMapper gameMapper) {
        this.gameRepository = gameRepository;
        this.crewMemberRepository = crewMemberRepository;
        this.gameMapper = gameMapper;
    }

    // Get all games - implements Use Case 5
    public List<GameDto> findAllGames() {
        return gameRepository.findAll()
                .stream()
                .map(gameMapper::toDto)
                .collect(Collectors.toList());
    }

    public void initializeSchedule() {
        // Optional: Initialize default entries or log action
    }

    // Add a single game - implements Use Case 20
    // @Transactional ensures DB operations are atomic
    @Transactional
    public GameDto addGame(GameDto gameDto) {
        Game game = gameMapper.toEntity(gameDto, new HashSet<>());
        Game savedGame = gameRepository.save(game);
        return gameMapper.toDto(savedGame);
    }

    // Helper method to assign crew to a game
    @Transactional
    public void assignCrewToGame(Long gameId, List<Long> crewMemberIds) {
        Game game = gameRepository.findById(gameId)
                .orElseThrow(() -> new IllegalArgumentException("Game not found with id: " + gameId));

        Set<CrewMember> crew = new HashSet<>(crewMemberRepository.findAllById(crewMemberIds));
        game.setAssignedCrew(crew);
        gameRepository.save(game);
    }

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
}
