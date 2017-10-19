import java.util.Arrays;

public class Flight {
	private long id;
	private Passenger[] passengers;
	private State state;

	public Flight(long id, int amountSeats) {
		super();
		this.id = id;
		passengers = new Passenger[amountSeats];
		state = State.active;
	}

	public void addPassenger(Passenger p) {
		if (ArraysOperation.existPosition(passengers)) {
			int position = ArraysOperation.getFreePosition(passengers);
			passengers[position] = p;
		} else
			Printer.show("seat not found");
	}

	private int findPassenger(Passenger p) {
		for (int i = 0; i < passengers.length; i++) {
			if (passengers[i] != null && passengers[i].equals(p))
				return i;
		}
		return -1;
	}

	private void clearPlace(int index) {
		passengers[index] = null;
		int position = ArraysOperation.getLastPosition(passengers);
		if (position > 0) {
			ArraysOperation.swap(passengers, index, position);
		}

	}

	public void removePassenger(Passenger p) {
		int removeIndex = findPassenger(p);
		if (removeIndex >= 0) {
			clearPlace(removeIndex);
		} else {
			Printer.show(String.format("Passenger %s not found in flight id=%s", p.getName(), id));
		}
	}

	public int getFreeSeats() {
		return ArraysOperation.getCountFreePosition(passengers);

	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Passenger[] getPassengers() {
		return passengers;
	}

	public void setPassengers(Passenger[] passengers) {
		this.passengers = passengers;
	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

	@Override
	public String toString() {
		return String.format("Flight [id=%s, state=%s, freeSeats=%s]", id, state, getFreeSeats());
	}

}
