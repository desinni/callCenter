package lt.vtvpmc.exam.ui.model;

import lt.vtvpmc.exam.entities.Trip;

public class TripModel {

	private Trip selectedTrip = new Trip();

	public Trip getSelectedTrip() {
		return selectedTrip;
	}

	public void setSelectedTrip(Trip selectedTrip) {
		this.selectedTrip = selectedTrip;
	}

}
