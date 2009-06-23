package whiter4bbit.umloid;

import whiter4bbit.umloid.activity.CreateActivity;
import whiter4bbit.umloid.activity.DiagramActivity;
import whiter4bbit.umloid.tool.UMLoidHelper;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.Menu.Item;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

/**
 * @author whiter4bbit
 * 
 * UMLoid a main activity
 */
public class UMLoid extends Activity {
	
	public static final int MENU_CREATE_DIAGRAM = Menu.FIRST;	
	
	private final int CREATE_DIAGRAM = 0;	
	
	private final int SHOW_DIAGRAM_ACTIVITY = 1;
		
	
	public ListView diagramsListView = null;
	
    /** Called when the activity is first created. */
	@Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.main);
        diagramsListView = (ListView) findViewById(R.id.files_list);                
        diagramsListView.setAdapter(new DiagramsListViewAdapter(this, fileList()));
        diagramsListView.setOnItemClickListener(onFileClickListener);
	}
	
	private OnItemClickListener onFileClickListener = new OnItemClickListener(){
		public void onItemClick(AdapterView parent, View v, int position, long id){
			String file = (String)diagramsListView.getSelectedItem();
			if( file!=null ){			
				
				Intent intent = new Intent(v.getContext(), DiagramActivity.class);
				intent.putExtra(UMLoidHelper.DIAGRAM_TYPE_KEY, UMLoidHelper.CLASS_DIAGRAM);
				intent.putExtra(UMLoidHelper.DIAGRAM_STATE, UMLoidHelper.DIAGRAM_STATE_LOAD);
				intent.putExtra(UMLoidHelper.DIAGRAM_FILE, file);
				
				startSubActivity(intent, SHOW_DIAGRAM_ACTIVITY);
			}
		}
	};
	
	private class DiagramsListViewAdapter extends BaseAdapter{
		
		public int getCount() {
			return files!=null ? files.length : 0;
		}
		
		public Object getItem(int position) {			
			return files[position];
		}
		
		public long getItemId(int position) {
			return position;
		}
		
		public View getView(int position, View convertView, ViewGroup parent) {
			TextView textView = new TextView(context);
			textView.setText(files[position]);
			return textView;
		}		
		
		private String[] files = null;
		
		private Context context = null;
		
		public DiagramsListViewAdapter(Context context, String[] files) {
			this.files = files;
			this.context = context;
		}
	};
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);
		menu.add(0, MENU_CREATE_DIAGRAM, R.string.menu_create_diagram);		
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(Item item) {
		super.onOptionsItemSelected(item);
		switch(item.getId()){
		case MENU_CREATE_DIAGRAM:
			createDiagram();
			break;
		}
		return true;		
	}
	
	private void createDiagram(){
		Intent i = new Intent(this, CreateActivity.class);
		startSubActivity(i, CREATE_DIAGRAM);		
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, String data, Bundle extras) {
		super.onActivityResult(requestCode, resultCode, data, extras);
		
		switch (requestCode) {
		case CREATE_DIAGRAM:			
			int diagramType = extras.getInt(UMLoidHelper.DIAGRAM_TYPE_KEY);
			String diagramName = extras.getString(UMLoidHelper.DIAGRAM_NAME_KEY);
			
			Intent i = new Intent(this, DiagramActivity.class);							
			i.putExtra(UMLoidHelper.DIAGRAM_NAME_KEY, diagramName);
			i.putExtra(UMLoidHelper.DIAGRAM_TYPE_KEY, diagramType);
			i.putExtra(UMLoidHelper.DIAGRAM_STATE, UMLoidHelper.DIAGRAM_STATE_CREATE);
			
			startSubActivity(i, SHOW_DIAGRAM_ACTIVITY);			
			break;

		}
	}		
}
