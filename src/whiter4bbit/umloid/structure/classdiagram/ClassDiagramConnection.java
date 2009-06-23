package whiter4bbit.umloid.structure.classdiagram;


/**
 * @author whiter4bbit
 * соединение 
 */
public class ClassDiagramConnection{

	private String role1;
	
	private String role2;
	
	private ClassDiagramClass class1;
	
	private ClassDiagramClass class2;
	
	private String multiplier1;
	
	private String multiplier2;
	
	private ClassDiagram classDiagram;
	
	private String name;
	
	private Integer id;
	
	public static final long TYPE_ASSOCIACION = 1;
	
	public static final long TYPE_COMPOSITION = 2;
	
	public static final long TYPE_AGGREGATION = 3;
	
	public static final long TYPE_GENERALIZATION = 4;
	
	public static final long TYPE_DEPENDENCY = 5;
	
	private long type = TYPE_ASSOCIACION;

	public ClassDiagramConnection() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * установить тип
	 * @param type тип
	 */
	public void setType(long type) {
		this.type = type;
	}
	
	/**
	 * вернуть тип
	 * @return тип
	 */
	public long getType() {
		return type;
	}
	
	public ClassDiagramConnection(String name, ClassDiagramClass class1, ClassDiagramClass class2, ClassDiagram classDiagram) {
		this.name = name;
		this.classDiagram = classDiagram;
		this.class1 = class1;
		this.class2 = class2;			
	}
	
	/**
	 * вернуть начальную точку (х)
	 * @return начальная точка (х)
	 */
	public Integer getX1() {
		return classDiagram.getClass(class1.getId()).getX()+50;
	}
	
	/**
	 * вернуть конечную точку (Х)
	 * @return конечная точка (х)
	 */
	public Integer getX2() {
		return classDiagram.getClass(class2.getId()).getX()+50;
	}
	
	/**
	 * вернуть начальное положение по (у)
	 * @return начальное положение по у
	 */
	public Integer getY1() {
		return classDiagram.getClass(class1.getId()).getY()+25;
	}
	
	/**
	 * вернуть кончное положение по у
	 * @return конечное положение по у
	 */
	public Integer getY2() {
		return classDiagram.getClass(class2.getId()).getY()+25;
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
			
	/**
	 * установить 1й класс связи
	 * @param class1 1й класс связи
	 */
	public void setClass1(ClassDiagramClass class1) {
		this.class1 = class1;
	}
	
	/**
	 * вернуть 1й класс связи
	 * @return 1й класс связи
	 */
	public ClassDiagramClass getClass1() {
		return class1;
	}
	
	/**
	 * установить 2й класс связи
	 * @param class2 2й класс связи
	 */
	public void setClass2(ClassDiagramClass class2) {
		this.class2 = class2;
	}
	
	/**
	 * вернуть 2й класс связи
	 * @return 2й класс связи
	 */
	public ClassDiagramClass getClass2() {
		return class2;
	}
	
	/**
	 * роль первого класса
	 * @param role1 роль первого класса
	 */
	public void setRole1(String role1) {
		this.role1 = role1;
	}
	
	/**
	 * вернуть имя роли первого класса
	 * @return роль первого класса
	 */
	public String getRole1() {
		return role1;
	}
	
	/**
	 * установить роль второго класса
	 * @param role2 роль второго класса
	 */
	public void setRole2(String role2) {
		this.role2 = role2;
	}
	
	/**
	 * вернуть роль второго класса
	 * @return роль второго класса
	 */
	public String getRole2() {
		return role2;
	}
	
	/**
	 * установить множитель первого класса
	 * @param multiplier1 множитель первого класса
	 */
	public void setMultiplier1(String multiplier1) {
		this.multiplier1 = multiplier1;
	}
	
	public String getMultiplier1() {
		return multiplier1;
	}
	
	/**
	 * установить множитель второго класса
	 * @param multiplier1 множитель второго класса
	 */
	public void setMultiplier2(String multiplier2) {
		this.multiplier2 = multiplier2;
	}
	
	public String getMultiplier2() {
		return multiplier2;
	}
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	private boolean selected = false;
	
	/**
	 * выбрана ли связь
	 * @return выбрана ли связь
	 */
	public boolean isSelected() {
		return selected;
	}
	
	/**
	 * установить выбранность связи
	 * @param selected выбранна ли связь
	 */
	public void setSelected(boolean selected) {
		this.selected = selected;
	}
	
	/**
	 * находится ли точка на связи
	 * @param x положение по х
	 * @param y положение по у
	 * @return находиться ли точка на связи
	 */
	public boolean isPointOn(int x, int y){
		boolean result = isPointOn(x, y, getX1(), getY1(), getX2(), getY2());		
		return result;
	}
	
	/**
	 * находится ли точка на связи
	 * @param x
	 * @param y
	 * @param x1
	 * @param y1
	 * @param x2
	 * @param y2
	 * @return находится ли точка на связи	
	 */
	public boolean isPointOn(int x, int y, int x1, int y1, int x2, int y2){
		double epsilon = 3;
		if(Math.abs(x1-x2)<epsilon){
			return (y>=y1 && y<=y2) || (y>=y2 && y<=y1);
		}
		if(Math.abs(y1-y2)<epsilon){			
			return (x>=x1 && x<=x2) || (x>=x2 && x<=x1);
		}
		
		double difference = (((double)x-(double)x1)/((double)x2-(double)x1))-(((double)y-(double)y1)/((double)y2-(double)y1));		
		return Math.abs(difference)<=0.1;
	}
	
	@Override
	public String toString() {	
		return "Connection [name="+name+", type="+getType()+" class1="+getClass1().getName()+" class2="+getClass2().getName()+"]";
	}
}
