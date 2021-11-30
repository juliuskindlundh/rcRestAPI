package rcRESTAPI.rcRESTAPI.Service;

import java.util.HashMap;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rcRESTAPI.rcRESTAPI.DTOs.UserDTO;
import rcRESTAPI.rcRESTAPI.Entity.User;
import rcRESTAPI.rcRESTAPI.Repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncodeAndMatch passwordEncodeAndMatch;

	@Autowired
	private HashMap<String, String> tokensHashMap;

	public User create(UserDTO dto) {
		System.out.println(dto.getUserId());
		if(dto.getUsername().length() < 1 || userRepository.findByUsername(dto.getUsername()).isPresent()) {
			return dto;
		}
		dto.setPassword(passwordEncodeAndMatch.encode(dto.getPassword()));
		return userRepository.save(dto.toEntity());
	}

	public Optional<User> getById(String id) {
		return userRepository.findById(id);
	}

	public Optional<User> getByUsername(String username) {
		return null;
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
			storedToken = null;
			return true;
		} else {
			return false;
		}
	}
	
	public void logout(String token,String username) {
		if(validateToken(username, token)) {
			tokensHashMap.remove(username);
		}
	}

}
