package rcRESTAPI.rcRESTAPI;

import java.util.ArrayList;
import java.util.List;

import rcRESTAPI.rcRESTAPI.DTOs.*;
import rcRESTAPI.rcRESTAPI.Entity.Post;
import rcRESTAPI.rcRESTAPI.Entity.User;

public class Converter {
	
	public static UserDTO userToDTO(User user) {
		UserDTO userDTO = new UserDTO();
		List<PostDTO> list_1 = new ArrayList<PostDTO>(); 
		user.getInteractions().forEach(a->{
		 	list_1.add(postToDTO(a));
		});
		userDTO.setInteractions(list_1);
		userDTO.setPassword(user.getPassword());
		List<PostDTO> list_2 = new ArrayList<PostDTO>(); 
		user.getPosts().forEach(a->{
		 	list_2.add(postToDTO(a));
		});
		userDTO.setPosts(list_2);
		userDTO.setUserId(user.getUserId());
		userDTO.setUsername(user.getUsername());
		return userDTO;
	}

	public static PostDTO postToDTO(Post post) {
		PostDTO postDTO = new PostDTO();
		postDTO.setDownvotes(post.getDownvotes());
		postDTO.setHeadline(post.getHeadline());
		postDTO.setPostId(post.getPostId());
		postDTO.setText(post.getText());
		postDTO.setUpvotes(post.getUpvotes());
		return postDTO;	
	}

	public static User DTOToUser(UserDTO dto) {
		User user = new User();
		List<Post> list_1 = new ArrayList<Post>(); 
		dto.getInteractions().forEach(a->{
		 	list_1.add(DTOToPost(a));
		});
		user.setInteractions(list_1);
		user.setPassword(dto.getPassword());
		List<Post> list_2 = new ArrayList<Post>(); 
		dto.getPosts().forEach(a->{
		 	list_1.add(DTOToPost(a));
		});
		user.setPosts(list_2);
		user.setUserId(dto.getUserId());
		user.setUsername(dto.getUsername());
		return user;
	}

	public static Post DTOToPost(PostDTO dto) {
		Post post = new Post();
		post.setDownvotes(dto.getDownvotes());
		post.setHeadline(dto.getHeadline());
		post.setPostId(dto.getPostId());
		post.setText(dto.getText());
		post.setUpvotes(dto.getUpvotes());
		return post;	
	}
}
