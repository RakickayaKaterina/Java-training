class Airfreighter extends Airplane{
	private int cargoCapacity;
	public Airfreighter(String name,String airline){
		super(name,airline);
		System.out.println("It's class Airfreighter");
	}
	public int getCargoCapacity(){
		return cargoCapacity;
	}
}