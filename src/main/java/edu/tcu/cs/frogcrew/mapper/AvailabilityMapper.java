package edu.tcu.cs.frogcrew.mapper;

import edu.tcu.cs.frogcrew.dto.AvailabilityDto;
import edu.tcu.cs.frogcrew.entity.Availability;
import org.springframework.stereotype.Component;

/**
 * Maps between Availability entity and DTO objects.
 * Provides methods to transform Availability entities to DTOs and vice versa.
 */
@Component
public class AvailabilityMapper {

    /**
     * Converts an Availability entity to its DTO representation.
     *
     * @param availability The Availability entity to convert
     * @return A DTO representation of the Availability
     */
    public AvailabilityDto toDto(Availability availability) {
        AvailabilityDto dto = new AvailabilityDto();
        dto.setId(availability.getId());
        dto.setCrewMemberId(availability.getCrewMember().getId());
        dto.setGameId(availability.getGame().getId());
        dto.setAvailable(availability.isAvailable());
        dto.setComment(availability.getComment());
        return dto;
    }

    /**
     * Creates a new Availability entity from DTO values.
     * Note: This method doesn't set the CrewMember and Game objects,
     * as those need to be retrieved from their respective repositories.
     *
     * @param dto The DTO to convert
     * @return A new Availability entity with basic properties set
     */
    public Availability toEntity(AvailabilityDto dto) {
        Availability availability = new Availability();
        availability.setId(dto.getId());
        availability.setAvailable(dto.isAvailable());
        availability.setComment(dto.getComment());
        // CrewMember and Game need to be set separately using their IDs
        return availability;
    }
} 