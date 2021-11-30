package rcRESTAPI.rcRESTAPI.Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import rcRESTAPI.rcRESTAPI.Entity.Post;

@Repository
public interface PostRepository extends CrudRepository<Post, String>{

}
