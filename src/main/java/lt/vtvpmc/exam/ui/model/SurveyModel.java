package lt.vtvpmc.exam.ui.model;

import lt.vtvpmc.exam.entities.Survey;

public class SurveyModel {

	private Survey selectedSurvey = new Survey();

	public Survey getSelectedSurvey() {
		return selectedSurvey;
	}

	public void setSelectedSurvey(Survey selectedSurvey) {
		this.selectedSurvey = selectedSurvey;
	}

	

}
