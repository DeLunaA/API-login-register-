package Springlog.Springlog.Repouser;

import Springlog.Springlog.domain.UserDomain;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface Repouser extends JpaRepository<UserDomain, Integer> {

    Optional<?> findByUsername(String username);
    Boolean  existsByUsername(String username);
}
