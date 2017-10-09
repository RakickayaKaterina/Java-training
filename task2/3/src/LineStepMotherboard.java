
public class LineStepMotherboard implements ILineStep {

	public LineStepMotherboard(){
		System.out.println("LineStepMotherboard was created");
	}
	
	@Override
	public IProductPart buildProductPart() {
		return new Motherboard();
	}

}
