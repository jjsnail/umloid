package whiter4bbit.umloid.activity;

import whiter4bbit.umloid.R;
import whiter4bbit.umloid.UMLoidStorage;
import whiter4bbit.umloid.adapter.UMLoidSpinnerAdapter;
import whiter4bbit.umloid.structure.classdiagram.ClassDiagramMethod;
import whiter4bbit.umloid.tool.UMLoidHelper;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.TextView;

public class AddMethodActivity extends Activity{
	
	private TextView methodNameText = null;	
	private TextView methodTypeText = null;	
	private Button saveButton = null;		
	private Button cancelButton = null;	
	private Button attributesButton = null;	
	private ClassDiagramMethod classDiagramMethod = null;	
	private Integer action = null;		
	private CheckBox staticCheckBox = null;	
	private CheckBox abstractCheckBox = null;	
	private Spinner visibilitySpinner = null;	
	
	@Override	
	protected void onCreate(Bundle bundle) {			
		super.onCreate(bundle);		
		setContentView(R.layout.addmethod);
		
		methodNameText = (TextView) findViewById(R.id.method_name);
		methodTypeText = (TextView) findViewById(R.id.method_type);
		saveButton = (Button) findViewById(R.id.save_button);
		cancelButton = (Button) findViewById(R.id.cancel_button);
		attributesButton = (Button) findViewById(R.id.attributes_button);
		staticCheckBox = (CheckBox) findViewById(R.id.static_cb);
		abstractCheckBox = (CheckBox) findViewById(R.id.abstract_cb);		
		visibilitySpinner = (Spinner) findViewById(R.id.visibility_spinner);		
		
		saveButton.setOnClickListener(saveButtonClickListener);
		cancelButton.setOnClickListener(cancelButtonClickListener);
		attributesButton.setOnClickListener(attributesButtonClickListener);
		
		visibilitySpinner.setAdapter(new UMLoidSpinnerAdapter(this, new AdapterProperties(), "methodVisibilityProperties"));		
		
		Bundle extras = getIntent().getExtras();
		if( extras!=null ){
			this.action = (Integer)extras.get(UMLoidHelper.ACTION);			
			if( this.action == UMLoidHelper.ACTION_CREATE ){
				classDiagramMethod = new ClassDiagramMethod("method");				
			}
			if( this.action == UMLoidHelper.ACTION_MODIFY ){
				classDiagramMethod = UMLoidStorage.getClassDiagramMethod();				
			}
			showDiagramParameters();			
		}
		UMLoidStorage.setParameters(classDiagramMethod.getParameters());
	}

	private void showDiagramParameters(){
		methodNameText.setText(classDiagramMethod.getName());
		methodTypeText.setText(classDiagramMethod.getType());	
		staticCheckBox.setChecked(classDiagramMethod.isStaticMethod());
		abstractCheckBox.setChecked(classDiagramMethod.isAbstractMethod());
		
		visibilitySpinner.setSelection(
				AdapterProperties.getPositionOf(AdapterProperties.methodVisibilityProperties, classDiagramMethod.getVisibilityScope())
				);		
	}
	
	private OnClickListener saveButtonClickListener = new OnClickListener(){
		public void onClick(View view) {			
			String methodName = methodNameText.getText().toString();
			String methodType = methodTypeText.getText().toString();			
			classDiagramMethod.setName(methodName);
			classDiagramMethod.setType(methodType);
			classDiagramMethod.setStaticMethod(staticCheckBox.isChecked());
			classDiagramMethod.setAbstractMethod(abstractCheckBox.isChecked());
			classDiagramMethod.setVisibilityScope(visibilitySpinner.getSelectedItemId());
			classDiagramMethod.setParameters(UMLoidStorage.getParameters());			
			
			UMLoidStorage.setClassDiagramMethod(classDiagramMethod);
			Bundle bundle = new Bundle();
			bundle.putInt(UMLoidHelper.ACTION, action);
			
			Intent intent = new Intent();
			intent.putExtras(bundle);
			setResult(RESULT_OK, intent);			
			finish();			
		}
	};
	
	private OnClickListener cancelButtonClickListener = new OnClickListener(){
		public void onClick(View view) {
			setResult(RESULT_CANCELED);
			finish();
		}
	};
	
	private final Integer PARAMETERS_ACTIVITY = 2;
	
	private OnClickListener attributesButtonClickListener = new OnClickListener(){
		public void onClick(View view) {
			Intent intent = new Intent(view.getContext(), MethodParametersActivity.class);
			
			startActivityForResult(intent, PARAMETERS_ACTIVITY);			
		}
	};
	
}
