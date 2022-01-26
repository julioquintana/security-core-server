package cl.qs.securitycoreserver.repository;

import cl.qs.securitycoreserver.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepositoryInterface extends JpaRepository<User, Long> {
    Optional<User> findByDni(String dni);

    Optional<User> findByEmailAndStatus(String email, String status);
}
