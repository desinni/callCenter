package lt.vtvpmc.exam.entities.repositories;

import java.util.*;
import lt.vtvpmc.exam.entities.Client;

public interface ClientRepository {

	public List<Client> findAll();

	public void save(Client client);

	public void delete(Client client);
}
