package whiter4bbit.umloid.classdiagram;

import java.io.Serializable;

import whiter4bbit.umloid.UMLoidHelper;

import android.util.Log;

//TODO: remove funckin' constants from this class and put it into static final members of one other
//TODO: do prev todo for each other class
public class Connection implements Serializable{

	private String role1;
	
	private String role2;
	
	private Class class1;
	
	private Class class2;
	
	private int multiplier1;
	
	private int multiplier2;
	
	private ClassDiagram classDiagram;
	
	private String name;
	
	private Integer id;	
	
	public Connection(String name, Class class1, Class class2, ClassDiagram classDiagram) {
		this.name = name;
		this.classDiagram = classDiagram;
		this.class1 = class1;
		this.class2 = class2;			
	}
	
	public Integer getX1() {
		return classDiagram.getClass(class1.getId()).getX()+50;
	}
	
	public Integer getX2() {
		return classDiagram.getClass(class2.getId()).getX()+50;
	}
	
	public Integer getY1() {
		return classDiagram.getClass(class1.getId()).getY()+25;
	}
	
	public Integer getY2() {
		return classDiagram.getClass(class2.getId()).getY()+25;
	}
	
	public void setX1(Integer x1) {
		this.x1 = x1;
	}
	
	public void setX2(Integer x2) {
		this.x2 = x2;
	}
	
	public void setY1(Integer y1) {
		this.y1 = y1;
	}
	
	public void setY2(Integer y2) {
		this.y2 = y2;
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
		boolean result = isPointOn(x, y, getX1(), getY1(), getX2(), getY2());		
		return result;
	}
	
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
}
