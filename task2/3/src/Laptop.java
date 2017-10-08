
public class Laptop implements IProduct {
	private IProductPart part1;
	private IProductPart part2;
	private IProductPart part3;
	public Laptop(){
		System.out.println("Laptop was created without parties");
	}
	
	@Override
	public void installFirstProduct(IProductPart part1) {
		this.part1 = part1;
		System.out.println("Installed the first part of product");

	}

	@Override
	public void installSecondPart(IProductPart part2) {
		this.part2 = part2;
		System.out.println("Installed the second part of product");

	}

	@Override
	public void installThirdPart(IProductPart part3) {
		this.part3 = part3;
		System.out.println("Installed the third part of product");

	}

}
