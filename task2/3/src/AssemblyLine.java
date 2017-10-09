
public class AssemblyLine implements IAssemblyLine {
	ILineStep lineStepLaptopCase, lineStepMonitor,lineStepMotherboard;
	
	public AssemblyLine(ILineStep lineStepLaptopCase, ILineStep lineStepMonitor, ILineStep lineStepMotherboard) {
		super();
		this.lineStepLaptopCase = lineStepLaptopCase;
		this.lineStepMonitor = lineStepMonitor;
		this.lineStepMotherboard = lineStepMotherboard;
		System.out.println("AssemblyLine installed");
	}
	@Override
	public IProduct assembleProduct(IProduct product) {
			product.installFirstProduct(lineStepLaptopCase.buildProductPart());
			System.out.println("Part Laptop Case installed");
			product.installSecondPart(lineStepMonitor.buildProductPart());
			System.out.println("Part Monitor installed");
			product.installThirdPart(lineStepMotherboard.buildProductPart());
			System.out.println("Part Motherboard installed");
			System.out.println("Product was made");
			return product;
			
	}
	
}
