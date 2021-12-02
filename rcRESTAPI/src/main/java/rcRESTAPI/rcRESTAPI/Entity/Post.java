package rcRESTAPI.rcRESTAPI.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.NonNull;

@Entity
public class Post {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@NonNull
	private long postId;
	@NonNull
	private String headline;
	@NonNull
	private String text;
	
	private String creator; 
	
	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}
	
	public long getPostId() {
		return postId;
	}

	public void setPostId(long postId) {
		this.postId = postId;
	}

	public String getHeadline() {
		return headline;
	}

	public void setHeadline(String headline) {
		this.headline = headline;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
}
