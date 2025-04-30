package edu.tcu.cs.frogcrew.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

// JPA entity for sporting events that need to be crewed
// Supports Use Cases 5, 18, 20, and 23 related to game scheduling
@Entity
public class Game {

    // Primary key with auto-increment
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Basic game information
    private String opponent;
    private String sport;
    private String venue;
    private LocalDateTime gameDateTime;

    // Many-to-many relationship with CrewMember
    // A game can have multiple crew members assigned
    // And a crew member can be assigned to multiple games
    @ManyToMany
    @JoinTable(
            name = "game_crew_member",  // Name of the join table in the database
            joinColumns = @JoinColumn(name = "game_id"),  // Column for this entity (Game)
            inverseJoinColumns = @JoinColumn(name = "crew_member_id")  // Column for the other entity (CrewMember)
    )
    private Set<CrewMember> assignedCrew = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOpponent() {
        return opponent;
    }

    public void setOpponent(String opponent) {
        this.opponent = opponent;
    }

    public String getSport() {
        return sport;
    }

    public void setSport(String sport) {
        this.sport = sport;
    }

    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public LocalDateTime getGameDateTime() {
        return gameDateTime;
    }

    public void setGameDateTime(LocalDateTime gameDateTime) {
        this.gameDateTime = gameDateTime;
    }

    public Set<CrewMember> getAssignedCrew() {
        return assignedCrew;
    }

    public void setAssignedCrew(Set<CrewMember> assignedCrew) {
        this.assignedCrew = assignedCrew;
    }
}
