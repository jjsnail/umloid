package whiter4bbit.umloid;

import whiter4bbit.umloid.classdiagram.ClassDiagram;
import whiter4bbit.umloid.classdiagram.Diagram;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.ViewGroup;
import android.view.Menu.Item;
import android.view.ViewGroup.LayoutParams;

/**
 * @author whiter4bbit
 * 
 */
public class DiagramActivity extends Activity{
	
	private Diagram currentDiagram;
	
	private DiagramUIManager diagramUIManager;
	
	private DiagramView diagramView;
	
	@Override
	protected void onCreate(Bundle icicle) {
		// TODO Auto-generated method stub
		super.onCreate(icicle);
		Bundle extras = getIntent().getExtras();	
		if(extras!=null){
			String diagramName = extras.getString(UMLoidHelper.DIAGRAM_NAME_KEY);
			int state = extras.getInt(UMLoidHelper.DIAGRAM_STATE);			
			int diagramType = extras.getInt(UMLoidHelper.DIAGRAM_TYPE_KEY);			
			switch (state) {			
			case UMLoidHelper.DIAGRAM_STATE_CREATE:			
				createDiagram(diagramType ,diagramName);				
				break;
			case UMLoidHelper.DIAGRAM_STATE_LOAD:				
				break;
			}
		}		
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		diagramUIManager.onCreateOptionsMenu(menu);
		return super.onCreateOptionsMenu(menu);
	}
	@Override
	public boolean onOptionsItemSelected(Item item) {
		diagramUIManager.onOptionsItemSelected(item);
		return super.onOptionsItemSelected(item);
	}
	
	private void createDiagram(int diagramType, String diagramName){
		if(diagramType == UMLoidHelper.CLASS_DIAGRAM){			
			currentDiagram = new ClassDiagram( diagramName );
			diagramUIManager = new ClassDiagramUIManager(currentDiagram);
			createDiagramView();
		}
	}	
	
	private void createDiagramView(){
		diagramView = new DiagramView(this, diagramUIManager);		
		addContentView(diagramView, new ViewGroup.LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode,
			String data, Bundle extras) {
		diagramUIManager.onActivityResult(requestCode, resultCode, data, extras);		
		super.onActivityResult(requestCode, resultCode, data, extras);
	}
}
