package edu.tcu.cs.frogcrew.controller;

import edu.tcu.cs.frogcrew.dto.GameDto;
import edu.tcu.cs.frogcrew.service.GameService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/games")
public class GameController {

    private final GameService service;

    public GameController(GameService service) {
        this.service = service;
    }

    // UC-5: Crew Member Views General Game Schedule
    @GetMapping
    public ResponseEntity<List<GameDto>> getAllGames() {
        return ResponseEntity.ok(service.findAllGames());
    }

    // UC-20: Admin Adds a Single Game to Schedule
    @PostMapping
    public ResponseEntity<GameDto> addGame(@RequestBody GameDto gameDto) {
        return ResponseEntity.ok(service.addGame(gameDto));
    }

    // UC-23: Admin Schedules Crew to a Game
    @PostMapping("/{gameId}/assign-crew")
    public ResponseEntity<GameDto> assignCrewToGame(@PathVariable Long gameId, @RequestBody List<Long> crewMemberIds) {
        return ResponseEntity.ok(service.assignCrew(gameId, crewMemberIds));
    }
}
