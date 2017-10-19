
public interface IManager {
	public void addFlight(Flight pFlight);

	public void changeState(long idFlight, State pState);

	public void addPassengerToFlight(Passenger pPassenger, long idFlight);

	public void removePassengerFromFlight(Passenger pPassenger, long idFlight);

	public void showReport();

}
