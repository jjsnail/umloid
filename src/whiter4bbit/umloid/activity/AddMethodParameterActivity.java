package whiter4bbit.umloid.activity;

import whiter4bbit.umloid.R;
import whiter4bbit.umloid.UMLoidStorage;
import whiter4bbit.umloid.structure.classdiagram.ClassDiagramMethodParameter;
import whiter4bbit.umloid.tool.UMLoidHelper;
import android.view.View;
import android.view.View.OnClickListener;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class AddMethodParameterActivity extends Activity {
	
	private ClassDiagramMethodParameter methodParameter = null;	
	private Integer action = null;	
	private EditText parameterNameEdit = null;	
	private EditText parameterTypeEdit = null;	
	private Button saveButton = null;	
	private Button cancelButton = null;
	
	@Override
	protected void onCreate(Bundle icicle) {	
		super.onCreate(icicle);
		
		setContentView(R.layout.addparameter);
		
		Bundle extras = getIntent().getExtras();		

		parameterNameEdit = (EditText) findViewById(R.id.add_parameter_parameter_name);		
		parameterTypeEdit = (EditText) findViewById(R.id.add_parameter_parameter_type);
		
		saveButton = (Button) findViewById(R.id.add_parameter_parameter_save);		
		saveButton.setOnClickListener(new OnClickListener(){
			public void onClick(View view) {
				methodParameter.setName(parameterNameEdit.getText().toString());
				methodParameter.setType(parameterTypeEdit.getText().toString());
				UMLoidStorage.setParameter(methodParameter);
				Bundle bundle = new Bundle();
				bundle.putInt(UMLoidHelper.ACTION, action);
				Intent intent = new Intent();
				intent.putExtras(bundle);
				setResult(RESULT_OK, intent);
				finish();
			}
		});
		
		cancelButton = (Button) findViewById(R.id.add_parameter_parameter_cancel);		
		cancelButton.setOnClickListener(new OnClickListener(){
			public void onClick(View view) {
				setResult(RESULT_CANCELED);
				finish();
			}
		});
		
		
		if(extras!=null){
			action = extras.getInt(UMLoidHelper.ACTION);
			if(action==UMLoidHelper.ACTION_CREATE){
				methodParameter = new ClassDiagramMethodParameter();
			}
			if(action==UMLoidHelper.ACTION_MODIFY){
				methodParameter = UMLoidStorage.getParameter();
			}
		}
		
		displayProperties();
	}
	
	private void displayProperties(){
		parameterNameEdit.setText(methodParameter.getName());
		parameterTypeEdit.setText(methodParameter.getType());
	}
	
	
}
