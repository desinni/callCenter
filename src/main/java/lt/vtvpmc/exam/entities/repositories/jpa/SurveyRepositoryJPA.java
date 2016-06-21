package lt.vtvpmc.exam.entities.repositories.jpa;

import java.util.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import lt.vtvpmc.exam.entities.Survey;
import lt.vtvpmc.exam.entities.repositories.SurveyRepository;

public class SurveyRepositoryJPA implements SurveyRepository {

	private EntityManagerFactory entityManagerFactory;

	public void setEntityManagerFactory(EntityManagerFactory emf) {
		this.entityManagerFactory = emf;
	}

	private EntityManager getEntityManager() {
		return entityManagerFactory.createEntityManager();
	}

	@Override
	public void insertOrUpdate(Survey survey) {
		EntityManager entityManager = getEntityManager();
		try {
			entityManager.getTransaction().begin();
			boolean merged = false;
			for (Survey srv : survey.getClient().getSurveys()) {
				if (!entityManager.contains(srv) && srv.getId() != null) {
					entityManager.merge(srv);
					merged = true;
				} else
					entityManager.persist(srv);
			}
			if (merged) {
				entityManager.merge(survey);
			} else {
				entityManager.persist(survey);
			}
			entityManager.getTransaction().commit();
		} finally {
			entityManager.close();
		}

	}

	@Override
	public void delete(Survey survey) {
		EntityManager entityManager = getEntityManager();
		try {
			entityManager.getTransaction().begin();
			entityManager.createQuery("DELETE FROM Survey e WHERE e.id = :id").setParameter("id", survey.getId())
					.executeUpdate();
			entityManager.getTransaction().commit();
		} finally {
			entityManager.close();
		}
	}

	@Override
	public void deleteById(Long surveyId) {
		EntityManager entityManager = getEntityManager();
		try {
			entityManager.getTransaction().begin();
			Survey survey = entityManager.find(Survey.class, surveyId);
			if (survey != null)
				entityManager.remove(survey);
			entityManager.getTransaction().commit();
		} finally {
			entityManager.close();
		}
	}

	@Override
	public Long countAllSurveys() {
		EntityManager entityManager = getEntityManager();
		try {
			CriteriaBuilder cb = entityManager.getCriteriaBuilder();
			CriteriaQuery<Long> countQuery = cb.createQuery(Long.class);
			countQuery.select(cb.count(countQuery.from(Survey.class)));
			TypedQuery<Long> q = entityManager.createQuery(countQuery);
			return q.getSingleResult();
		} finally {
			entityManager.close();
		}
	}

	public List<Survey> findLastSurveys() {
		String jpql = "select e from Survey e order by e.company desc";
		// "SELECT a FROM Author a JOIN a.tags t WHERE t.tag = :searchTag"

		EntityManager entityManager = getEntityManager();
		try {
			TypedQuery<Survey> query = entityManager.createQuery(jpql, Survey.class);
			// query.setParameter("searchTag", tag);
			return query.getResultList();
		} finally {
			entityManager.close();
		}
	}

}
