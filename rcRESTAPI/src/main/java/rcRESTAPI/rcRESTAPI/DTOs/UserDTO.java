package rcRESTAPI.rcRESTAPI.Entity;

import java.util.List;
import java.util.UUID;

import rcRESTAPI.rcRESTAPI.Entity.Interaction;
import rcRESTAPI.rcRESTAPI.Entity.Post;
import rcRESTAPI.rcRESTAPI.Entity.User;

public class UserDTO {

	private String userId = UUID.randomUUID().toString();
	private String username;
	private String password;
	private List<PostDTO> posts;
	private List<InteractionDTO> interactions;

	public List<InteractionDTO> getInteractions() {
		return interactions;
	}

	public void setInteractions(List<InteractionDTO> interactions) {
		this.interactions = interactions;
	}

	public String getUserId() {
		return userId;
	}

	public List<PostDTO> getPosts() {
		return posts;
	}

	public void setPosts(List<PostDTO> posts) {
		this.posts = posts;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public User toEntity() {
		User newUser = new User();
		newUser.setInteractions(null);
	}

}
