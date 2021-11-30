package rcRESTAPI.rcRESTAPI.Entity;

import java.util.List;
import java.util.UUID;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;

import org.springframework.security.config.annotation.authentication.configurers.userdetails.UserDetailsAwareConfigurer;

import lombok.Generated;
import lombok.NonNull;

@Entity
public class User{
	
	@Id@Generated
	private String userId = UUID.randomUUID().toString();
	@NonNull
	private String username;
	@NonNull
	private String password;
	
	@ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable(name="user_posts",joinColumns = @JoinColumn(name="post_id"))
	private List<Post> posts;
	
	@ElementCollection(fetch = FetchType.LAZY)
	@CollectionTable(name="user_interactions",joinColumns = @JoinColumn(name="interaction_id"))
	private List<Interaction> interactions;
	
	public List<Interaction> getInteractions() {
		return interactions;
	}
	public void setInteractions(List<Interaction> interactions) {
		this.interactions = interactions;
	}
	public String getUserId() {
		return userId;
	}
	public List<Post> getPosts() {
		return posts;
	}
	public void setPosts(List<Post> posts) {
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
	
}
