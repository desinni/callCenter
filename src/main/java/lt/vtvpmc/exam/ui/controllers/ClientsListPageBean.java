package lt.vtvpmc.exam.ui.controllers;

import java.util.*;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import lt.vtvpmc.exam.entities.Client;
import lt.vtvpmc.exam.entities.Survey;
import lt.vtvpmc.exam.entities.repositories.ClientRepository;
import lt.vtvpmc.exam.ui.model.ClientModel;
import lt.vtvpmc.exam.ui.model.SurveyModel;

public class ClientsListPageBean {

	static final Logger log = LoggerFactory.getLogger(ClientsListPageBean.class);

	private ClientModel clientModel;
	private ClientRepository clientRepo;
	private SurveyModel surveyModel;

	public ClientModel getClientModel() {
		return clientModel;
	}

	public void setClientModel(ClientModel clientModel) {
		this.clientModel = clientModel;
	}

	public ClientRepository getClientRepo() {
		return clientRepo;
	}

	public void setClientRepo(ClientRepository clientRepo) {
		this.clientRepo = clientRepo;
	}

	public List<Client> getClientList() {
		return clientRepo.findAll();
	}

	public SurveyModel getSurveyModel() {
		return surveyModel;
	}

	public void setSurveyModel(SurveyModel surveyModel) {
		this.surveyModel = surveyModel;
	}

	public String saveNew() {
		Client client = clientModel.getSelectedClient();
		clientRepo.save(client);
		clientModel.setSelectedClient(new Client());
		return "main";
	}

	public String deleteSelected(Client client) {
		if (client == null) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, "Warning!", "Client being deleted is null?"));
		} else {
			clientRepo.delete(client);
		}
		return "main";
	}

	public String showMoreInfoPage(Client client) {
		log.debug(">>>>> Will show currently selected client in showMoreInfoPage: {}", client);
		clientModel.setSelectedClient(client);
		return "viewCustomer";
	}

	public String showNewCustomerPage() {
		clientModel.setSelectedClient(new Client());
		return "addNewCustomer";
	}

	public String showNewSurveyPage() {
		surveyModel.setSelectedSurvey(new Survey());
		surveyModel.getSelectedSurvey().setDate(Calendar.getInstance().getTime());
		return "addNewSurvey";
	}

	public double countAverageEvaluation() {
		double sum = 0;
		for (Client client : clientRepo.findAll()) {
			if (client.getSurveys() != null && !client.getSurveys().isEmpty()) {
				sum += client.getSurveys().get(client.getSurveys().size() - 1).getEvaluation();
			}
		}
		double average = sum / clientRepo.findAll().size();
		double roundAverage = (double) Math.round(average * 100) / 100;
		return roundAverage;
	}

	private BarChartModel barModel;

	public BarChartModel getBarModel() {
		createBarModel();
		return barModel;
	}

	private BarChartModel initBarModel() {
		BarChartModel model = new BarChartModel();

		ChartSeries kritikai = new ChartSeries();
		kritikai.setLabel("Kritikai");

		ChartSeries neutralus = new ChartSeries();
		neutralus.setLabel("Neutralus");

		ChartSeries skatintojai = new ChartSeries();
		skatintojai.setLabel("Skatintojai");

		int critics = 0;
		int neutrals = 0;
		int promoters = 0;
		for (Client client : clientRepo.findAll()) {
			if (client.getSurveys() != null && !client.getSurveys().isEmpty()) {
				if (client.getSurveys().get(client.getSurveys().size() - 1).getRecommendation() <= 6) {
					critics++;
				} else if (client.getSurveys().get(client.getSurveys().size() - 1).getRecommendation() >= 7
						&& client.getSurveys().get(client.getSurveys().size() - 1).getRecommendation() <= 8) {
					neutrals++;
				} else {
					promoters++;
				}
			}
		}

		kritikai.set("", critics);
		neutralus.set("", neutrals);
		skatintojai.set("", promoters);

		model.addSeries(kritikai);
		model.addSeries(neutralus);
		model.addSeries(skatintojai);

		return model;
	}

	private void createBarModel() {
		barModel = initBarModel();

		barModel.setTitle("Bar Chart");
		barModel.setLegendPosition("e");

		 Axis xAxis = barModel.getAxis(AxisType.X);
		 xAxis.setLabel("Clients");

		Axis yAxis = barModel.getAxis(AxisType.Y);
		yAxis.setLabel("Number");
		yAxis.setMin(0);
		yAxis.setMax(50);
	}

}
