package whiter4bbit.umloid.structure.classdiagram;

/**
 * @author whiter4bbit
 * аттрибут класса
 */
public class ClassDiagramAttribute{	
	
	private String name;
	
	private String type;
	
	private Long id;
		
	public final static long SCOPE_PUBLIC = 1;
	
	public final static long SCOPE_PRIVATE = 2;
	
	public final static long SCOPE_PROTECTED = 3;
	
	private long visibilityScope = SCOPE_PUBLIC;
	
	public ClassDiagramAttribute() {
		// TODO Auto-generated constructor stub
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	/**
	 * вернуть имя диаграммы
	 * @return имя
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * вернуть тип
	 * @return тип
	 */
	public String getType() {
		return type;
	}
	
	/**
	 * установить имя
	 * @param name имя
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * установить тип
	 * @param type тип
	 */
	public void setType(String type) {
		this.type = type;	
	}
		
	/**
	 * установить область видимости
	 * @param visibilityScope область видимости
	 */
	public void setVisibilityScope(long visibilityScope) {
		this.visibilityScope = visibilityScope;
	}
	
	/**
	 * получить область видимости
	 * @return область видимости
	 */
	public long getVisibilityScope() {
		return visibilityScope;
	}
	
	private boolean staticAttribute;
	
	/**
	 * является ли аттрибут статическим
	 * @param staticAttribute является ли аттрибут статическим
	 */
	public void setStaticAttribute(boolean staticAttribute) {
		this.staticAttribute = staticAttribute;
	}
	
	/**
	 * является ли аттрибут статическим
	 * @return staticAttribute является ли аттрибут статическим
	 */
	public boolean isStaticAttribute() {
		return staticAttribute;
	}
	
	@Override
	public String toString() {	
		return "Attribute [ id:"+getId()+"name:"+getName()+" attribute type "+getType()+" visibility scope "+getVisibilityScope()+"]";		
	}
}
