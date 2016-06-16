package lt.vtvpmc.exam.ui.model;

import lt.vtvpmc.exam.entities.Client;

public class ClientModel {

	private Client selectedClient = new Client();

	public Client getSelectedClient() {
		return selectedClient;
	}

	public void setSelectedClient(Client selectedClient) {
		this.selectedClient = selectedClient;
	}

}
