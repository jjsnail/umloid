package whiter4bbit.umloid.classdiagram;

import java.io.Serializable;

public class Connection implements Serializable{

	private String role1;
	
	private String role2;
	
	private Class class1;
	
	private Class class2;
	
	private int multiplier1;
	
	private int multiplier2;
	
	private String name;
	
	private Integer id;
	
	public Connection(String name, Class class1, Class class2) {
		this.name = name;
		this.class1 = class1;
		this.class2 = class2;	
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
			
	public void setClass1(Class class1) {
		this.class1 = class1;
	}
	
	public Class getClass1() {
		return class1;
	}
	
	public void setClass2(Class class2) {
		this.class2 = class2;
	}
	
	public Class getClass2() {
		return class2;
	}
	
	public void setRole1(String role1) {
		this.role1 = role1;
	}
	
	public String getRole1() {
		return role1;
	}
	
	public void setRole2(String role2) {
		this.role2 = role2;
	}
	
	public String getRole2() {
		return role2;
	}
	
	public void setMultiplier1(int multiplier1) {
		this.multiplier1 = multiplier1;
	}
	
	public int getMultiplier1() {
		return multiplier1;
	}
	
	public void setMultiplier2(int multiplier2) {
		this.multiplier2 = multiplier2;
	}
	
	public int getMultiplier2() {
		return multiplier2;
	}
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	private boolean selected = false;
	
	public boolean isSelected() {
		return selected;
	}
	
	public void setSelected(boolean selected) {
		this.selected = selected;
	}
	
	public boolean isPointOn(int x, int y){
		double epsilon = 3;
		int x1 = getClass1().getX()+50, x2 = getClass2().getX()+50;
		int y1 = getClass1().getY()+25, y2 = getClass2().getY()+25;
		if(x1==x2){
			return (y>=y1 && y<=y2) || (y>=y2 && y<=y1);
		}
		if(y1==y2){
			return (x>=x1 && x<=x2) || (x>=x2 && x<=x1);
		}
		double k = (y1-y2)/(x1-x2);
		double b = y2 - k*x2;		
		return (k*x+b-y)<epsilon;
	}
}
