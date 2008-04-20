package whiter4bbit.umloid;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

/**
 * @author whiter4bbit
 *
 */
public class CreateActivity extends Activity{

	private ListView diagramTypeList;
	
	private Button createDiagramButton;
	
	private EditText diagramNameText;
	
	private String[] diagramTypeItems = new String[]{"Class diagram","Activity diagram"};
	
	@Override
	protected void onCreate(Bundle icicle) {
		// TODO Auto-generated method stub
		super.onCreate(icicle);	
		setContentView(R.layout.create);
		diagramTypeList = (ListView) findViewById(R.id.diagram_type);
		diagramTypeList.setAdapter(new ArrayAdapter<String>(this, 
															android.R.layout.simple_expandable_list_item_1, 
															diagramTypeItems));
		createDiagramButton = (Button) findViewById(R.id.create_diagram);
		createDiagramButton.setOnClickListener(createDiagramListener);
		diagramNameText = (EditText) findViewById(R.id.diagram_name);
	}
	
	private OnClickListener createDiagramListener = new OnClickListener(){
		public void onClick(View view) {
			// TODO Auto-generated method stub
			int selectedTypePos = diagramTypeList.getSelectedItemPosition();
			int selectedDiagramType = UMLoidHelper.CLASS_DIAGRAM;  
			if(selectedTypePos==1){
				selectedDiagramType = UMLoidHelper.ACTIVITY_DIAGRAM;
			}
			Bundle bundle = new Bundle();
			bundle.putString(UMLoidHelper.DIAGRAM_NAME_KEY, diagramNameText.getText().toString());
			bundle.putInt(UMLoidHelper.DIAGRAM_TYPE_KEY, selectedDiagramType);			
			setResult(RESULT_OK, null, bundle);			
			finish();
		}
	};
}
