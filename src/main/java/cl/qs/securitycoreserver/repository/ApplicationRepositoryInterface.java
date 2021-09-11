package cl.qs.securitycoreserver.repository;

import cl.qs.securitycoreserver.entity.Application;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ApplicationRepositoryInterface extends JpaRepository<Application, Long> {
    Optional<Application> findByName(String name);
}
