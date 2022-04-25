package cl.qs.securitycoreserver.repository;

import cl.qs.securitycoreserver.entity.UserApplicationAccess;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserApplicationAccessRepositoryInterface extends
    JpaRepository<UserApplicationAccess, Long> {

  Optional<UserApplicationAccess> findByDescription(String description);
}
