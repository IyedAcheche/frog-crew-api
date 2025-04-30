package edu.tcu.cs.frogcrew.mapper;

import edu.tcu.cs.frogcrew.dto.CrewMemberDto;
import edu.tcu.cs.frogcrew.entity.CrewMember;
import org.springframework.stereotype.Component;

/**
 * Maps between CrewMember entity and DTO objects.
 * Provides conversion methods to transform entity objects to DTOs and vice versa.
 */
public interface CrewMemberMapper {
    /**
     * Converts a CrewMember DTO to its entity representation.
     * 
     * @param dto The DTO to convert
     * @return A CrewMember entity
     */
    CrewMember toEntity(CrewMemberDto dto);
    
    /**
     * Converts a CrewMember entity to its DTO representation.
     * 
     * @param entity The CrewMember entity to convert
     * @return A DTO representation of the CrewMember
     */
    CrewMemberDto toDto(CrewMember entity);
}

/**
 * Implementation of the CrewMemberMapper interface.
 * Handles the conversion between CrewMember entity and DTO objects.
 */
@Component
class CrewMemberMapperImpl implements CrewMemberMapper {
    @Override
    public CrewMember toEntity(CrewMemberDto dto) {
        CrewMember entity = new CrewMember();
        entity.setId(dto.getId());
        entity.setFirstName(dto.getFirstName());
        entity.setLastName(dto.getLastName());
        entity.setEmail(dto.getEmail());
        entity.setPhoneNumber(dto.getPhoneNumber());
        entity.setRole(dto.getRole());
        entity.setQualifiedPosition(dto.getQualifiedPosition());
        entity.setInvited(dto.isInvited());
        return entity;
    }
    
    @Override
    public CrewMemberDto toDto(CrewMember entity) {
        CrewMemberDto dto = new CrewMemberDto();
        dto.setId(entity.getId());
        dto.setFirstName(entity.getFirstName());
        dto.setLastName(entity.getLastName());
        dto.setEmail(entity.getEmail());
        dto.setPhoneNumber(entity.getPhoneNumber());
        dto.setRole(entity.getRole());
        dto.setQualifiedPosition(entity.getQualifiedPosition());
        dto.setInvited(entity.isInvited());
        return dto;
    }
}