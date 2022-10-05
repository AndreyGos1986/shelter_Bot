package repository;

import model.Knowledge;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KnowledgeRepository extends JpaRepository<Knowledge,Long> {

    Knowledge findKnowledgeByQuestionContainingIgnoreCase(String question);
    Knowledge findKnowledgeByCodeId(String codeId);
}
