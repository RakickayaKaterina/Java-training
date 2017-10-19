
public class Test {

	public static void main(String[] args) {
		Airport airport = new Airport(3);
		IManager manager = new Manager(airport);
		manager.addFlight(new Flight(1, 2));
		manager.addFlight(new Flight(2, 1));
		manager.addFlight(new Flight(3, 1));
		Passenger p1 = new Passenger(1, "Kate");
		Passenger p2 = new Passenger(2, "Alex");
		Passenger p3 = new Passenger(3, "Lapanya");
		manager.addPassengerToFlight(p1, 1);
		manager.addPassengerToFlight(p2, 1);
		manager.addPassengerToFlight(p3, 2);

		manager.showReport();

		manager.changeState(2, State.canceled);
		manager.changeState(3, State.delayed);

		manager.removePassengerFromFlight(p3, 1);
		manager.removePassengerFromFlight(p1, 1);
		manager.addPassengerToFlight(p2, 1);
		manager.addPassengerToFlight(p3, 1);
		manager.showReport();

	}

}
