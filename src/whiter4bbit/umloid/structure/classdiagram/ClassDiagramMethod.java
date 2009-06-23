package whiter4bbit.umloid.structure.classdiagram;

import java.util.HashMap;
import java.util.List;

import whiter4bbit.umloid.tool.UMLoidHelper;

/**
 * @author whiter4bbit
 * метод 
 */
public class ClassDiagramMethod{
	
	private String name;
	
	private Long id;
	
	private boolean staticMethod = false;
	
	private boolean abstractMethod = false;
	
	public final static long SCOPE_PUBLIC = 1;
	
	public final static long SCOPE_PRIVATE = 2;
	
	public final static long SCOPE_PROTECTED = 3;
	
	public final static long SCOPE_REALIZATION = 4;	
	
	public ClassDiagramMethod() {
		// TODO Auto-generated constructor stub
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public Long getId() {
		return id;
	}
	
	public ClassDiagramMethod(String name){
		this.name = name;
	}
	
	/**
	 * установить имя
	 * @param name имя
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * вернуть имя
	 * @return имя
	 */
	public String getName() {
		return name;
	}
	
	private String type;
	
	/**
	 * установить тип
	 * @param type тип
	 */
	public void setType(String type) {
		this.type = type;
	}
	
	/**
	 * вернуть тип
	 * @return тип
	 */
	public String getType() {
		return type;
	}
		
	/**
	 * установить является ли метод статическим
	 * @param staticMethod является ли метод статическим
	 */
	public void setStaticMethod(boolean staticMethod) {
		this.staticMethod = staticMethod;
	}
	
	/**
	 * установить является ли метод абстрактным
	 * @param abstractMethod является ли метод абстрактным
	 */
	public void setAbstractMethod(boolean abstractMethod) {
		this.abstractMethod = abstractMethod;
	}
	
	/**
	 * вернуть является ли метод статическим
	 * @return является ли метод статическим
	 */
	public boolean isStaticMethod() {
		return staticMethod;
	}

	/**
	 * вернуть является ли метод абстрактным
	 * @return является ли метод абстрактным
	 */
	public boolean isAbstractMethod() {
		return abstractMethod;
	}	
	
	private long visibilityScope = SCOPE_PRIVATE;

	/**
	 * установить область видимости
	 * @param visibilityScope область видимости
	 */
	public void setVisibilityScope(long visibilityScope) {
		this.visibilityScope = visibilityScope;
	}
	
	/**
	 * вернуть область видимости
	 * @return область видимости
	 */
	public long getVisibilityScope() {
		return visibilityScope;
	}
	
	private HashMap<Long, ClassDiagramMethodParameter> parameters = new HashMap<Long, ClassDiagramMethodParameter>();
	
	/**
	 * вернуть параметры
	 * @return параметры
	 */
	public List<ClassDiagramMethodParameter> getParameters(){
		return UMLoidHelper.makeListFromCollection(parameters.values());
	}
	
	/**
	 * добавить параметр
	 * @param parameter параметр
	 */
	public void addParameter(ClassDiagramMethodParameter parameter){
		parameter.setId(UMLoidHelper.getMax(getParameters())+1);
		parameters.put(parameter.getId(), parameter);
	}
	
	/**
	 * удалить параметр
	 * @param parameter параметр
	 */
	public void deleteParameter(ClassDiagramMethodParameter parameter){
		parameters.remove(parameter);
	}
	
	/**
	 * вернуть параметр
	 * @param id идентфикатор
	 * @return параметр
	 */
	public ClassDiagramMethodParameter getParameter(Long id){
		return parameters.get(id);
	}
	
	/**
	 * установить параметры
	 * @param parameterz параметры
	 */
	public void setParameters(List<ClassDiagramMethodParameter> parameterz){
		parameters = new HashMap<Long, ClassDiagramMethodParameter>();
		for(ClassDiagramMethodParameter parameter :parameterz){
			parameters.put(parameter.getId(), parameter);
		}
	}
	
	@Override
	public String toString() {
		return "Method [name="+name+", parameters="+parameters.size()+", type="+type+"]";
	}
}
