package kevin.Eurofunk.repositories;

import kevin.Eurofunk.Modells.User;
import kevin.Eurofunk.Modells.UserGroups;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsersGroupsRepository extends JpaRepository<UserGroups, Long> {

    Optional<UserGroups> findByGroupName(String groupName);

}
