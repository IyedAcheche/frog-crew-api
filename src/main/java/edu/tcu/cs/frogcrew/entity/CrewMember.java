package edu.tcu.cs.frogcrew.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

// JPA entity that maps to 'crew_member' table in the database
@Entity
// Lombok annotations for automatic getters, setters, and constructors
@Getter
@Setter
@NoArgsConstructor  // Creates default constructor
@AllArgsConstructor // Creates constructor with all fields
public class CrewMember {
    // Primary key field with auto-incrementing value
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;

    // Email must be unique and cannot be null in the database
    @Column(unique = true, nullable = false)
    private String email;

    private String phoneNumber;
    private String role;
    private String qualifiedPosition;
    private boolean invited;
}
