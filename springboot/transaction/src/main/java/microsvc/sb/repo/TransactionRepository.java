package microsvc.sb.repo;

import microsvc.sb.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Integer> {

    List<Transaction> findByAccount(String account);

    List<Transaction> findByAccountAndTimeBetween(String account, LocalDateTime from, LocalDateTime to);

    @Query("SELECT SUM(t.amount) FROM Transaction t WHERE t.account=:account AND t.time<=:time")
    Float sumTransactionAmount(@Param("account") String account, @Param("time") LocalDateTime time);
}
