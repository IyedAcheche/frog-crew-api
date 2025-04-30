package edu.tcu.cs.frogcrew.entity;

import jakarta.persistence.*;

// JPA entity that stores crew member availability records for specific games
// Implements Use Case 7: Crew Member Submits Availability
@Entity
public class Availability {

    // Primary key with auto-increment
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Many availability records can link to one crew member
    // optional=false ensures each availability must have a crew member
    @ManyToOne(optional = false)
    private CrewMember crewMember;

    // Many availability records can link to one game
    // optional=false ensures each availability must have a game
    @ManyToOne(optional = false)
    private Game game;

    // Whether the crew member is available for this game
    private boolean available;

    // Optional comment/notes about the availability
    private String comment;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CrewMember getCrewMember() {
        return crewMember;
    }

    public void setCrewMember(CrewMember crewMember) {
        this.crewMember = crewMember;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
