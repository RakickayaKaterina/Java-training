class Airplane{
	protected int id;
	protected String name,airline,pointOfDeparture,destination,departureTime,arrivalTime;
	boolean status;
	protected Pilot pilot;
	private Airplane(String name){
		this.name = name;
	}
	public Airplane(String name, String airline){
		this(name);
		pilot = new Pilot();
		this.airline = airline;
		System.out.println("It's class Airplane");
	}
	public int getId(){
		return id;
	}
	public String getName(){
		return name;
	}
	public String getAirline(){
		return airline;
	}
	public boolean getStatus(){
		return status;
	}
	public String getPointOfDeparture(){
		return pointOfDeparture;
	}
	public String getDestination(){
		return destination;
	}
	public String getDepartureTime(){
		return departureTime;
	}
	public String getArrivalTime(){
		return arrivalTime;
	}
	public Pilot getPilot(){
		return pilot;
	}
	private void changeStatus(){
		status=!status;
	}
	
	
}