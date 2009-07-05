package whiter4bbit.umloid.activity;

import whiter4bbit.umloid.adapter.UMLoidListAdapter;
import whiter4bbit.umloid.adapter.UMLoidListAdapterException;
import whiter4bbit.umloid.tool.UMLoidHelper;
import whiter4bbit.umloid.tool.UMLoidReflectionTools;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

/**
 * @author whiter4bbit
 * активность, отображающая список, содержащийся в экземпляре класса
 * с определенными аттрибутами
 */
abstract public class UMLoidListActivity extends Activity{
	
	private ListView mainListView = null;
	
	/**
	 * получить идентификатор списка на форме
	 * @return идентификатор
	 */
	abstract public Integer getMainListId();
	
	/**
	 * получить идентификатор раскладки
	 * @return идентфикатор
	 */
	abstract public Integer getViewLayoutId();
	
	/**
	 * получить экземпляр класса, в котором хранятся объекты
	 * @return экземпляр класса
	 */
	abstract public Object getStorageClassInstance();
	
	/**
	 * получить имя сущности для отображения
	 * @return имя сущности
	 */
	abstract public String getEntityName();
		
	abstract public String getCreateItemText();
	
	abstract public String getDeleteItemText();
	
	abstract public String getEntityNameInStorage();
	
	protected Integer getBackButtonId(){
		return null;
	}
	
	@Override
	protected void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		
		setContentView(getViewLayoutId());
		mainListView = (ListView) findViewById(getMainListId());
		mainListView.setOnItemClickListener(mainListItemClicked);
		
		if(getBackButtonId()!=null){
			Button backButton = (Button) findViewById(getBackButtonId());		
			backButton.setOnClickListener(new OnClickListener(){
				public void onClick(View view) {					
					finish();
				}
			});
		}
		refreshList();
	}
	
	abstract public Class getModifyActivityClass();
	
	private final int CREATE_ACTIVITY = 0;
	
	private final int MODIFY_ACTIVITY = 1;
	
	private OnItemClickListener mainListItemClicked = new OnItemClickListener(){		
		public void onItemClick(AdapterView parent, View v, int position, long id){			
			Object selectedEntity = UMLoidReflectionTools.invokeMethod("get"+getEntityName(), 
																	   new Object[]{mainListView.getSelectedItemId()}, 
																	   getStorageClassInstance().getClass() , 
																	   getStorageClassInstance());
			if( selectedEntity !=null ){
				UMLoidReflectionTools.invokeMethod("set"+getEntityNameInStorage(), 
												   new Object[]{selectedEntity}, 
												   getStorageClassInstance().getClass(),
												   getStorageClassInstance());
				Intent intent = new Intent(v.getContext(), getModifyActivityClass());
				intent.putExtra(UMLoidHelper.ACTION, UMLoidHelper.ACTION_MODIFY);
				
				
				startActivityForResult(intent, MODIFY_ACTIVITY);				
			}
		}
	};

	
	private void refreshList(){		
		UMLoidListAdapter umListAdapter = null;
		try{
			umListAdapter = new UMLoidListAdapter(this, getStorageClassInstance(), getEntityName());			
		}catch(UMLoidListAdapterException ex){
			Log.e(UMLoidHelper.UMLOID_TAG, ex.getMessage());			
		}		
		mainListView.setAdapter( umListAdapter );
	}
	
	private static final int MENU_CREATE = Menu.FIRST+1;
	
	private static final int MENU_DELETE = Menu.FIRST+2;
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);
		menu.add(1, MENU_CREATE,0,  getCreateItemText());
		menu.add(1, MENU_DELETE,0,  getDeleteItemText());		
		return true;
	}
	
	@Override
	public boolean onMenuItemSelected(int featureId, MenuItem item) {
		super.onMenuItemSelected(featureId, item);
		switch (item.getItemId()) {
		case MENU_CREATE:
			Intent intent = new Intent(this, getModifyActivityClass());
			intent.putExtra(UMLoidHelper.ACTION, UMLoidHelper.ACTION_CREATE);
			
			startActivityForResult(intent, CREATE_ACTIVITY);	
			break;
		case MENU_DELETE:
			Object selectedEntity = UMLoidReflectionTools.invokeMethod("remove"+getEntityName(), 
					   													new Object[]{mainListView.getSelectedItemId()}, 
					   													getStorageClassInstance().getClass() , 
					   													getStorageClassInstance());
			refreshList();
			break;
		}
		return true;
	}
	
		
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		if( resultCode == RESULT_OK ){
			Bundle extras = data.getExtras();
			if( extras.getInt(UMLoidHelper.ACTION) == UMLoidHelper.ACTION_CREATE ){
				Object createdEntity = UMLoidReflectionTools.invokeMethod("get"+getEntityNameInStorage(), 
						   												  new Object[]{}, 
						   												  getStorageClassInstance().getClass(),
						   												  getStorageClassInstance());
				
				Log.i(UMLoidHelper.UMLOID_TAG, "Created entity "+createdEntity+" ");
				
				UMLoidReflectionTools.invokeMethod("add"+getEntityName(), 
						   						   new Object[]{createdEntity}, 
						   						   getStorageClassInstance().getClass(),
						   						   getStorageClassInstance());				
				refreshList();
			}			
			if( extras.getInt(UMLoidHelper.ACTION) == UMLoidHelper.ACTION_MODIFY ){
				Object updatedEntity = UMLoidReflectionTools.invokeMethod("get"+getEntityNameInStorage(), 
							  											  new Object[]{}, 
							  											  getStorageClassInstance().getClass(),
							  											  getStorageClassInstance());
				UMLoidReflectionTools.invokeMethod("update"+getEntityName(), 
													new Object[]{updatedEntity}, 
													getStorageClassInstance().getClass(),
													getStorageClassInstance());
				Log.i(UMLoidHelper.UMLOID_TAG, "Updated entity "+updatedEntity+" ");				
				refreshList();
			}			
		}
	}
	
}
