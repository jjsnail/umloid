package whiter4bbit.umloid.structure.classdiagram;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Hashtable;
import java.util.List;

import org.apache.commons.httpclient.methods.GetMethod;

/**
 * @author whiter4bbit
 *
 */
public class ClassDiagram extends Diagram{
		
	private Hashtable<Integer, ClassDiagramClass> classes = new Hashtable<Integer, ClassDiagramClass>();
	
	private Hashtable<Integer, ClassDiagramConnection> connections = new Hashtable<Integer, ClassDiagramConnection>();
	
	public ClassDiagram(){
		
	}
	
	public ClassDiagram(String name){
		this.name = name;		
	}

	/**
	 * добавление нового класса
	 * @param newClass новый класс 
	 */
	public void addClass(ClassDiagramClass newClass){
		Integer id = classes.size()+1;
		newClass.setId(id);		
		this.classes.put(id, newClass);		
	}
	
	/**
	 * удалить выделение со всех связей
	 */
	private void deselectAllConnections(){
		for(ClassDiagramConnection connection : connections.values() ){
			connection.setSelected(false);
		}
	}
	
	/**
	 * добавить новую свзяь
	 * @param connection связь
	 */
	public void addConnection(ClassDiagramConnection connection){
		Integer id = connections.size()+1;
		connection.setId(id);
		this.connections.put(id, connection);
	}
	
	/**
	 * обновить связь
	 * @param connection свзяь
	 */
	public void updateConnection(ClassDiagramConnection connection){
		this.connections.put(connection.getId(), connection);
	}
	
	/**
	 * вернуть коллекцию со связями
	 * @return коллекция со связями
	 */
	public Collection<ClassDiagramConnection> getConnections() {		
		return connections.values();
	}
	
	/**
	 * удалить связь
	 * @param connection связь
	 */
	public void removeConnection(ClassDiagramConnection connection){
		connections.remove(connection.getId());
	}
	
	/**
	 * удалить свзяи, содержащие заданый класс 
	 * @param removeableClass класс, который учавствует в связи
	 */
	public void removeConnectionsWithClass(ClassDiagramClass removeableClass){
		List<Integer> keys = new ArrayList<Integer>();
		for(ClassDiagramConnection connection: getConnections()){
			if(connection.getClass1().getId() == removeableClass.getId()
					|| connection.getClass2().getId() == removeableClass.getId()){				
				keys.add(connection.getId());
			}
		}
		for( Integer key : keys ){
			removeConnection(connections.get(key));			
		}
	}
	
	/**
	 * обновить класс 
	 * @param updClass класс
	 */
	public void updateClass(ClassDiagramClass updClass){
		this.classes.put(updClass.getId(), updClass);
	}
	
	/**
	 * вернуть класс по id
	 * @param id идентификатор
	 * @return класс
	 */
	public ClassDiagramClass getClass(Integer id){
		return this.classes.get(id);		
	}
	
	/**
	 * удалить класс
	 * @param toRemove класс
	 */
	public void removeClass(ClassDiagramClass toRemove){		
		this.classes.remove(toRemove.getId());		
	}
	
	/**
	 * вернуть карту с классами (ключ - идентификатор, значение - связь)
	 * @return карта со связями 
	 */
	public Hashtable<Integer, ClassDiagramClass> getClasses() {
		return classes;
	}
	
	/**
	 * выбрать связь
	 * @param connection связь
	 */
	public void setSelectedConnection(ClassDiagramConnection connection) {
		deselectAllConnections();
		connection.setSelected(true);
	}
	
	@Override
	public String toString() {
		return "Class Diagram [name="+name+", classes="+getClasses().size()+"]";
	}
	
}
