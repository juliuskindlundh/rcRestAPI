package rcRESTAPI.rcRESTAPI.Entity;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;

import lombok.Generated;
import lombok.NonNull;

@Entity
public class Interaction {

	@Id
	@Generated
	private String interactionId = UUID.randomUUID().toString();
	
	//@NonNull@JoinColumn(name="userId")
	//private String userId;

	@NonNull
	private boolean doesLike;

	public String getInteractionId() {
		return interactionId;
	}

	public void setInteractionId(String interactionId) {
		this.interactionId = interactionId;
	}

	public boolean isDoesLike() {
		return doesLike;
	}

	public void setDoesLike(boolean doesLike) {
		this.doesLike = doesLike;
	}
}
