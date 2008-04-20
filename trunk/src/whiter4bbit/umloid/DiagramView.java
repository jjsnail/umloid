package whiter4bbit.umloid;

import android.content.Context;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.widget.AbsoluteLayout;

public class DiagramView extends AbsoluteLayout{	

	private DiagramUIManager diagramActivityManager;
	
	public DiagramView(Context context, DiagramUIManager diagramActivityManager) {
		super(context);
		this.diagramActivityManager = diagramActivityManager;
		setBackground(this.diagramActivityManager.getBackground());		
		diagramActivityManager.setView(this);
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);		
		diagramActivityManager.onDraw(canvas);
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {						
		return diagramActivityManager.onTouchEvent(event);
	}
}
