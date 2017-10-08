
public class Test {

	public static void main(String[] args) {
		ILineStep lineStep = new LineStep();
		IAssemblyLine assemblyLine = new AssemblyLine(lineStep);
		assemblyLine.assembleProduct(new Laptop());

	}

}
