package whiter4bbit.umloid.activity;

import whiter4bbit.umloid.R;
import whiter4bbit.umloid.UMLoidStorage;

public class MethodParametersActivity extends UMLoidListActivity {

	@Override
	public String getCreateItemText() { return "create"; }
	
	@Override
	public String getDeleteItemText() {	return "delete"; }
	
	@Override
	public String getEntityName() {	return "Parameter"; }
	
	@Override
	public String getEntityNameInStorage() { return "Parameter"; }
	
	@Override
	public Integer getMainListId() { return R.id.parameters_list; }
	
	@Override
	public Class getModifyActivityClass() {	return AddMethodParameterActivity.class; }
	
	@Override
	public Object getStorageClassInstance() { return new UMLoidStorage(); }
	
	@Override
	public Integer getViewLayoutId() { return R.layout.parameters; }
	
	@Override
	protected Integer getBackButtonId() { return R.id.method_parameters_back; }
}
