package rcRESTAPI.rcRESTAPI.Service;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import rcRESTAPI.rcRESTAPI.Entity.Interaction;
import rcRESTAPI.rcRESTAPI.Repository.InteractionRepository;

@Service
public class InteractionService implements BasicCRUD<Interaction> {

	@Autowired
	private InteractionRepository interactionRepository;

	@Override
	public Interaction create(Interaction dto) {
		return interactionRepository.save(dto);
	}

	@Override
	public ArrayList<Interaction> getAll() {
		ArrayList<Interaction> list = new ArrayList<Interaction>();
		interactionRepository.findAll().forEach(interaction -> list.add(interaction));
		return list;
	}

	@Override
	public Optional<Interaction> getById(String id) {
		return interactionRepository.findById(id);
	}

	@Override
	@Transactional
	public Interaction update(Interaction dto) {
		Optional<Interaction> toUpdate = getById(dto.getInteractionId());
		if (toUpdate.isPresent()) {
			toUpdate.get().setDoesLike(dto.isDoesLike());
			interactionRepository.save(toUpdate.get());
			return toUpdate.get();
		}
		return null;
	}

	@Override
	public void deleteById(String id) {
		interactionRepository.deleteById(id);

	}

}
