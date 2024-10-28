package ma.enset.cqrsaxon.queries.repositories;

import ma.enset.cqrsaxon.queries.entities.Operation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OperationRepository extends JpaRepository<Operation,Long> {
}
