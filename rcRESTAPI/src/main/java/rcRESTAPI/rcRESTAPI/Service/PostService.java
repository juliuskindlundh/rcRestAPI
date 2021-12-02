package rcRESTAPI.rcRESTAPI.Service;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import rcRESTAPI.rcRESTAPI.Converter;
import rcRESTAPI.rcRESTAPI.DTOs.PostDTO;
import rcRESTAPI.rcRESTAPI.Entity.Post;
import rcRESTAPI.rcRESTAPI.Repository.PostRepository;
import rcRESTAPI.rcRESTAPI.Repository.UserRepository;

@Service
public class PostService {

	@Autowired
	private PostRepository postRepository;
	@Autowired
	private UserService userService;

	public PostDTO create(PostDTO dto) {
		return Converter.postToDTO(postRepository.save(Converter.DTOToPost(dto)));
	}

	public ArrayList<PostDTO> getAll() {
		ArrayList<PostDTO> list = new ArrayList<PostDTO>();
		postRepository.findAll().forEach(post -> list.add(Converter.postToDTO(post)));
		return list;
	}

	public Optional<Post> getById(Long id) {
		return postRepository.findById(id);
	}

	public void deleteById(Long id,String username) {
		userService.removePost(id,username);
		postRepository.deleteById(id);

	}

}
