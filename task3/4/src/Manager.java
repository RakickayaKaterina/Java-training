
public class Manager implements IManager {
	private Airport mAirport;

	public Manager(Airport mAirport) {
		super();
		this.mAirport = mAirport;
	}

	@Override
	public void addFlight(Flight pFlight) {
		mAirport.addFlight(pFlight);

	}

	@Override
	public void changeState(long idFlight, State pState) {
		Flight f = mAirport.findFlight(idFlight);
		if (f != null)
			f.setState(pState);

	}

	@Override
	public void addPassengerToFlight(Passenger pPassenger, long idFlight) {
		Flight f = mAirport.findFlight(idFlight);
		f.addPassenger(pPassenger);

	}

	@Override
	public void removePassengerFromFlight(Passenger pPassenger, long idFlight) {
		Flight f = mAirport.findFlight(idFlight);
		f.removePassenger(pPassenger);

	}

	@Override
	public void showReport() {
		for (Flight flight : mAirport.getFlights()) {
			Printer.show(flight.toString());
		}

	}

}
