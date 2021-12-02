package rcRESTAPI.rcRESTAPI.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rcRESTAPI.rcRESTAPI.Converter;
import rcRESTAPI.rcRESTAPI.DTOs.PostDTO;
import rcRESTAPI.rcRESTAPI.DTOs.UserDTO;
import rcRESTAPI.rcRESTAPI.Entity.Post;
import rcRESTAPI.rcRESTAPI.Entity.User;
import rcRESTAPI.rcRESTAPI.Repository.PostRepository;
import rcRESTAPI.rcRESTAPI.Repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private PostService postService;
	@Autowired
	private PostRepository postRepository;

	@Autowired
	private PasswordEncodeAndMatch passwordEncodeAndMatch;

	@Autowired
	private HashMap<String, String> tokensHashMap;

	public UserDTO create(UserDTO dto) {
		if (dto.getUsername().length() < 1) {
			return dto;
		} else if (dto.getPassword().length() < 1) {
			return dto;
		} else if (userRepository.findByUsername(dto.getUsername()).isPresent()) {
			return dto;
		}
		dto.setPassword(passwordEncodeAndMatch.encode(dto.getPassword()));
		return Converter.userToDTO(userRepository.save(Converter.DTOToUser(dto)));
	}

	public Optional<User> getById(Long id) {
		return userRepository.findById(id);
	}

	public Optional<User> getByUsername(String username) {
		return userRepository.findByUsername(username);
	}

	public String login(String username, String password) {
		Optional<User> optional = userRepository.findByUsername(username);
		if (optional.isPresent()) {
			if (passwordEncodeAndMatch.matches(password, optional.get().getPassword())) {
				tokensHashMap.put(username, UUID.randomUUID().toString());
				return tokensHashMap.get(username);
			} else {
				return "";
			}
		} else {
			return "";
		}
	}

	public boolean validateToken(String username, String token) {
		String storedToken = tokensHashMap.get(username);
		if (storedToken != null && storedToken.equals(token)) {
			return true;
		} else {
			return false;
		}
	}

	public void logout(String username) {
		while (tokensHashMap.get(username) != null) {
			tokensHashMap.remove(username);
		}
	}

	public void addPost(PostDTO response, String username) {
		Optional<User> user = userRepository.findByUsername(username);
		user.get().getPosts().add(postRepository.findById(response.getPostId()).get());
		userRepository.save(user.get());
	}

	public void removePost(Long id, String username) {
		Optional<User> user = userRepository.findByUsername(username);
		List<Post> list = user.get().getPosts();
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getPostId() == id) {
				list.remove(i);
				break;
			}
		}
		user.get().setPosts(list);
		userRepository.save(user.get());
	}

}
