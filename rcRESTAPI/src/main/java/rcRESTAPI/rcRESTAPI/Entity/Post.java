package rcRESTAPI.rcRESTAPI.Entity;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Generated;
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
	
	private int upvotes;
	private int downvotes;
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

	public int getUpvotes() {
		return upvotes;
	}

	public void setUpvotes(int upvotes) {
		this.upvotes = upvotes;
	}

	public int getDownvotes() {
		return downvotes;
	}

	public void setDownvotes(int downvotes) {
		this.downvotes = downvotes;
	}


}
