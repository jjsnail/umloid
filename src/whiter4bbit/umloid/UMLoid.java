package whiter4bbit.umloid;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.Menu.Item;

/**
 * @author whiter4bbit
 * 
 * UMLoid a main activity
 */
public class UMLoid extends Activity {
	
	public static final int MENU_CREATE_DIAGRAM = Menu.FIRST;
	
	public static final int MENU_LOAD_DIAGRAM = Menu.FIRST+1;
	
	private final int CREATE_DIAGRAM = 0;
	
	private final int LOAD_DIAGRAM = 1;
	
	private final int SHOW_DIAGRAM_ACTIVITY = 2;
	
    /** Called when the activity is first created. */
	@Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.main);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		menu.add(0, MENU_CREATE_DIAGRAM, R.string.menu_create_diagram);
		menu.add(0, MENU_LOAD_DIAGRAM, R.string.menu_load_diagram);		
		return super.onCreateOptionsMenu(menu);
	}
	
	@Override
	public boolean onOptionsItemSelected(Item item) {
		// TODO Auto-generated method stub
		switch(item.getId()){
		case MENU_CREATE_DIAGRAM:
			createDiagram();
			break;
		case MENU_LOAD_DIAGRAM:
			break;
		}
		return super.onOptionsItemSelected(item);
	}
	
	private void createDiagram(){
		Intent i = new Intent(this, CreateActivity.class);
		startSubActivity(i, CREATE_DIAGRAM);		
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode,
			String data, Bundle extras) {
		super.onActivityResult(requestCode, resultCode, data, extras);
		
		switch (requestCode) {
		case CREATE_DIAGRAM:			
			int diagramType = extras.getInt(UMLoidHelper.DIAGRAM_TYPE_KEY);
			String diagramName = extras.getString(UMLoidHelper.DIAGRAM_NAME_KEY);
			
			Intent i = new Intent(this, DiagramActivity.class);							
			i.putExtra(UMLoidHelper.DIAGRAM_NAME_KEY, diagramName);
			i.putExtra(UMLoidHelper.DIAGRAM_TYPE_KEY, diagramType);
			i.putExtra(UMLoidHelper.DIAGRAM_STATE, UMLoidHelper.DIAGRAM_STATE_CREATE);
			
			startSubActivity(i, SHOW_DIAGRAM_ACTIVITY);			
			break;
		case LOAD_DIAGRAM:
			break;
		}
	}		
}
