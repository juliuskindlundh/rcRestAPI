package rcRESTAPI.rcRESTAPI.Service;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import rcRESTAPI.rcRESTAPI.Entity.Post;
import rcRESTAPI.rcRESTAPI.Repository.PostRepository;

@Service
public class PostService implements BasicCRUD<Post> {

	@Autowired
	private PostRepository postRepository;

	@Override
	public Post create(Post post) {
		return postRepository.save(post);
	}

	@Override
	public ArrayList<Post> getAll() {
		ArrayList<Post> list = new ArrayList<Post>();
		postRepository.findAll().forEach(post -> list.add(post));
		return list;
	}

	@Override
	public Optional<Post> getById(String id) {
		return postRepository.findById(id);
	}

	@Override
	@Transactional
	public Post update(Post dto) {
		Optional<Post> toUpdate = getById(dto.getPostId());
		if (toUpdate.isPresent()) {
			toUpdate.get().setHeadline(dto.getHeadline());
			toUpdate.get().setText(dto.getHeadline());
			toUpdate.get().setInteractions(dto.getInteractions());
			postRepository.save(toUpdate.get());
			return toUpdate.get();
		}
		return null;
	}

	@Override
	public void deleteById(String id) {
		postRepository.deleteById(id);

	}

}
