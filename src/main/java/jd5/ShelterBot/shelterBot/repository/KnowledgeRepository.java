package jd5.ShelterBot.shelterBot.repository;


import jd5.ShelterBot.shelterBot.model.Knowledge;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KnowledgeRepository extends JpaRepository<Knowledge,Long> {
    Knowledge findKnowledgeByQuestionContainingIgnoreCase(String question);

    Knowledge findKnowledgeByCodeId(String codeId);

}
