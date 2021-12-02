package rcRESTAPI.rcRESTAPI.Entity;

import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;

import lombok.NonNull;

@Entity
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@NonNull
	private Long userId;
	@NonNull
	private String username;
	@NonNull
	private String password;

	@ElementCollection(fetch = FetchType.LAZY)
	@CollectionTable(name = "user_posts", joinColumns = @JoinColumn(name = "post_id"))
	private List<Post> posts;

	public Long getUserId() {
		return userId;
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

	public List<Post> getPosts() {
		return posts;
	}

	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}

}
