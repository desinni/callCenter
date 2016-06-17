package lt.vtvpmc.exam.ui.controllers;

import java.util.*;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import lt.vtvpmc.exam.entities.Client;
import lt.vtvpmc.exam.entities.Trip;
import lt.vtvpmc.exam.entities.repositories.ClientRepository;
import lt.vtvpmc.exam.ui.model.ClientModel;
import lt.vtvpmc.exam.ui.model.TripModel;

public class ClientsListPageBean {

	private ClientModel clientModel;
	private ClientRepository clientRepo;
	private TripModel tripModel;

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

	public TripModel getTripModel() {
		return tripModel;
	}

	public void setTripModel(TripModel tripModel) {
		this.tripModel = tripModel;
	}

	public String saveNew() {
		Client train = clientModel.getSelectedClient();
		clientRepo.save(train);
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
		clientModel.setSelectedClient(client);
		return "viewCustomer";
	}
	
	public String showNewCustomerPage() {
		clientModel.setSelectedClient(new Client());
		return "addNewCustomer";
	}
	
	public String showNewTripPage() {
		tripModel.setSelectedTrip(new Trip());
		return "addNewTrip";
	}
	
//	public List<Client> topThreeClients() {
//		List<Client> topList = new ArrayList<Client>();
//		List<Client> allClients = this.getClientList();
//		for (int i = 0; i < allClients.size(); i++) {
//			if (allClients.get(i) )
//		}
//		return topList;
//	}
}
