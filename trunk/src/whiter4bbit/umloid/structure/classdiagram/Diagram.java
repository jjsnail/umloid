package whiter4bbit.umloid.structure.classdiagram;

public abstract class Diagram {			
	
	protected String name;
	
	public Diagram() {
		// TODO Auto-generated constructor stub
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {	
		return "Diagram [Name="+name+"]";
	}
}
