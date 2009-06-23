package whiter4bbit.umloid;

import whiter4bbit.umloid.activity.ClassPropertyActivity;
import whiter4bbit.umloid.activity.ConnectionPropertiesActivity;
import whiter4bbit.umloid.structure.classdiagram.ClassDiagram;
import whiter4bbit.umloid.structure.classdiagram.ClassDiagramClass;
import whiter4bbit.umloid.structure.classdiagram.ClassDiagramConnection;
import whiter4bbit.umloid.structure.classdiagram.Diagram;
import whiter4bbit.umloid.tool.UMLoidDiagramSaver;
import whiter4bbit.umloid.tool.UMLoidHelper;
import whiter4bbit.umloid.view.ClassCallBackListener;
import whiter4bbit.umloid.view.ClassView;
import whiter4bbit.umloid.view.ConnectionView;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Canvas;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.SubMenu;
import android.view.Menu.Item;
import android.widget.AbsoluteLayout;

public class ClassDiagramUIManager extends DiagramUIManager implements ClassCallBackListener{
	
	private enum State {ST_NONE, ST_CREATE_CLASS, ST_DELETE_CLASS, ST_CLASS_PROPERTIES, ST_CLASS_CONNECTION, ST_CONNECTION_PROPERTIES};
	private ClassDiagram classDiagram;
	private State state;
	private final int PROPERTIES = 1;
	private ClassView selectedClassView = null;
	private ClassDiagramClass selectedClass1 = null;
	private ClassDiagramClass selectedClass2 = null;
	private ClassDiagramConnection currentConnection = null;
	
	public ClassDiagramUIManager(Diagram diagram) {
		super(diagram);		
		classDiagram = (ClassDiagram) diagram;
		UMLoidStorage.setDiagram(classDiagram);		
		state = State.ST_NONE;
	}
	
	@Override
	public void onCreate(Activity activity) {
		super.onCreate(activity);

		for(ClassDiagramClass diagramClass : classDiagram.getClasses().values()){
			ClassView classView = new ClassView(getView().getContext(), diagramClass, this);		
			getView().addView( classView , new AbsoluteLayout.LayoutParams(100,50, diagramClass.getX(),diagramClass.getY()));
		}
		
		repaint();
	}
	
	private void changeState(State newState){
		this.state = newState;
		Log.i(UMLoidHelper.UMLOID_TAG,"Changed state:"+newState);		
	}
		
	@Override
	public boolean onTouchEvent(MotionEvent event) {		
		int x = (int)event.getX();
		int y = (int)event.getY();
		switch(event.getAction()){
		case MotionEvent.ACTION_MOVE:
			if( state == State.ST_NONE && selectedClassView!=null ){
				selectedClassView.getCurrentClass().setPos(x-50, y-50);
				selectedClassView.setLayoutParams(new AbsoluteLayout.LayoutParams(100,50, x-50,y-25));				
				repaint();
			}			
			break;
		case MotionEvent.ACTION_DOWN:
			if( state == State.ST_CREATE_CLASS ){
				createClass(x, y);
				state = State.ST_NONE;				
			}
			if( state == State.ST_NONE || state == State.ST_CONNECTION_PROPERTIES){				
				for(ClassDiagramConnection connection:classDiagram.getConnections()){
					if(connection.isPointOn(x, y)){
						onConnectionSelect(connection);
					}
				}
			}
			break;
		}
		return true;
	}
	
	private final Integer CONNECTION_PROPERTIES_ACTIVITY = 1;
	
	private void onConnectionSelect(ClassDiagramConnection connection){
		Log.i(UMLoidHelper.UMLOID_TAG, "Selected connection between "+connection.getClass1().getName()+" and "+connection.getClass2().getName());			
		classDiagram.setSelectedConnection(connection);
		if( state == State.ST_CONNECTION_PROPERTIES ){
			Intent intent = new Intent(getView().getContext(), ConnectionPropertiesActivity.class);
			UMLoidStorage.setConnection(connection);			
			getActivity().startSubActivity(intent, CONNECTION_PROPERTIES_ACTIVITY);
			changeState(State.ST_NONE);
		}
		repaint();
	}
	
	
	private final int MENU_CREATE_CLASS = Menu.FIRST;	
	private final int MENU_DELETE_CLASS = Menu.FIRST+1;	
	private final int MENU_CLASS_PROPERTIES = Menu.FIRST+2;	
	private final int MENU_CONNECTION_PROPERTIES = Menu.FIRST+4;	
	private final int MENU_CREATE_CONNECTION = Menu.FIRST+5;
	private final int MENU_CREATE_ASSOCIATION = Menu.FIRST+6;
	private final int MENU_CREATE_COMPOSITION = Menu.FIRST+7;
	private final int MENU_CREATE_AGGREGATION = Menu.FIRST+8;
	private final int MENU_CREATE_GENERALIZATION = Menu.FIRST+9;
	private final int MENU_CREATE_DEPENDENCY = Menu.FIRST+10;
	private final int MENU_SAVE_DIAGRAM= Menu.FIRST+11;
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		
		menu.add(0, MENU_SAVE_DIAGRAM, R.string.menu_save_diagram);
		menu.add(0, MENU_CREATE_CLASS, R.string.menu_create_class);
		menu.add(0, MENU_DELETE_CLASS, R.string.menu_delete_class);		
		menu.add(0, MENU_CLASS_PROPERTIES, R.string.menu_class_properties);	
		menu.add(0, MENU_CONNECTION_PROPERTIES, R.string.menu_connection_properties);
		
