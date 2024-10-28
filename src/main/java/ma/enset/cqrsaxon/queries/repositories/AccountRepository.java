package ma.enset.cqrsaxon.queries.repositories;

import ma.enset.cqrsaxon.queries.entities.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account,String> {
}
