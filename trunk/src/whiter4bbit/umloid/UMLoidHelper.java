package whiter4bbit.umloid;

import whiter4bbit.umloid.classdiagram.Class;
import whiter4bbit.umloid.classdiagram.ClassDiagram;
import android.os.Bundle;


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
	
	public static Class getClassFromBundle(Bundle bundle){
		Class currentClass = null;
		if(bundle!=null){
			Integer classId = bundle.getInt(CLASS_ID_KEY);
			ClassDiagram classDiagram = (ClassDiagram)UMLoidStorage.getDiagram();
			currentClass = classDiagram.getClass(classId);
		}
		return currentClass;
	}

}
