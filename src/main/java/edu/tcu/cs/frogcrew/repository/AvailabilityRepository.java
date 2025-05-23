package edu.tcu.cs.frogcrew.repository;

import edu.tcu.cs.frogcrew.entity.Availability;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AvailabilityRepository extends JpaRepository<Availability, Long> {
}

