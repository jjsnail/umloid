package whiter4bbit.umloid.activity;

import whiter4bbit.umloid.R;
import whiter4bbit.umloid.UMLoidStorage;

/**
 * @author whiter4bbit
 * methods property activity
 */
public class MethodsActivity extends UMLoidListActivity{
	
	@Override
	public String getCreateItemText() { return "create"; }
	
	@Override
	public String getDeleteItemText() {	return "delete"; }
	
	@Override
	public String getEntityName() {	return "Method"; }
	
	@Override
	public String getEntityNameInStorage() { return "ClassDiagramMethod"; }
	
	@Override
	public Integer getMainListId() { return R.id.methods_list; }
	
	@Override
	public Class getModifyActivityClass() {	return AddMethodActivity.class; }
	
	@Override
	public Object getStorageClassInstance() { return new UMLoidStorage(); }
	
	@Override
	public Integer getViewLayoutId() { return R.layout.methods; }
	
	@Override
	protected Integer getBackButtonId() { return R.id.class_methods_back; }
	
}
