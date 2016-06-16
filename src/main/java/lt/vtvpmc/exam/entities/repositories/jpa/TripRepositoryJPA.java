package lt.vtvpmc.exam.entities.repositories.jpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import lt.vtvpmc.exam.entities.Trip;
import lt.vtvpmc.exam.entities.repositories.TripRepository;

public class TripRepositoryJPA implements TripRepository {

	private EntityManagerFactory entityManagerFactory;

	public void setEntityManagerFactory(EntityManagerFactory emf) {
		this.entityManagerFactory = emf;
	}

	private EntityManager getEntityManager() {
		return entityManagerFactory.createEntityManager();
	}

	@Override
	public void insertOrUpdate(Trip trip) {
		EntityManager entityManager = getEntityManager();
		try {
			entityManager.getTransaction().begin();
			boolean merged = false;
			for (Trip trp : trip.getClient().getTrips()) {
				if (!entityManager.contains(trp) && trp.getId() != null) {
					entityManager.merge(trp);
					merged = true;
				} else
					entityManager.persist(trp);
			}
			if (merged) {
				entityManager.merge(trip);
			} else {
				entityManager.persist(trip);
			}
			entityManager.getTransaction().commit();
		} finally {
			entityManager.close();
		}

	}

	@Override
	public void delete(Trip trip) {
		EntityManager entityManager = getEntityManager();
		try {
			entityManager.getTransaction().begin();
			entityManager.createQuery("DELETE FROM Trip e WHERE e.id = :id").setParameter("id", trip.getId())
					.executeUpdate();
			entityManager.getTransaction().commit();
		} finally {
			entityManager.close();
		}
	}

	@Override
	public void deleteById(Long tripId) {
		EntityManager entityManager = getEntityManager();
		try {
			entityManager.getTransaction().begin();
			Trip trip = entityManager.find(Trip.class, tripId);
			if (trip != null)
				entityManager.remove(trip);
			entityManager.getTransaction().commit();
		} finally {
			entityManager.close();
		}
	}

	@Override
	public Long countAllTrips() {
		EntityManager entityManager = getEntityManager();
		try {
			CriteriaBuilder cb = entityManager.getCriteriaBuilder();
			CriteriaQuery<Long> countQuery = cb.createQuery(Long.class);
			countQuery.select(cb.count(countQuery.from(Trip.class)));
			TypedQuery<Long> q = entityManager.createQuery(countQuery);
			return q.getSingleResult();
		} finally {
			entityManager.close();
		}
	}

}
