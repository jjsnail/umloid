package whiter4bbit.umloid.activity;

import whiter4bbit.umloid.R;
import whiter4bbit.umloid.UMLoidStorage;
import whiter4bbit.umloid.adapter.UMLoidSpinnerAdapter;
import whiter4bbit.umloid.structure.classdiagram.ClassDiagramClass;
import whiter4bbit.umloid.structure.classdiagram.ClassDiagramConnection;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class ConnectionPropertiesActivity extends Activity {
	
	private Spinner connectionType = null;	
	private Button saveButton = null;	
	private Button cancelButton = null;	
	private EditText connectionNameText = null;	
	private EditText classOneRoleText = null;	
	private EditText classTwoRoleText = null;	
	private EditText classOneMultiplicityText = null;	
	private EditText classTwoMultiplicityText = null;	
	private ClassDiagramConnection connection = null;
	
	@Override
	protected void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		
		setContentView(R.layout.connection);

		connectionType = (Spinner) findViewById(R.id.connection_type_spinner);
		connectionType.setAdapter(new UMLoidSpinnerAdapter(this, new AdapterProperties(), "connectionTypeProperties"));
		
		saveButton = (Button) findViewById(R.id.connection_save);
		saveButton.setOnClickListener(onSaveButtonClick);
		cancelButton = (Button) findViewById(R.id.connection_cancel);
		cancelButton.setOnClickListener(new OnClickListener(){
			public void onClick(View view) {
				setResult(RESULT_CANCELED);
				finish();
			}
		});
		
		connectionNameText = (EditText) findViewById(R.id.connection_name);		
		classOneRoleText = (EditText) findViewById(R.id.class1_role);
		classTwoRoleText = (EditText) findViewById(R.id.class2_role);
		classOneMultiplicityText = (EditText) findViewById(R.id.class1_multiplicity);
		classTwoMultiplicityText = (EditText) findViewById(R.id.class2_multiplicity);
		
		connection = UMLoidStorage.getConnection();
		
		displayProperties();
	}
	
	private void displayProperties(){		
		ClassDiagramClass class1 = connection.getClass1();
		ClassDiagramClass class2 = connection.getClass2();
		
		TextView classOneRoleTextView = (TextView) findViewById(R.id.class_one_role_text);
		classOneRoleTextView.setText("Class "+class1.getName()+" role:");
		TextView classTwoRoleTextView = (TextView) findViewById(R.id.class_two_role_text);
		classTwoRoleTextView.setText("Class "+class2.getName()+" role:");
		
		TextView classOneMultiplicityTextView = (TextView) findViewById(R.id.class_one_multiplicity_text);
		classOneMultiplicityTextView.setText("Class "+class1.getName()+" multiplicity:");
		TextView classTwoMultiplicityTextView = (TextView) findViewById(R.id.class_two_multiplicity_text);
		classTwoMultiplicityTextView.setText("Class "+class2.getName()+" multiplicity:");
		
		connectionNameText.setText(connection.getName());
		classOneRoleText.setText(connection.getRole1());
		classTwoRoleText.setText(connection.getRole2());
		classOneMultiplicityText.setText(connection.getMultiplier1());
		classTwoMultiplicityText.setText(connection.getMultiplier2());
		
		connectionType.setSelection(AdapterProperties.getPositionOf(AdapterProperties.connectionTypeProperties, connection.getType()));		
	}
	
	private OnClickListener onSaveButtonClick = new OnClickListener(){
		public void onClick(View view) {
			connection.setName(connectionNameText.getText().toString());
			connection.setRole1(classOneRoleText.getText().toString());
			connection.setRole2(classTwoRoleText.getText().toString());
			connection.setMultiplier1(classOneMultiplicityText.getText().toString());
			connection.setMultiplier2(classTwoMultiplicityText.getText().toString());
			connection.setType(connectionType.getSelectedItemId());
			UMLoidStorage.setConnection(connection);
			setResult(RESULT_OK);
			finish();
		}
	};
}
