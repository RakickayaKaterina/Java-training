
public class LineStep implements ILineStep{
	private int count;
	public LineStep(){
		count = 0;
		System.out.println("LineStep was created");
	}
	
	@Override
	public IProductPart buildProductPart() {
		IProductPart part;
		switch(count){
		case 0 :  part = new LaptopCase(); break;
		case 1 :  part = new Motherboard(); break;
		case 2 :  part = new Monitor(); break;
		default : return null;
		}
		System.out.println("Part №"+(count+1)+" was sent to the Assembling");
		count = ++count%3;
		
		return part;
	}
	
}
