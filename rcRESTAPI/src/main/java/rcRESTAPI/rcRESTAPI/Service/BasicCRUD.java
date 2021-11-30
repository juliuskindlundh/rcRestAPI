package rcRESTAPI.rcRESTAPI.Service;

import java.util.ArrayList;
import java.util.Optional;

public interface BasicCRUD<T> {
	
	public T create(T dto);
	public ArrayList<T> getAll();
	public Optional<T> getById(String id);
	public T update(T dto);
	public void deleteById(String id);
}