package whiter4bbit.umloid;

import java.util.HashMap;
import java.util.List;

import android.util.Log;

import whiter4bbit.umloid.structure.classdiagram.ClassDiagramAttribute;
import whiter4bbit.umloid.structure.classdiagram.ClassDiagramConnection;
import whiter4bbit.umloid.structure.classdiagram.ClassDiagramMethod;
import whiter4bbit.umloid.structure.classdiagram.ClassDiagramMethodParameter;
import whiter4bbit.umloid.structure.classdiagram.Diagram;
import whiter4bbit.umloid.tool.UMLoidHelper;

public class UMLoidStorage {	
	
	private static Diagram diagram;
	
	public static void setDiagram(Diagram diagram) {
		UMLoidStorage.diagram = diagram;		
	}
	
	public static Diagram getDiagram() {
		return diagram;
	}
	
	private static ClassDiagramMethod classDiagramMethod;
	
	public static void setClassDiagramMethod(
			ClassDiagramMethod classDiagramMethod) {
		UMLoidStorage.classDiagramMethod = classDiagramMethod;
	}
	
	public static ClassDiagramMethod getClassDiagramMethod() {
		return classDiagramMethod;
	}
	
	private static ClassDiagramAttribute classDiagramAttribute;
	
	public static ClassDiagramAttribute getClassDiagramAttribute() {
		return classDiagramAttribute;
	}
	
	public static void setClassDiagramAttribute( ClassDiagramAttribute classDiagramAttribute) {
		UMLoidStorage.classDiagramAttribute = classDiagramAttribute;
	}
	
	public static void updateAttribute(ClassDiagramAttribute attribute){
		attributes.put(attribute.getId(), attribute);
	}
	
	private static HashMap<Long, ClassDiagramAttribute> attributes = new HashMap<Long, ClassDiagramAttribute>();
	
	public static List<ClassDiagramAttribute> getAttributes() {
		return UMLoidHelper.makeListFromCollection(attributes.values());
	}
	
	public static void setAttributes(List<ClassDiagramAttribute> attribs) {		
		attributes = new HashMap<Long, ClassDiagramAttribute>();
		for(ClassDiagramAttribute attribute : attribs){
			attributes.put(attribute.getId(), attribute);			
		}
	}
	
	public static ClassDiagramAttribute getAttribute(Long id){	
		return attributes.get(id);		
	}
	
	public static void addAttribute(ClassDiagramAttribute classDiagramAttribute){
		classDiagramAttribute.setId(UMLoidHelper.getMax(getAttributes())+1);		
		attributes.put(classDiagramAttribute.getId(), classDiagramAttribute);		
	}
	
	public static void removeAttribute(Long id){
		attributes.remove(id);
	}
	
	private static HashMap<Long, ClassDiagramMethod> methods = new HashMap<Long, ClassDiagramMethod>();
	
	public static List<ClassDiagramMethod> getMethods(){
		return UMLoidHelper.makeListFromCollection(methods.values());
	}
	
	public static void setMethods(List<ClassDiagramMethod> methodz){
		methods = new HashMap<Long, ClassDiagramMethod>();
		for(ClassDiagramMethod method :methodz){
			methods.put(method.getId(), method);
		}
	}
	
	public static ClassDiagramMethod getMethod(Long id){
		return methods.get(id);
	}
	
	public static void addMethod(ClassDiagramMethod method){
		Log.i(UMLoidHelper.UMLOID_TAG, "Adding method["+method+"] to UMLoidStorage ");
		method.setId(UMLoidHelper.getMax(getMethods())+1);
		methods.put(method.getId(), method);
	}
	
	public static void updateMethod(ClassDiagramMethod method){
		methods.put(method.getId(), method);
	}

	
	public static void removeMethod(Long id){
		methods.remove(id);		
	}
	
	
	private static HashMap<Long, ClassDiagramMethodParameter> parameters = new HashMap<Long, ClassDiagramMethodParameter>();
	
	public static List<ClassDiagramMethodParameter> getParameters(){
		return UMLoidHelper.makeListFromCollection(parameters.values());
	}
	
	public static void setParameters(List<ClassDiagramMethodParameter> parameterz){
		parameters = new HashMap<Long, ClassDiagramMethodParameter>();
		for(ClassDiagramMethodParameter parameter :parameterz){
			parameters.put(parameter.getId(), parameter);
		}
	}
	
	public static ClassDiagramMethodParameter getParameter(Long id){
		return parameters.get(id);
	}
	
	public static void addParameter(ClassDiagramMethodParameter parameter){
		parameter.setId(UMLoidHelper.getMax(getParameters())+1);
		parameters.put(parameter.getId(), parameter);
	}
	
	public static void updateParameter(ClassDiagramMethodParameter parameter){
		parameters.put(parameter.getId(), parameter);
	}

	
	public static void removeParameter(Long id){
		parameters.remove(id);		
	}
	
	private static ClassDiagramMethodParameter parameter;
	
	public static void setParameter(ClassDiagramMethodParameter parameter) {
		UMLoidStorage.parameter = parameter;
	}
	
	public static ClassDiagramMethodParameter getParameter(){
		return parameter;
	}
	
	public static ClassDiagramConnection connection;
	
	public static ClassDiagramConnection getConnection() {
		return connection;
	}
	
	public static void setConnection(ClassDiagramConnection connection) {
		UMLoidStorage.connection = connection;
	}
}
