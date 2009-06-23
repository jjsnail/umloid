package whiter4bbit.umloid.activity;

import whiter4bbit.umloid.R;
import whiter4bbit.umloid.UMLoidStorage;
import whiter4bbit.umloid.adapter.UMLoidSpinnerAdapter;
import whiter4bbit.umloid.structure.classdiagram.ClassDiagramAttribute;
import whiter4bbit.umloid.tool.UMLoidHelper;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class AddAttributeActivity extends Activity {
	
	private EditText attributeNameText = null;	
	private EditText attributeTypeText = null;	
	private Button saveButton = null;	
	private Button cancelButton = null;	
	private Spinner visibilityScope = null;	
	private Integer action = null;	
	private ClassDiagramAttribute currentAttribute = null;
	
	@Override
	protected void onCreate(Bundle icicle) {		
		super.onCreate(icicle);
		setContentView(R.layout.addattribute);
		
		attributeNameText = (EditText) findViewById(R.id.add_attribute_attribute_name);
		attributeTypeText = (EditText) findViewById(R.id.add_attribute_attribute_type);
		saveButton = (Button) findViewById(R.id.add_attribute_attribute_save);
		cancelButton = (Button) findViewById(R.id.add_attribute_attribute_cancel);		
		visibilityScope = (Spinner) findViewById(R.id.add_attribute_visibility_scope);
		
		saveButton.setOnClickListener(saveButtonClicked);
		cancelButton.setOnClickListener(cancelButtonClicked);
		
		visibilityScope.setAdapter(new UMLoidSpinnerAdapter(this, new AdapterProperties(), "attributeVisibilityProperties"));			
		
		Bundle bundle = getIntent().getExtras();
		
		if(bundle!=null){
			action = bundle.getInt(UMLoidHelper.ACTION);					
			if( action == UMLoidHelper.ACTION_CREATE ){
				currentAttribute = new ClassDiagramAttribute();
			}			
			if( action == UMLoidHelper.ACTION_MODIFY ){
				currentAttribute = UMLoidStorage.getClassDiagramAttribute();
			}
			displayValues();
		}
	}
	
	private void displayValues(){
		attributeNameText.setText(currentAttribute.getName());
		attributeTypeText.setText(currentAttribute.getType());		
		visibilityScope.setSelection(
				AdapterProperties.getPositionOf(AdapterProperties.attributeVisibilityProperties, currentAttribute.getVisibilityScope())
				);		
	}
	
	private OnClickListener cancelButtonClicked = new OnClickListener(){
		public void onClick(View view) {
			setResult(RESULT_CANCELED);
			finish();			
		}
	};
	
	private OnClickListener saveButtonClicked = new OnClickListener(){
		public void onClick(View view) {
			currentAttribute.setName(attributeNameText.getText().toString());
			currentAttribute.setType(attributeTypeText.getText().toString());			
			currentAttribute.setVisibilityScope(visibilityScope.getSelectedItemId());
			UMLoidStorage.setClassDiagramAttribute(currentAttribute);
			Bundle extras = new Bundle();
			extras.putInt(UMLoidHelper.ACTION, action);			
			setResult(RESULT_OK, "Attribute", extras);
			finish();
		}
	};	
}
