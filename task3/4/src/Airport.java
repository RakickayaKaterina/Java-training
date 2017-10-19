
public class Airport {
	private Flight[] flights;

	public Airport(int size) {
		super();
		flights = new Flight[size];
	}

	public void addFlight(Flight f) {
		if (ArraysOperation.existPosition(flights)) {
			int position = ArraysOperation.getFreePosition(flights);
			flights[position] = f;
		} else
			Printer.show("Flight wasn't added");
	}

	public Flight findFlight(long id) {
		for (int i = 0; i < flights.length; i++) {
			if (flights[i].getId() == id)
				return flights[i];
		}
		Printer.show("This Flight is not found");
		return null;
	}

	public Flight[] getFlights() {
		return flights;
	}

}
