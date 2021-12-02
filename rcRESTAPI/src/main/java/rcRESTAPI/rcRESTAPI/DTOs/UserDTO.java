package rcRESTAPI.rcRESTAPI.DTOs;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import rcRESTAPI.rcRESTAPI.Entity.User;

public class UserDTO {

	private Long userId;
	private String username;
	private String password;
	private ArrayList<PostDTO> posts = new ArrayList<PostDTO>();

	public Long getUserId() {
		return userId;
	}

	public ArrayList<PostDTO> getPosts() {
		return posts;
	}

	public void setPosts(ArrayList<PostDTO> posts) {
		this.posts = posts;
	}

	public void setUserId(Long userId) {
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
}
