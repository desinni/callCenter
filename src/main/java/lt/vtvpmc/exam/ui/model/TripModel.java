package lt.vtvpmc.exam.ui.model;

import lt.vtvpmc.exam.entities.Survey;

public class TripModel {

	private Survey selectedTrip = new Survey();

	public Survey getSelectedTrip() {
		return selectedTrip;
	}

	public void setSelectedTrip(Survey selectedTrip) {
		this.selectedTrip = selectedTrip;
	}

}
