package microsvc.sb.repo;

import microsvc.sb.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AccountRepository  extends JpaRepository<Account, String> {

    List<Account> findByCustomer(int customer);
}
