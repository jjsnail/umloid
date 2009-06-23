package whiter4bbit.umloid.tool;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import whiter4bbit.umloid.UMLoidStorage;
import whiter4bbit.umloid.structure.classdiagram.ClassDiagram;
import whiter4bbit.umloid.structure.classdiagram.ClassDiagramClass;
import android.os.Bundle;
import android.util.Log;


public class UMLoidHelper {
	
	public static final String DIAGRAM_TYPE_KEY = "diagramtype_";
	
	public static final String UMLOID_TAG = "UMLoid_";
	
	public static final int CLASS_DIAGRAM = 0;
	
	public static final int ACTIVITY_DIAGRAM = 1;
	
	public static final String DIAGRAM_NAME_KEY = "whiter4bbit.umloid.classdiagram.DiagramName";	
	
	public static final String DIAGRAM_STATE = "whiter4bbit.umloid.classdiagram.DiagramState";
	
	public static final String CLASS_DIAGRAM_KEY = "whiter4bbit.umloid.classdiagram.Class";
	
	public static final String CLASS_ID_KEY = "whiter4bbit.umloid.classdiagram.Id";	
	
	public static final String ACTION = "action";
	
	public static final String DIAGRAM_FILE = "whiter4bbit.umloid.diagramfile";
	
	public static final int DIAGRAM_STATE_CREATE = 0;
	
	public static final int DIAGRAM_STATE_LOAD = 1;
	
	public static final int ACTION_CREATE = 0;
	
	public static final int ACTION_MODIFY = 1;
	
	public static ClassDiagramClass getClassFromBundle(Bundle bundle){
		ClassDiagramClass currentClass = null;
		if(bundle!=null){
			Integer classId = bundle.getInt(CLASS_ID_KEY);
			ClassDiagram classDiagram = (ClassDiagram)UMLoidStorage.getDiagram();
			currentClass = classDiagram.getClass(classId);
		}
		return currentClass;
	}
	
	
	public static <T extends Object> List<T> makeListFromCollection(Collection<T> collection){
		List<T> list = new ArrayList<T>();
		for(Object o: collection){			
			list.add((T)o);
		}
		return list;
	}
	
	public static <T extends Object> Object getClassInstanceProperty(T obj,String propertyName){
		Object id = null;
		try{
			String className = ((T)obj).getClass().getName();
			java.lang.Class clazz = java.lang.Class.forName(className);			
			Method method = clazz.getMethod("get"+propertyName);			
			id = method.invoke(obj);			
		}catch (InvocationTargetException e) {
			Log.e(UMLOID_TAG, e.getMessage());			
		}
		catch (IllegalAccessException e1) {
			Log.e(UMLOID_TAG, e1.getMessage());
		}
		catch (NoSuchMethodException e2) {			
			Log.e(UMLOID_TAG, e2.getMessage());
		}
		catch (ClassNotFoundException e3) {
			Log.e(UMLOID_TAG, e3.getMessage());
		}		
		return id;
	}

	
	public static <T extends Object> Long getMax(Collection<T> elements){		
		Long max = 0L;
		Iterator<T> iterator = elements.iterator();
		if(iterator.hasNext()){
			max = (Long) getClassInstanceProperty(iterator.next(), "Id");		
		}
		for(T t : elements){
			if( (Long) getClassInstanceProperty(t, "Id") > max ){
				max = (Long) getClassInstanceProperty(t, "Id");
			}			
		}		
		return max;		
	}
	
	
		
}
