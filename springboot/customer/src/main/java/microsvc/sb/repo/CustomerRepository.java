package microsvc.sb.repo;

import microsvc.sb.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    List<Customer> findByFirstNameContainingOrLastNameContaining(String firstName, String lastName);
}
