package edu.tcu.cs.frogcrew.mapper;

import edu.tcu.cs.frogcrew.dto.GameDto;
import edu.tcu.cs.frogcrew.entity.CrewMember;
import edu.tcu.cs.frogcrew.entity.Game;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Maps between Game entity and DTO objects.
 * Provides methods to transform Game entities to DTOs and vice versa,
 * handling the conversion of assigned crew members.
 */
@Component
public class GameMapper {

    /**
     * Converts a Game DTO to its entity representation.
     * Takes a separate parameter for the assigned crew members set.
     * 
     * @param dto The DTO to convert
     * @param assignedCrew The set of CrewMember entities to assign to the game
     * @return A Game entity
     */
    public Game toEntity(GameDto dto, Set<CrewMember> assignedCrew) {
        Game game = new Game();
        game.setId(dto.getId());
        game.setOpponent(dto.getOpponent());
        game.setSport(dto.getSport());
        game.setVenue(dto.getVenue());
        game.setGameDateTime(dto.getGameDateTime());
        game.setAssignedCrew(assignedCrew);
        return game;
    }

    /**
     * Converts a Game entity to its DTO representation.
     * Maps complex fields like assignedCrew to simple ID collections.
     * 
     * @param game The Game entity to convert
     * @return A DTO representation of the Game
     */
    public GameDto toDto(Game game) {
        GameDto dto = new GameDto();
        dto.setId(game.getId());
        dto.setOpponent(game.getOpponent());
        dto.setSport(game.getSport());
        dto.setVenue(game.getVenue());
        dto.setGameDateTime(game.getGameDateTime());
        dto.setAssignedCrewIds(
                game.getAssignedCrew()
                        .stream()
                        .map(CrewMember::getId)
                        .collect(Collectors.toList())
        );
        return dto;
    }
}
