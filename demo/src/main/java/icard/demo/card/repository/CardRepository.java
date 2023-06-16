package icard.demo.card.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import icard.demo.card.model.CardEntity;

@Repository
public interface CardRepository extends JpaRepository<CardEntity, Integer> {
}
