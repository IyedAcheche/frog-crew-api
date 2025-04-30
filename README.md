# FrogCrew API

FrogCrew is a Spring Boot REST API designed to help TCU Athletics manage scheduling and staffing for sports games. It allows crew members (such as referees and scorekeepers) to manage their profiles and availability, and enables administrators to organize games and assign crews efficiently.

---

## Features

- Crew member registration, listing, and deletion  
- Crew invitations by email  
- Game schedule creation (single + bulk)  
- Crew assignment to games  
- Availability submissions per crew member per game  
- View full crew list, individual crew profiles, and game schedule  
- All entities mapped with JPA and stored via Spring Data repositories  

---

## Supported Use Cases

| Use Case # | Description                        | Endpoint                                     |
|------------|------------------------------------|----------------------------------------------|
| UC-1       | Crew Member Creates Profile        | POST /api/crew-members                       |
| UC-3       | Crew Member Views Profile          | GET /api/crew-members/{id}                   |
| UC-5       | View General Game Schedule         | GET /api/games                               |
| UC-6 / 16  | View Crew List                     | GET /api/crew-members                        |
| UC-7       | Submit Availability                | POST /api/availability/submit                |
| UC-14      | Admin Invites Crew Member          | POST /api/crew-members/invite?email=         |
| UC-15      | Admin Deletes Crew Member          | DELETE /api/crew-members/{id}                |
| UC-20      | Admin Adds Game to Schedule        | POST /api/games                              |
| UC-23      | Admin Assigns Crew to Game         | POST /api/games/{gameId}/assign-crew         |
|            | View All Availability Records      | GET /api/availability                        |

---

## Architecture

- Backend Framework: Spring Boot  
- Persistence: Spring Data JPA (with H2 or any SQL backend)  
- DTO Mapping: Manual mapping via custom Mapper components  
- Validation: Jakarta Bean Validation (@NotBlank, @Email, etc.)  
- Dependency Injection: Constructor-based via @Service and @Controller  
- Profiles: dev and optional test profile support via application.properties  

---

## Entities

### CrewMember
- Fields: id, firstName, lastName, email, phoneNumber, role, qualifiedPosition, invited  
- Uniqueness constraint on email  

### Game
- Fields: id, opponent, sport, venue, gameDateTime  
- Many-to-Many relation with CrewMember via assignedCrew  

### Availability
- Fields: id, crewMember, game, available, comment  
- Many-to-One to both CrewMember and Game 

---

## Acknowledgements & Sources

- This project is still being worked on. It is inspired by an existant TCU FrogCrew project. There are issues in terms of matching with the API documentation required (Specifically GameDto and GameScheduleDto), the implementation of role based authorization, and authentication, is also to be completed. 