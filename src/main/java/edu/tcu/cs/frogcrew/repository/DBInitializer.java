package edu.tcu.cs.frogcrew.repository;

import edu.tcu.cs.frogcrew.entity.Availability;
import edu.tcu.cs.frogcrew.entity.CrewMember;
import edu.tcu.cs.frogcrew.entity.Game;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashSet;


    // This bean runs on application startup
    // CommandLineRunner is executed after the application context is loaded
    @Configuration
    @Profile("!test")
    public class DBInitializer {

        @Bean
        CommandLineRunner initDatabase(CrewMemberRepository crewMemberRepository,
                                       GameRepository gameRepository,
                                       AvailabilityRepository availabilityRepository) {
            return args -> {
                // Create sample crew members
                CrewMember harry = new CrewMember();
                harry.setFirstName("Harry");
                harry.setLastName("Potter");
                harry.setEmail("harry.potter@tcu.edu");
                harry.setPhoneNumber("555-0101");
                harry.setRole("Camera - Handheld");
                harry.setQualifiedPosition("Camera");
                harry.setInvited(true);

                CrewMember linus = new CrewMember();
                linus.setFirstName("Linus");
                linus.setLastName("Linux");
                linus.setEmail("linus.linux@tcu.edu");
                linus.setPhoneNumber("555-0102");
                linus.setRole("Line Judge");
                linus.setQualifiedPosition("Judge");
                linus.setInvited(true);

                CrewMember mike = new CrewMember();
                mike.setFirstName("Mike");
                mike.setLastName("Jordan");
                mike.setEmail("mike.jordan@tcu.edu");
                mike.setPhoneNumber("555-0103");
                mike.setRole("Offciator");
                mike.setQualifiedPosition("Officiating Team");
                mike.setInvited(true);

                crewMemberRepository.saveAll(Arrays.asList(harry, linus, mike));

                // Create sample games
                Game game1 = new Game();
                game1.setOpponent("Texas Tech University");
                game1.setSport("Baseball");
                game1.setVenue("Lupton Stadium");
                game1.setGameDateTime(LocalDateTime.now().plusDays(7));
                game1.setAssignedCrew(new HashSet<>(Arrays.asList(harry, linus)));

                Game game2 = new Game();
                game2.setOpponent("Oklahoma University");
                game2.setSport("Basketball");
                game2.setVenue("Amon G. Carter Stadium");
                game2.setGameDateTime(LocalDateTime.now().plusDays(14));
                game2.setAssignedCrew(new HashSet<>(Arrays.asList(mike, harry)));

                gameRepository.saveAll(Arrays.asList(game1, game2));

                // Create sample availabilities
                Availability availability1 = new Availability();
                availability1.setCrewMember(harry);
                availability1.setGame(game1);
                availability1.setAvailable(true);
                availability1.setComment("Available for the whole game.");

                Availability availability2 = new Availability();
                availability2.setCrewMember(linus);
                availability2.setGame(game1);
                availability2.setAvailable(true);
                availability2.setComment("Able to work the first shift.");

                Availability availability3 = new Availability();
                availability3.setCrewMember(mike);
                availability3.setGame(game2);
                availability3.setAvailable(false);
                availability3.setComment("Unavailble for any shift.");

                availabilityRepository.saveAll(Arrays.asList(availability1, availability2, availability3));
            };
        }
    }