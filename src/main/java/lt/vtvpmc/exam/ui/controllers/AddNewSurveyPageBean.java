package lt.vtvpmc.exam.ui.controllers;

import java.util.Calendar;

import lt.vtvpmc.exam.entities.Client;
import lt.vtvpmc.exam.entities.Survey;
import lt.vtvpmc.exam.entities.repositories.ClientRepository;
import lt.vtvpmc.exam.entities.repositories.SurveyRepository;
import lt.vtvpmc.exam.ui.model.ClientModel;
import lt.vtvpmc.exam.ui.model.SurveyModel;

public class AddNewSurveyPageBean {

	private ClientRepository clientRepo;
	private SurveyRepository surveyRepo;
	private ClientModel clientModel;
	private SurveyModel surveyModel;
	private ClientsListPageBean clientsListPageBean;

	public ClientRepository getClientRepo() {
		return clientRepo;
	}

	public void setClientRepo(ClientRepository clientRepo) {
		this.clientRepo = clientRepo;
	}

	public SurveyRepository getSurveyRepo() {
		return surveyRepo;
	}

	public void setSurveyRepo(SurveyRepository surveyRepo) {
		this.surveyRepo = surveyRepo;
	}

	public SurveyModel getSurveyModel() {
		return surveyModel;
	}

	public void setSurveyModel(SurveyModel surveyModel) {
		this.surveyModel = surveyModel;
	}

	public ClientModel getClientModel() {
		return clientModel;
	}

	public void setClientModel(ClientModel clientModel) {
		this.clientModel = clientModel;
	}

	public ClientsListPageBean getClientsListPageBean() {
		return clientsListPageBean;
	}

	public void setClientsListPageBean(ClientsListPageBean clientsListPageBean) {
		this.clientsListPageBean = clientsListPageBean;
	}

	public String addNew() {
		Client client = clientModel.getSelectedClient();
		Survey newSurvey = surveyModel.getSelectedSurvey();
		newSurvey.setDate(Calendar.getInstance().getTime());
		newSurvey.setClient(client);
		client.addSurvey(newSurvey);
		clientRepo.save(client);

		return "viewCustomer";
	}
}
