package cl.qs.securitycoreserver.repository;

import cl.qs.securitycoreserver.entity.Level;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LevelRepositoryInterface extends JpaRepository<Level, Long> { }
