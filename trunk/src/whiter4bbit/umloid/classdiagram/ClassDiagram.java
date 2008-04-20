package whiter4bbit.umloid.classdiagram;

import java.util.Collection;
import java.util.Hashtable;

/**
 * @author whiter4bbit
 *
 */
public class ClassDiagram extends Diagram{
		
	private Hashtable<Integer, Class> classes = new Hashtable<Integer, Class>();
	
	private Hashtable<Integer, Connection> connections = new Hashtable<Integer, Connection>();
	
	public ClassDiagram(String name){
		this.name = name;		
	}

	public void addClass(Class newClass){
		Integer id = classes.size()+1;
		newClass.setId(id);		
		this.classes.put(id, newClass);		
	}
	
	public void deselectAllConnections(){
		for(Connection connection : connections.values() ){
			connection.setSelected(false);
		}
	}
	
	public void addConnection(Connection connection){
		Integer id = connections.size()+1;
		connection.setId(id);
		this.connections.put(id, connection);
	}
	
	public void updateConnection(Connection connection){
		this.connections.put(connection.getId(), connection);
	}
	
	public Collection<Connection> getConnections() {		
		return connections.values();
	}
	
	public void updateClass(Class updClass){
		this.classes.put(updClass.getId(), updClass);
	}
	
	public Class getClass(Integer id){
		return this.classes.get(id);		
	}
	
	public void removeClass(Class toRemove){		
		this.classes.remove(toRemove.getId());		
	}
	
	public Hashtable<Integer, Class> getClasses() {
		return classes;
	}
	
}
