package whiter4bbit.umloid.structure.classdiagram;

import java.util.HashMap;
import java.util.List;

import whiter4bbit.umloid.tool.UMLoidHelper;


/**
 * @author whiter4bbit
 * диаграмма классов
 */
public class ClassDiagramClass{
	
	public static final Long TYPE_CLASS = 0L;
	
	public static final Long TYPE_INTERFACE = 1L;
	
	public static final Long SCOPE_PRIVATE = 0L;
	
	public static final Long SCOPE_PUBLIC = 1L;
	
	public static final Long SCOPE_PROTECTED = 2L;
	
	public static final Long SCOPE_REALIZATION = 3L;
	
	private Long visibilityScope = SCOPE_PRIVATE;
	
	public ClassDiagramClass() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * установить область видимости
	 * @param visibilityScope область видимости
	 */
	public void setVisibilityScope(Long visibilityScope) {
		this.visibilityScope = visibilityScope;
	}
	
	/**
	 * получить область вилимости
	 * @return область видимости
	 */
	public Long getVisibilityScope() {
		return visibilityScope;
	}
	
	private Long type = TYPE_CLASS;
	
	/**
	 * установить тип
	 * @param type тип
	 */
	public void setType(Long type) {
		this.type = type;
	}
	
	/**
	 * получить тип
	 * @return тип
	 */
	public Long getType() {
		return type;
	}

	private Integer id;
	
	/**
	 * установить идентификатор
	 * @param id идентификатор
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	
	/**
	 * получить идентификатор
	 * @return идентификатор
	 */
	public Integer getId() {
		return id;
	}
	
	private String name;
	
	/**
	 * установить имя
	 * @param name имя
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * получить имя
	 * @return имя
	 */
	public String getName() {
		return name;
	}
	
	public ClassDiagramClass(String name) {
		this.name = name;
	}	
	
	private int x;
	
	private int y;
	
	/**
	 * установить положение 
	 * @param x положение по х
	 * @param y положение по у
	 */
	public void setPos(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	/**
	 * вернуть положение по у
	 * @return положение по у
	 */
	public int getY() {
		return y;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	/**
	 * вернуть положение по х
	 * @return положение по х
	 */
	public int getX() {
		return x;
	}
	
	private HashMap<Long, ClassDiagramMethod> methods = new HashMap<Long, ClassDiagramMethod>();	
	
	/**
	 * вернуть список методов
	 * @return список методов
	 */
	public List<ClassDiagramMethod> getMethods() {		
		return UMLoidHelper.makeListFromCollection(methods.values());
	}
	
	/**
	 * вернуть метод
	 * @param id идентификатор
	 * @return метод
	 */
	public ClassDiagramMethod getMethod(Long id){
		return methods.get(id);
	}
	
	/**
	 * установить методы
	 * @param methods методы
	 */
	public void setMethods(List<ClassDiagramMethod> methods) {		
		this.methods = new HashMap<Long, ClassDiagramMethod>();
		for(ClassDiagramMethod method : methods){
			this.methods.put(method.getId(), method);			
		}
	}	

	/**
	 * добавить метод
	 * @param method метод
	 */
	public void addMethod(ClassDiagramMethod method){
		method.setId( UMLoidHelper.getMax(getMethods())+1 );		
		this.methods.put(method.getId(), method);		
	}
	
	/**
	 * удалить метод
	 * @param id идентификатор
	 */
	public void removeMethod(Long id){
		methods.remove(id);		
	}
	
	/**
	 * обновить метод
	 * @param method метод
	 */
	public void updateMethod(ClassDiagramMethod method){
		this.methods.put(method.getId(), method);
	}
	
	private HashMap<Long, ClassDiagramAttribute> attributes = new HashMap<Long, ClassDiagramAttribute>();
	
	/**
	 * установить атрибуты
	 * @param attribs атрибуты
	 */
	public void setAttributes(List<ClassDiagramAttribute> attribs) {		
		attributes = new HashMap<Long, ClassDiagramAttribute>();
		for(ClassDiagramAttribute attribute : attribs){
			attributes.put(attribute.getId(), attribute);			
		}
	}	
	
	/**
	 * получить атрибуты
	 * @return атрибуты
	 */
	public List<ClassDiagramAttribute> getAttributes(){
		return UMLoidHelper.makeListFromCollection(attributes.values());
	}
	
	/**
	 * получить атрибут по идентифитикатору
	 * @param id идентификатор
	 * @return аттрибут
	 */
	public ClassDiagramAttribute getAttribute(Long id){
		return attributes.get(id);		
	}
	
	/**
	 * добавить атрибут
	 * @param attribute атрибут
	 */
	public void addAttribute(ClassDiagramAttribute attribute){
		attribute.setId(UMLoidHelper.getMax(getAttributes())+1);
		attributes.put(attribute.getId(), attribute);
	}
	
	/**
	 * удалиьть атрибут
	 * @param id иденфификатор
	 */
	public void removeAttribute(Long id){
		attributes.remove(id);
	}
	
	/**
	 * обновить атрибут
	 * @param attribute атрибут
	 */
	public void updateAttribute(ClassDiagramAttribute attribute){
		attributes.put(attribute.getId(), attribute);
	}
	
	@Override
	public String toString() {
		return "Class [name="+name+", attributes="+attributes.size()+", type="+getType()+" methods="+methods.size()+"]";
	}
	
}
