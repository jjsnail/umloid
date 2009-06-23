package whiter4bbit.umloid.activity;

import whiter4bbit.umloid.R;
import whiter4bbit.umloid.UMLoidStorage;
import whiter4bbit.umloid.adapter.UMLoidSpinnerAdapter;
import whiter4bbit.umloid.structure.classdiagram.ClassDiagram;
import whiter4bbit.umloid.structure.classdiagram.ClassDiagramClass;
import whiter4bbit.umloid.tool.UMLoidHelper;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class ClassPropertyActivity extends Activity{			
	
	private ClassDiagramClass currentClass = null;	
	private ClassDiagram classDiagram = null;	
	private EditText nameEdit = null;	
	private Button saveButton = null;	
	private Button methodsButton = null;	
	private Button attributesButton = null;	
	private Button cancelButton = null;	
	private Spinner classTypeSpinner = null;	
	private Spinner classVisibilityScopeSpinner = null;
	
	@Override
	protected void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		setContentView(R.layout.properties);
		nameEdit = (EditText) findViewById(R.id.diagramname);
		saveButton = (Button) findViewById(R.id.save_button);
		saveButton.setOnClickListener(onSaveListener);
		
		methodsButton = (Button) findViewById(R.id.methods_btn);
		methodsButton.setOnClickListener(onMethodsListener);
		
		cancelButton = (Button) findViewById(R.id.cancel_button);
		cancelButton.setOnClickListener(new OnClickListener(){
			public void onClick(View view) {
				setResult(RESULT_CANCELED);
				finish();
			}
		});
		
		attributesButton = (Button) findViewById(R.id.attributes_btn);
		attributesButton.setOnClickListener(onAttributesListner);
		
		classTypeSpinner = (Spinner) findViewById(R.id.class_type_spinner);		
		classTypeSpinner.setAdapter(new UMLoidSpinnerAdapter(this, new AdapterProperties(), "classTypeProperties"));
		
		classVisibilityScopeSpinner = (Spinner) findViewById(R.id.class_visibility_scope_spinner);		
		classVisibilityScopeSpinner.setAdapter(new UMLoidSpinnerAdapter(this, new AdapterProperties(), "classVisibilityProperties"));
				
		Bundle bundle = getIntent().getExtras();
		if( bundle!=null ){
			Integer classId = bundle.getInt(UMLoidHelper.CLASS_ID_KEY);
			classDiagram = (ClassDiagram) UMLoidStorage.getDiagram();
			currentClass = classDiagram.getClass(classId);
			
			UMLoidStorage.setAttributes( currentClass.getAttributes() );
			UMLoidStorage.setMethods( currentClass.getMethods() );
			displayProperties();			
		}
	}
	
	private void displayProperties(){
		nameEdit.setText(currentClass.getName());
		classTypeSpinner.setSelection( AdapterProperties.getPositionOf(AdapterProperties.classTypeProperties, currentClass.getType()) );
		classVisibilityScopeSpinner.setSelection( 
				AdapterProperties.getPositionOf(AdapterProperties.classVisibilityProperties, currentClass.getVisibilityScope()) 
				);
	}
	
	private final int METHODS_PROPERTIES_ACTIVITY = 1;
	
	private final int ATTRIBUTES_PROPERTIES_ACTIVITY = 2;
	
	private OnClickListener onAttributesListner = new OnClickListener(){
		public void onClick(View view) {
			Intent intent = new Intent(view.getContext(), ClassAttributesActivity.class);						
			startSubActivity(intent, ATTRIBUTES_PROPERTIES_ACTIVITY);			
		}
	};
	
	private OnClickListener onMethodsListener = new OnClickListener(){
		public void onClick(View view) {
			Intent intent = new Intent(view.getContext(), MethodsActivity.class);			
			startSubActivity(intent, METHODS_PROPERTIES_ACTIVITY);			
		};
	};
	
	private OnClickListener onSaveListener = new OnClickListener(){
		public void onClick(View view) {			
			setResult(RESULT_OK);
			currentClass.setName(nameEdit.getText().toString());
			currentClass.setAttributes(UMLoidStorage.getAttributes());
			currentClass.setMethods(UMLoidStorage.getMethods());
			currentClass.setType(classTypeSpinner.getSelectedItemId());
			currentClass.setVisibilityScope(classVisibilityScopeSpinner.getSelectedItemId());			
			finish();			
		}
	};
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode,
			String data, Bundle extras) {
		super.onActivityResult(requestCode, resultCode, data, extras);		
	}
}
