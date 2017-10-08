import java.util.List;
import java.util.ArrayList;

class Airliner extends Airplane{
	private int amountOfSeats;
	private List<Passenger> passengers;
	public Airliner(String name, String airline){
		super(name,airline);
		passengers = new ArrayList<>();
		System.out.println("It's class Airliner");
	}
	public int getAmountOfSeats(){
		return amountOfSeats;
	}
	public void addPassenger(Passenger p){
		passengers.add(p);
	}
}