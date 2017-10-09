
public class LineStepLaptopCase implements ILineStep {

	
	public LineStepLaptopCase(){
		System.out.println("LineStepLaptopCase was created");
	}
	
	@Override
	public IProductPart buildProductPart() {
		return new LaptopCase();
	}
	
}
