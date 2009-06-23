package whiter4bbit.umloid.activity;

import java.util.HashMap;

import whiter4bbit.umloid.structure.classdiagram.ClassDiagramAttribute;
import whiter4bbit.umloid.structure.classdiagram.ClassDiagramClass;
import whiter4bbit.umloid.structure.classdiagram.ClassDiagramConnection;
import whiter4bbit.umloid.structure.classdiagram.ClassDiagramMethod;

public class AdapterProperties {

	public final static HashMap<Long, String> methodVisibilityProperties = new HashMap<Long, String>();
	
	public final static HashMap<Long, String> classVisibilityProperties = new HashMap<Long, String>();
	
	public final static HashMap<Long, String> attributeVisibilityProperties = new HashMap<Long, String>();
	
	public final static HashMap<Long, String> classTypeProperties = new HashMap<Long, String>();
	
	public final static HashMap<Long, String> connectionTypeProperties = new HashMap<Long, String>();
	
	static{
		methodVisibilityProperties.put(ClassDiagramMethod.SCOPE_PRIVATE,"private");
		methodVisibilityProperties.put(ClassDiagramMethod.SCOPE_PUBLIC,"public");
		methodVisibilityProperties.put(ClassDiagramMethod.SCOPE_PROTECTED,"protected");
		methodVisibilityProperties.put(ClassDiagramMethod.SCOPE_REALIZATION,"realization");
		
		classVisibilityProperties.put(ClassDiagramClass.SCOPE_PRIVATE,"private");
		classVisibilityProperties.put(ClassDiagramClass.SCOPE_PUBLIC,"public");
		classVisibilityProperties.put(ClassDiagramClass.SCOPE_PROTECTED,"protected");
		classVisibilityProperties.put(ClassDiagramClass.SCOPE_REALIZATION,"realization");
		
		attributeVisibilityProperties.put(ClassDiagramAttribute.SCOPE_PRIVATE,"private");
		attributeVisibilityProperties.put(ClassDiagramAttribute.SCOPE_PUBLIC,"public");
		attributeVisibilityProperties.put(ClassDiagramAttribute.SCOPE_PROTECTED,"protected");
		
		classTypeProperties.put(ClassDiagramClass.TYPE_CLASS, "class");
		classTypeProperties.put(ClassDiagramClass.TYPE_INTERFACE, "interface");
		
		connectionTypeProperties.put(ClassDiagramConnection.TYPE_AGGREGATION,"Aggregation");
		connectionTypeProperties.put(ClassDiagramConnection.TYPE_ASSOCIACION,"Association");
		connectionTypeProperties.put(ClassDiagramConnection.TYPE_COMPOSITION,"Composition");
		connectionTypeProperties.put(ClassDiagramConnection.TYPE_DEPENDENCY,"Dependency");
		connectionTypeProperties.put(ClassDiagramConnection.TYPE_GENERALIZATION,"Generalization");		
	}
	
	public static int getPositionOf(HashMap<Long, String> map, Long key){
		int pos = 0;
		for(Long currentKey : map.keySet()){
			pos++;			
			if(currentKey == key){
				return pos-1;				
			}
		}
		return -1;
	}
}
