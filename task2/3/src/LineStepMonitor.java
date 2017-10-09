
public class LineStepMonitor implements ILineStep {

	public LineStepMonitor(){
		System.out.println("LineStepMonitor was created");
	}
	
	@Override
	public IProductPart buildProductPart() {
		return new Monitor();
	}

}