		SubMenu subMenu = menu.addSubMenu(0, MENU_CREATE_CONNECTION, R.string.menu_create_connection);
		
		subMenu.add(0, MENU_CREATE_ASSOCIATION, R.string.menu_create_association);
		subMenu.add(0, MENU_CREATE_COMPOSITION, R.string.menu_create_composition);
		subMenu.add(0, MENU_CREATE_AGGREGATION, R.string.menu_create_aggregation);
		subMenu.add(0, MENU_CREATE_GENERALIZATION, R.string.menu_create_generalization);
		subMenu.add(0, MENU_CREATE_DEPENDENCY, R.string.menu_create_dependency);
		return true;		
	}
	
	private long connectionType = ClassDiagramConnection.TYPE_AGGREGATION;
	
	@Override
	public boolean onOptionsItemSelected(Item item) {
		switch(item.getId()){
		case MENU_CREATE_CLASS:
			changeState(State.ST_CREATE_CLASS);			
			break;			
		case MENU_SAVE_DIAGRAM:
			new UMLoidDiagramSaver(classDiagram, getActivity());
			break;
		case MENU_DELETE_CLASS:
			changeState(State.ST_DELETE_CLASS);
			break;
		case MENU_CLASS_PROPERTIES:
			changeState(State.ST_CLASS_PROPERTIES);
			break;
		case MENU_CREATE_AGGREGATION:
			connectionType = ClassDiagramConnection.TYPE_AGGREGATION;
			selectedClass1 = null;
			selectedClass2 = null;
			changeState(State.ST_CLASS_CONNECTION);			
			break;
		case MENU_CREATE_COMPOSITION:
			connectionType = ClassDiagramConnection.TYPE_COMPOSITION;
			selectedClass1 = null;
			selectedClass2 = null;
			changeState(State.ST_CLASS_CONNECTION);			
			break;
		case MENU_CREATE_GENERALIZATION:
			connectionType = ClassDiagramConnection.TYPE_GENERALIZATION;
			selectedClass1 = null;
			selectedClass2 = null;
			changeState(State.ST_CLASS_CONNECTION);			
			break;
		case MENU_CREATE_DEPENDENCY:
			connectionType = ClassDiagramConnection.TYPE_DEPENDENCY;
			selectedClass1 = null;
			selectedClass2 = null;
			changeState(State.ST_CLASS_CONNECTION);			
			break;
		case MENU_CREATE_ASSOCIATION:
			selectedClass1 = null;
			selectedClass2 = null;
			changeState(State.ST_CLASS_CONNECTION);			
			break;
		case MENU_CONNECTION_PROPERTIES:			
			changeState(State.ST_CONNECTION_PROPERTIES);
			break;
		}
		return true;		
	}
	
	
	private void createClass(int x, int y){		
		ClassDiagramClass newClass = new ClassDiagramClass("class"+classDiagram.getClasses().size());		
		newClass.setPos(x, y);		
		classDiagram.addClass(newClass);
		ClassView classView = new ClassView(getView().getContext(), newClass, this);		
		getView().addView( classView , new AbsoluteLayout.LayoutParams(100,50, x,y));		
	}		
	
	public void onClassClick(ClassView classView) {
		if( state == State.ST_NONE ){
			selectedClassView = classView;
		}
		if( state == State.ST_DELETE_CLASS ){
			getView().removeView(classView);
			classDiagram.removeConnectionsWithClass(classView.getCurrentClass());			
			classDiagram.removeClass(classView.getCurrentClass());
			changeState(State.ST_NONE);
			repaint();
		}
		if( state == State.ST_CLASS_PROPERTIES){
			Intent i = new Intent(getView().getContext(), ClassPropertyActivity.class);			
			i.putExtra(UMLoidHelper.CLASS_ID_KEY, classView.getCurrentClass().getId());
			changeState(State.ST_NONE);
			getActivity().startSubActivity(i, PROPERTIES);			
		}
		if( state == State.ST_CLASS_CONNECTION ){			
			if( selectedClass1 == null ){
				selectedClass1 = classView.getCurrentClass();
				selectedClass2 = null;
				ClassDiagramConnection connection = new ClassDiagramConnection("connection"+classDiagram.getConnections().size(), selectedClass1, null, classDiagram);
				currentConnection = connection;
				connection.setType(connectionType);
				classDiagram.addConnection(connection);
			} else  
			if( selectedClass1 !=null && selectedClass2 == null ){
				selectedClass2 = classView.getCurrentClass();
				currentConnection.setClass2(selectedClass2);
				changeState(State.ST_NONE);
				repaint();
			}
		}
	}
	
	@Override
	public void onActivityResult(int requestCode, int resultCode, String data, Bundle extras) {
		super.onActivityResult(requestCode, resultCode, data, extras);
		if(resultCode==Activity.RESULT_OK){
			if(requestCode==CONNECTION_PROPERTIES_ACTIVITY){
				ClassDiagramConnection connection = UMLoidStorage.getConnection();
				currentConnection = connection;
			}
		}
	}
	
	@Override
	public void onDraw(Canvas canvas) {
		for(ClassDiagramConnection connection : classDiagram.getConnections()){			
			ConnectionView.drawConnection(connection, canvas);			
		}
	}

}
