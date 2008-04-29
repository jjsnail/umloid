package whiter4bbit.umloid.activities;

import whiter4bbit.umloid.R;
import whiter4bbit.umloid.UMLoidHelper;
import whiter4bbit.umloid.UMLoidStorage;
import whiter4bbit.umloid.classdiagram.Class;
import whiter4bbit.umloid.classdiagram.ClassDiagram;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class ClassPropertyActivity extends Activity{			
	
	private Class currentClass;
	
	private ClassDiagram classDiagram;
	
	private EditText nameEdit;
	
	private Button saveButton;
	
	private Button methodsButton;
	
	@Override
	protected void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		setContentView(R.layout.properties);
		nameEdit = (EditText) findViewById(R.id.diagramname);
		saveButton = (Button) findViewById(R.id.save_button);
		saveButton.setOnClickListener(onSaveListener);
		methodsButton = (Button) findViewById(R.id.methods_btn);
		methodsButton.setOnClickListener(onMethodsListener);
		Bundle bundle = getIntent().getExtras();
		if( bundle!=null ){
			Integer classId = bundle.getInt(UMLoidHelper.CLASS_ID_KEY);
			classDiagram = (ClassDiagram) UMLoidStorage.getDiagram();
			currentClass = classDiagram.getClass(classId);
			nameEdit.setText(currentClass.getName());
			Log.i(UMLoidHelper.UMLOID_TAG, "Loaded properties activity for class "+currentClass.getName());			
		}
	}
	
	private OnClickListener onMethodsListener = new OnClickListener(){
		public void onClick(View arg0) {
			
		};
	};
	
	
	private OnClickListener onSaveListener = new OnClickListener(){
		public void onClick(View arg0) {
			currentClass.setName(nameEdit.getText().toString());			
			classDiagram.updateClass(currentClass);			
			setResult(RESULT_OK);
			finish();			
		}
	};	
	
}
