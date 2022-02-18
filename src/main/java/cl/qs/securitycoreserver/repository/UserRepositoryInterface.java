package cl.qs.securitycoreserver.repository;

import cl.qs.securitycoreserver.entity.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepositoryInterface extends JpaRepository<User, Long> {

  Optional<User> findByDni(String dni);

  Optional<User> findByEmailAndStatus(String email, String status);
}
