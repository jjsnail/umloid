package whiter4bbit.umloid;


public class UMLoidHelper {
	
	public static final String DIAGRAM_TYPE_KEY = "diagramtype_";
	
	public static final String UMLOID_TAG = "UMLoid_";
	
	public static final int CLASS_DIAGRAM = 0;
	
	public static final int ACTIVITY_DIAGRAM = 1;
	
	public static final String DIAGRAM_NAME_KEY = "whiter4bbit.umloid.classdiagram.DiagramName";	
	
	public static final String DIAGRAM_STATE = "whiter4bbit.umloid.classdiagram.DiagramState";
	
	public static final String CLASS_DIAGRAM_KEY = "whiter4bbit.umloid.classdiagram.Class";
	
	public static final String CLASS_ID_KEY = "whiter4bbit.umloid.classdiagram.Id";
	
	public static final int DIAGRAM_STATE_CREATE = 0;
	
	public static final int DIAGRAM_STATE_LOAD = 1;	
	
//	public static Class getClassByCoordinate(int x, int y, ClassDiagram classDiagram){
//		for(Class cClass : classDiagram.getClasses()){
//			if( x>=cClass.getX() && y>=cClass.getY() 
//						&& x<=cClass.getX()+ClassShape.CLASS_RECTANGLE_WIDTH
//						&& y<=cClass.getY()+ClassShape.CLASS_RECTANGLE_HEIGHT){
//				return cClass;				
//			}
//		}
//		return null;
//	}
}
