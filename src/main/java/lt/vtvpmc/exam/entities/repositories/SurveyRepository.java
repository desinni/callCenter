package lt.vtvpmc.exam.entities.repositories;

import lt.vtvpmc.exam.entities.Survey;

public interface SurveyRepository {

	/*
	 * If item.id is set then update operation is performed
	 * If item.id == null then insert operation is performed
	 */
	public void insertOrUpdate(Survey survey);
	public void delete(Survey survey);
	public void deleteById(Long surveyId);
	public Long countAllSurveys();
}
