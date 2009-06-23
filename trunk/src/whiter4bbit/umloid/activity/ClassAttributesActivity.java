package whiter4bbit.umloid.activity;

import whiter4bbit.umloid.R;
import whiter4bbit.umloid.UMLoidStorage;

public class ClassAttributesActivity extends UMLoidListActivity {	
	
	@Override
	public String getCreateItemText() { return "create"; }
	
	@Override
	public String getDeleteItemText() {	return "delete"; }
	
	@Override
	public String getEntityName() {	return "Attribute"; }
	
	@Override
	public String getEntityNameInStorage() { return "ClassDiagramAttribute"; }
	
	@Override
	public Integer getMainListId() { return R.id.attributes_list; }
	
	@Override
	public Class getModifyActivityClass() {	return AddAttributeActivity.class; }
	
	@Override
	public Object getStorageClassInstance() { return new UMLoidStorage(); }
	
	@Override
	public Integer getViewLayoutId() { return R.layout.attributes; }
	
	@Override
	protected Integer getBackButtonId() { return R.id.class_attributes_back; }
}

