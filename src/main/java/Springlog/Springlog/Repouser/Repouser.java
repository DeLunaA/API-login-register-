package Springlog.Springlog.Repouser;

import Springlog.Springlog.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;

@Repository
public interface Repouser extends JpaRepository<User , Integer> {

    Optional<?> findByUsername(String username);
    Boolean  existsByUsername(String username);
}
