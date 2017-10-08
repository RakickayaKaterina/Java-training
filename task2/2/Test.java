class Test{
	public static void main(String[] args){
		Airliner airliner = new Airliner("Some name for Airliner","Some Airline for Airliner");
		Airplane airfreighter = new Airfreighter("Some name for Airfreighter","Some Airline for Airliner");
		Passenger passenger = new Passenger();
		airliner.addPassenger(passenger);
	}
}