package edu.tcu.cs.frogcrew.repository;

import edu.tcu.cs.frogcrew.entity.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameRepository extends JpaRepository<Game, Long> {

}
