package whiter4bbit.umloid;

import whiter4bbit.umloid.classdiagram.Diagram;

/**
 * @author whiter4bbit
 * a storage for storing current diagram
 */
public class UMLoidStorage {	
	
	private static Diagram diagram;
	
	public static void setDiagram(Diagram diagram) {
		UMLoidStorage.diagram = diagram;		
	}
	
	public static Diagram getDiagram() {
		return diagram;
	}
	
}
