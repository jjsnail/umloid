package whiter4bbit.umloid;

import whiter4bbit.umloid.classdiagram.Diagram;
import android.app.Activity;
import android.graphics.Canvas;
import android.os.Bundle;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.view.Menu.Item;

public abstract class DiagramUIManager {
	
	public abstract boolean onCreateOptionsMenu(Menu menu);
	
	public abstract boolean onOptionsItemSelected(Item item);
	
	public abstract void onDraw(Canvas canvas);
	
	public abstract boolean onTouchEvent(MotionEvent event);
	
	protected Diagram diagram;
	
	private ViewGroup view;
	
	protected void onActivityResult(int requestCode, int resultCode,
			String data, Bundle extras){		
	}
	
	public DiagramUIManager(Diagram diagram) {
		this.diagram = diagram;
	}
	
	public void onCreate(Activity activity){
		
	}	
	
	public void setDiagram(Diagram diagram){
		this.diagram = diagram;
	}
	
	/**
	 * default resource is an white image png
	 * if you want se custom background  redifine this method 
	 * @return resource for background
	 */
	protected int getBackground(){
		return R.drawable.white;
	}	

	public void setView(ViewGroup view){
		this.view = view;		
	}
	
	public ViewGroup getView() {
		return view;
	}
	
	public Activity getActivity(){
		if( view.getContext() instanceof Activity ){
			return (Activity) view.getContext();			
		}
		return null;
	}
	
	void repaint(){
		if(this.view!=null)
			this.view.invalidate();		
	}
	
}
