
public class AssemblyLine implements IAssemblyLine {
	ILineStep lineStep;
	public AssemblyLine(ILineStep lineStep) {
		this.lineStep = lineStep;
		System.out.println("AssemblyLine installed");
	}
	@Override
	public IProduct assembleProduct(IProduct product) {
			product.installFirstProduct(lineStep.buildProductPart());
			product.installSecondPart(lineStep.buildProductPart());
			product.installThirdPart(lineStep.buildProductPart());
			return product;
	}
	public void setLineStep(ILineStep lineStep){
		this.lineStep = lineStep;
	}
	public ILineStep getLineStep(){
		return lineStep;
	}

}
