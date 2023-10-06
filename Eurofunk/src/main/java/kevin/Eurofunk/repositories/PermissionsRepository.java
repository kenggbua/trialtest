package kevin.Eurofunk.repositories;

import kevin.Eurofunk.Modells.Permissions;
import kevin.Eurofunk.Modells.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PermissionsRepository extends JpaRepository<Permissions, Long> {

    Optional<Permissions> findBypermission(String permissions);

}
