package whiter4bbit.umloid.activity;

import java.io.IOException;
import java.io.InputStreamReader;

import com.thoughtworks.xstream.XStream;

import whiter4bbit.umloid.ClassDiagramUIManager;
import whiter4bbit.umloid.DiagramUIManager;
import whiter4bbit.umloid.structure.classdiagram.ClassDiagram;
import whiter4bbit.umloid.structure.classdiagram.Diagram;
import whiter4bbit.umloid.tool.UMLoidHelper;
import whiter4bbit.umloid.view.DiagramView;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
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
		super.onCreate(icicle);
		Bundle extras = getIntent().getExtras();	
		if(extras!=null){
			String diagramName = extras.getString(UMLoidHelper.DIAGRAM_NAME_KEY);
			int state = extras.getInt(UMLoidHelper.DIAGRAM_STATE);			
			int diagramType = extras.getInt(UMLoidHelper.DIAGRAM_TYPE_KEY);
			String diagramFileName = extras.getString(UMLoidHelper.DIAGRAM_FILE);
			switch (state) {			
			case UMLoidHelper.DIAGRAM_STATE_CREATE:			
				createDiagram(diagramType ,diagramName);				
				break;
			case UMLoidHelper.DIAGRAM_STATE_LOAD:
				loadDiagram(diagramType, diagramFileName);
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
	
	private void loadDiagram(int diagramType, String fileName){
		if(diagramType == UMLoidHelper.CLASS_DIAGRAM){
			currentDiagram = null;
			try{				
				InputStreamReader fis = new InputStreamReader(openFileInput(fileName));
				XStream xStream = new XStream();
				currentDiagram = (ClassDiagram)xStream.fromXML(fis);
				fis.close();
			}catch(IOException ioException){
				Log.e(UMLoidHelper.UMLOID_TAG, "While loading "+fileName+" "+ioException);
			}
			if(currentDiagram!=null){
				diagramUIManager = new ClassDiagramUIManager(currentDiagram);
				createDiagramView();
			}
		}
	}
	
	private void createDiagramView(){
		diagramView = new DiagramView(this, diagramUIManager);		
		addContentView(diagramView, new ViewGroup.LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));
		diagramUIManager.onCreate(this);
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode,
			String data, Bundle extras) {
		diagramUIManager.onActivityResult(requestCode, resultCode, data, extras);		
		super.onActivityResult(requestCode, resultCode, data, extras);
	}
}
