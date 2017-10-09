
public class Test {

	public static void main(String[] args) {
		ILineStep lineStep1 = new LineStepLaptopCase();
		ILineStep lineStep2 = new LineStepMonitor();
		ILineStep lineStep3 = new LineStepMotherboard();
		IAssemblyLine assemblyLine = new AssemblyLine(lineStep1,lineStep2,lineStep3);
		assemblyLine.assembleProduct(new Laptop());

	}

}
