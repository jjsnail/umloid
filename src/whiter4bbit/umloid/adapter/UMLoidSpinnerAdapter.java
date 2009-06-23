package whiter4bbit.umloid.adapter;

import java.lang.reflect.Field;
import java.util.HashMap;

import whiter4bbit.umloid.tool.UMLoidHelper;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


/**
 * @author whiter4bbit
 * адаптер для выпадающего списка
 */
public class UMLoidSpinnerAdapter extends BaseAdapter {
  
	public int getCount() {
		return propertiesMap.size();
	}

	public long getItemId(int position) {
		return (Long)propertiesMap.keySet().toArray()[position];
	}

	public Object getItem(int position) {
		return position;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		TextView textView = new TextView(context);
		Long id = getItemId(position);		
		textView.setText(propertiesMap.get(id));			
		return textView;
	}
	
	private Context context = null;
	
	private HashMap<Long, String> propertiesMap = null;
	
	/**
	 * создание нового экземпляра
	 * @param context контекст
	 * @param instance экземпляр класса со списком 
	 * @param propertyName имя свойства
	 */
	public UMLoidSpinnerAdapter(Context context, Object instance, String propertyName) {		
		this.context = context;				
		Class clazz = null;		
		try{
			clazz = Class.forName(instance.getClass().getName());
		}catch (ClassNotFoundException ex) {
			Log.e(UMLoidHelper.UMLOID_TAG, ex.getMessage());			
		}
		
		try{
			Field field = clazz.getField(propertyName);
			propertiesMap = (HashMap<Long, String>)field.get(instance);			
		}catch (IllegalAccessException illegalAccessExc) {
			Log.e(UMLoidHelper.UMLOID_TAG, illegalAccessExc.getMessage());
		}
		catch (NoSuchFieldException e) {
			Log.e(UMLoidHelper.UMLOID_TAG, e.getMessage());
		}
		
	}

}
