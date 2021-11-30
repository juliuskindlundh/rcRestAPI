package rcRESTAPI.rcRESTAPI.Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import rcRESTAPI.rcRESTAPI.Entity.Interaction;

@Repository
public interface InteractionRepository extends CrudRepository<Interaction, String>{
	
}
