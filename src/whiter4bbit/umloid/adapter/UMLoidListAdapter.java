package whiter4bbit.umloid.adapter;

import java.util.List;

import whiter4bbit.umloid.tool.UMLoidHelper;
import whiter4bbit.umloid.tool.UMLoidReflectionTools;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * @author whiter4bbit
 * абстрактная можель для списка
 */
public class UMLoidListAdapter extends BaseAdapter{
	
	/* (non-Javadoc)
	 * @see android.widget.Adapter#getCount()
	 */
	public int getCount() {		
		return entitiesList.size();
	}
	
	public Object getItem(int position) {
		return position;
	}
	
	public long getItemId(int position) {
		Log.i(UMLoidHelper.UMLOID_TAG, "Trying to load entity:"+entitiesList.get(position));
		return (Long)UMLoidHelper.getClassInstanceProperty(entitiesList.get(position), "Id");
	}	
	
	public View getView(int position, View convertView, ViewGroup parent) {
		TextView textView = new TextView(context);		
		String name = (String)UMLoidHelper.getClassInstanceProperty(entitiesList.get(position), "Name");		
		textView.setText(name);		
		return textView;
	}
	
	private Context context = null;
	
	private Class clazz = null;
	
	private String entityName = null;
	
	private List entitiesList = null;
	
	/**
	 * создать абстрактную модель для списка
	 * @param <T> класс объектов 
	 * @param context контекс 
	 * @param instance экземпляр класса со списком
	 * @param entityName имя сущности в классе
	 * @throws UMLoidListAdapterException 
	 */
	public <T extends Object> UMLoidListAdapter(Context context, T instance , String entityName) throws UMLoidListAdapterException{
		this.context = context;		
		try{
			this.clazz = Class.forName(instance.getClass().getName());			
		}catch (ClassNotFoundException e) {
			throw new UMLoidListAdapterException("While loading class "+instance.getClass().getName()+" "+e.getMessage());						
		}
		String listGetter = "get"+UMLoidReflectionTools.makeMultiply(entityName);
		entitiesList = (List)UMLoidReflectionTools.invokeMethod(listGetter, new Object[]{}, clazz, instance);
		Log.i(UMLoidHelper.UMLOID_TAG, "from method "+listGetter+" loading entities list "+entitiesList);		
	}
		
}
