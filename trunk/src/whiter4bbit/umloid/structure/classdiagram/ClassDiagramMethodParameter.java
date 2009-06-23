package whiter4bbit.umloid.structure.classdiagram;

/**
 * @author whiter4bbit
 * параметр метода
 */
public class ClassDiagramMethodParameter {
	
	private String name;
	
	private String type;
	
	private Long id;
	
	public ClassDiagramMethodParameter() {
		// TODO Auto-generated constructor stub
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	/**
	 * вернуть имя метода
	 * @return имя метода
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * вернуть тип метода 
	 * @return тип метода
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
	
	@Override
	public String toString() {	
		return "Method parameter [ id:"+getId()+"name:"+getName()+" attribute type "+getType()+" ]";		
	}
	
}
