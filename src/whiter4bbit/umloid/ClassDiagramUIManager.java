package whiter4bbit.umloid;

import whiter4bbit.umloid.activities.ClassPropertyActivity;
import whiter4bbit.umloid.classdiagram.Class;
import whiter4bbit.umloid.classdiagram.ClassDiagram;
import whiter4bbit.umloid.classdiagram.Connection;
import whiter4bbit.umloid.classdiagram.Diagram;
import whiter4bbit.umloid.views.ClassView;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.Menu.Item;
import android.widget.AbsoluteLayout;

public class ClassDiagramUIManager extends DiagramUIManager implements ClassCallBackListener{

	private ClassDiagram classDiagram;
	
	private enum State {ST_NONE, ST_CREATE_CLASS, ST_DELETE_CLASS, ST_CLASS_PROPERTIES, ST_CLASS_ASSOCIATION};
	
	private State state;
	
	private final int MENU_CREATE_CLASS = Menu.FIRST;
	
	private final int MENU_DELETE_CLASS = Menu.FIRST+1;
	
	private final int MENU_CLASS_PROPERTIES = Menu.FIRST+2;
	
	private final int MENU_CREATE_ASSOCIATION = Menu.FIRST+3;	
	
	private final int PROPERTIES = 1;
	
	private ClassView selectedClassView = null;
	
	private Class selectedClass1 = null;
	
	private Class selectedClass2 = null;
	
	private Connection currentConnection = null;
	
	public ClassDiagramUIManager(Diagram diagram) {
		super(diagram);		
		classDiagram = (ClassDiagram) diagram;
		UMLoidStorage.setDiagram(classDiagram);		
		state = State.ST_NONE;		
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
			Log.i(UMLoidHelper.UMLOID_TAG, "Diagram View: Action move ("+state+")");
			if( state == State.ST_NONE && selectedClassView!=null ){
				Class currentClass = selectedClassView.getCurrentClass();
				currentClass.setPos(x-50, y-25);
				selectedClassView.setCurrentClass(currentClass);
				selectedClassView.setLayoutParams(new AbsoluteLayout.LayoutParams(100,50, x-50,y-25));
				classDiagram.updateClass(currentClass);				
				Log.i(UMLoidHelper.UMLOID_TAG, "new pos for class "+currentClass.getName()+" x:"+currentClass.getX()+" Y:"+currentClass.getY());
				repaint();
			}			
			break;
		case MotionEvent.ACTION_DOWN:
			Log.i(UMLoidHelper.UMLOID_TAG, "Diagram View: Action down ("+state+")");
			if( state == State.ST_CREATE_CLASS ){
				createClass(x, y);
				state = State.ST_NONE;				
			}
			if( state == State.ST_NONE ){
				//TODO: don't toch				
				//TODO: think in near time about it....
				//TODO: don't touch before thinking!!!!				
				for(Connection connection:classDiagram.getConnections()){
					if(connection.isPointOn(x, y)){
						onConnectionSelect(connection);
					}
				}
			}
			break;
		case MotionEvent.ACTION_UP:
			Log.i(UMLoidHelper.UMLOID_TAG, "Diagram View: Action up ("+state+")");
			break;
		}
		return true;
	}
	
	private void onConnectionSelect(Connection connection){
		Log.i(UMLoidHelper.UMLOID_TAG, "Selected connection between "+connection.getClass1().getName()
				+" and "+connection.getClass2().getName());
		
		classDiagram.deselectAllConnections();
		connection.setSelected(true);
		classDiagram.updateConnection(connection);
		repaint();
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		menu.add(0, MENU_CREATE_CLASS, R.string.menu_create_class);
		menu.add(0, MENU_DELETE_CLASS, R.string.menu_delete_class);		
		menu.add(0, MENU_CLASS_PROPERTIES, R.string.menu_class_properties);
		menu.add(0, MENU_CREATE_ASSOCIATION, R.string.menu_create_association);
		return false;
	}
	
	@Override
	public boolean onOptionsItemSelected(Item item) {
		switch(item.getId()){
		case MENU_CREATE_CLASS:
			changeState(State.ST_CREATE_CLASS);			
			break;
		case MENU_DELETE_CLASS:
			changeState(State.ST_DELETE_CLASS);
			break;
		case MENU_CLASS_PROPERTIES:
			changeState(State.ST_CLASS_PROPERTIES);
			break;
		case MENU_CREATE_ASSOCIATION:
			selectedClass1 = null;
			selectedClass2 = null;
			changeState(State.ST_CLASS_ASSOCIATION);			
			break;
		}
		return false;
	}
	
	private void createClass(int x, int y){		
		Class newClass = new Class("class"+classDiagram.getClasses().size());		
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
		if( state == State.ST_CLASS_ASSOCIATION ){			
			if( selectedClass1 == null ){
				selectedClass1 = classView.getCurrentClass();
				selectedClass2 = null;
				Connection connection = new Connection("connection"+classDiagram.getConnections().size(), selectedClass1, null, classDiagram);
				currentConnection = connection;
				classDiagram.addConnection(connection);
			} else  
			if( selectedClass1 !=null && selectedClass2 == null ){
				selectedClass2 = classView.getCurrentClass();
				currentConnection.setClass2(selectedClass2);
				classDiagram.updateConnection(currentConnection);
				changeState(State.ST_NONE);
				repaint();
			}
		}
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, String data, Bundle extras) {
		super.onActivityResult(requestCode, resultCode, data, extras);		
	}
	
	@Override
	public void onDraw(Canvas canvas) {
		for(Connection connection : classDiagram.getConnections()){
			Paint connectionPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
			connectionPaint.setStrokeWidth(3);			
			connectionPaint.setARGB(255, 0, 255, 0);
			if( connection.isSelected() ){
				connectionPaint.setARGB(255, 255, 0, 0);
			}
			if( connection.getClass2() != null ){
				canvas.drawLine(connection.getX1(), 
								connection.getY1(), 
								connection.getX2(), 
								connection.getY2(),
								connectionPaint);			
				classDiagram.updateConnection(connection);
			}
						
		}
	}

}
