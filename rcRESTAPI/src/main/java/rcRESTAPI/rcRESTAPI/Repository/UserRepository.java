package rcRESTAPI.rcRESTAPI.Repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import rcRESTAPI.rcRESTAPI.Entity.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

	Optional<User> findByUsername(String username);

}
