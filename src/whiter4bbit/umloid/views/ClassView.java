package whiter4bbit.umloid.views;

import whiter4bbit.umloid.ClassCallBackListener;
import whiter4bbit.umloid.R;
import whiter4bbit.umloid.classdiagram.Class;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.view.MotionEvent;
import android.view.View;

public class ClassView extends View{
	
	private Class currentClass;
	
	private ClassCallBackListener classCallBackListener;
	
	public Class getCurrentClass(){
		return currentClass;
	}
	
	public void setCurrentClass(Class currentClass) {
		this.currentClass = currentClass;
	}
	
	public ClassView(Context context, Class currentClass, ClassCallBackListener classCallBackListener) {
		super(context);
		this.currentClass = currentClass;
		this.classCallBackListener = classCallBackListener;		
		setBackground(R.drawable.classpic);
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {		
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			classCallBackListener.onClassClick(this);			
			break;
		case MotionEvent.ACTION_UP:
			break;
		case MotionEvent.ACTION_MOVE:
			break;
		}
		return super.onTouchEvent(event);
	}
	
	@Override
	protected void onDraw(Canvas canvas) {		
		Paint reclanglePaint = new Paint();
		reclanglePaint.setARGB(255, 0, 0, 0);
		reclanglePaint.setStyle(Style.STROKE);
		canvas.drawRect(0, 0, getWidth()-1, getHeight()-1, reclanglePaint);		
		canvas.drawLine(0, 22, getWidth()-1, 22, reclanglePaint);		
		Paint titlePaint = new Paint();
		titlePaint.setARGB(255, 0, 0, 0);
		canvas.drawText(currentClass.getName(), 5, 15, titlePaint);
		super.onDraw(canvas);
	}
}
