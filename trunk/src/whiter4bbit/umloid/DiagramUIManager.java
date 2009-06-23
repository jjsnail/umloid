package whiter4bbit.umloid;

import whiter4bbit.umloid.structure.classdiagram.Diagram;
import android.app.Activity;
import android.graphics.Canvas;
import android.os.Bundle;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.view.Menu.Item;

/**
 * @author whiter4bbit
 *
 */
public abstract class DiagramUIManager {
	
	/**
	 * обработка создания меню
	 * @param menu меню
	 * @return результат создания меню
	 */
	public abstract boolean onCreateOptionsMenu(Menu menu);
	
	/**
	 * обработка выбора пункта меню
	 * @param item пункт меню
	 * @return результат создания меню
	 */
	public abstract boolean onOptionsItemSelected(Item item);
	
	/**
	 * обработка сообщения при отрисовке окна
	 * @param canvas
	 */
	public abstract void onDraw(Canvas canvas);
	
	/**
	 * обработка прикосновения к экрану
	 * @param event описатель события
	 * @return результат обработки
	 */
	public abstract boolean onTouchEvent(MotionEvent event);
	
	/**
	 * текущая диаграмма
	 */
	protected Diagram diagram;
	
	/**
	 * текущий вид
	 */
	private ViewGroup view;
	
	/**
	 * обработка возврата из другой активности
	 * @param requestCode запршеный код
	 * @param resultCode полученый код выполнения
	 * @param data название хранилища данных
	 * @param extras данные полученый из активности
	 */
	public void onActivityResult(int requestCode, int resultCode,
			String data, Bundle extras){		
	}
	
	/**
	 * создание новго экземпляра 
	 * @param diagram диаграмма
	 */
	public DiagramUIManager(Diagram diagram) {
		this.diagram = diagram;
	}
	/**
	 * обработка создания диаграммы
	 * @param activity активность 
	 */
	public void onCreate(Activity activity){
		
	}	
	
	/**
	 * установить диаграмму
	 * @param diagram диаграма
	 */
	public void setDiagram(Diagram diagram){
		this.diagram = diagram;
	}
	
	/**
	 * установить цвет фона
	 * @return цвет фона
	 */
	public int getBackground(){
		return R.drawable.white;
	}	

	/**
	 * установить вид
	 * @param view вид
	 */
	public void setView(ViewGroup view){
		this.view = view;		
	}
	
	/**
	 * вернуть вид
	 * @return вид
	 */
	public ViewGroup getView() {
		return view;
	}
	
	/**
	 * вернуть активность
	 * @return активность
	 */
	public Activity getActivity(){
		if( view.getContext() instanceof Activity ){
			return (Activity) view.getContext();			
		}
		return null;
	}
	
	/**
	 * перерисовка окна
	 */
	void repaint(){
		if(this.view!=null)
			this.view.invalidate();		
	}
	
}
