package lt.vtvpmc.exam.entities.repositories;

import lt.vtvpmc.exam.entities.Trip;

public interface TripRepository {

	/*
	 * If item.id is set then update operation is performed
	 * If item.id == null then insert operation is performed
	 */
	public void insertOrUpdate(Trip trip);
	public void delete(Trip trip);
	public void deleteById(Long tripId);
	public Long countAllTrips();
}
