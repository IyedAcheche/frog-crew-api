package edu.tcu.cs.frogcrew.repository;

import edu.tcu.cs.frogcrew.entity.CrewMember;
import jakarta.persistence.Id;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CrewMemberRepository extends JpaRepository<CrewMember, Long> {
    Optional<CrewMember> findByEmail(String email);
}
