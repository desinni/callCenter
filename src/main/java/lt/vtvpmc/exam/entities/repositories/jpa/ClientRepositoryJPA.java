package lt.vtvpmc.exam.entities.repositories.jpa;

import java.util.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import lt.vtvpmc.exam.entities.Client;
import lt.vtvpmc.exam.entities.repositories.ClientRepository;

public class ClientRepositoryJPA implements ClientRepository {

	private EntityManagerFactory entityManagerFactory;

	public void setEntityManagerFactory(EntityManagerFactory emf) {
		this.entityManagerFactory = emf;
	}

	private EntityManager getEntityManager() {
		return entityManagerFactory.createEntityManager();
	}

	@Override
	public List<Client> findAll() {
		EntityManager entityManager = getEntityManager();
		try {
			CriteriaBuilder cb = entityManager.getCriteriaBuilder();
			CriteriaQuery<Client> cq = cb.createQuery(Client.class);
			Root<Client> root = cq.from(Client.class);
			cq.select(root); // we select entity here
			TypedQuery<Client> q = entityManager.createQuery(cq);
			return q.getResultList();
		} finally {
			entityManager.close();
		}
	}

	@Override
	public void save(Client client) {
		EntityManager entityManager = getEntityManager();
		try {
			entityManager.getTransaction().begin();
			if (!entityManager.contains(client))
				client = entityManager.merge(client);
			entityManager.persist(client);
			entityManager.getTransaction().commit();
		} finally {
			entityManager.close();
		}
	}

	@Override
	public void delete(Client client) {
		EntityManager entityManager = getEntityManager();
		try {
			entityManager.getTransaction().begin();
			client = entityManager.merge(client);
			entityManager.remove(client);
			entityManager.getTransaction().commit();
		} finally {
			entityManager.close();
		}
	}

}
