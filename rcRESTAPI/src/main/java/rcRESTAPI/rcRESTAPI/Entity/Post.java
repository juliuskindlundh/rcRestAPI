package rcRESTAPI.rcRESTAPI.Entity;

import java.util.List;
import java.util.UUID;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;

import lombok.Generated;
import lombok.NonNull;

@Entity
public class Post {

	@Id@Generated
	private String postId = UUID.randomUUID().toString();
	@NonNull
	private String headline;
	@NonNull
	private String text;
	
	@ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable(name="post_interactions",joinColumns = @JoinColumn(name="post_id"))
	private List<Interaction> interactions;
	
	private int upvotes;
	private int downvotes;
	
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
	public String getPostId() {
		return postId;
	}
	public void setPostId(String postId) {
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
	public List<Interaction> getInteractions() {
		return interactions;
	}
	public void setInteractions(List<Interaction> interactions) {
		this.interactions = interactions;
	}
}
