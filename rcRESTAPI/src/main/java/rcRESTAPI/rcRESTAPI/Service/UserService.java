package rcRESTAPI.rcRESTAPI.Service;

import java.util.HashMap;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rcRESTAPI.rcRESTAPI.Converter;
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

	public UserDTO create(UserDTO dto) {
		if(dto.getUsername().length()<1) {
			return dto;
		}
		else if(dto.getPassword().length()<1) {
			return dto;
		}
		else if(userRepository.findByUsername(dto.getUsername()).isPresent()) {
			return dto;
		}
		dto.setPassword(passwordEncodeAndMatch.encode(dto.getPassword()));
		return Converter.userToDTO(userRepository.save(Converter.DTOToUser(dto)));
	}

	public Optional<User> getById(Long id) {
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
