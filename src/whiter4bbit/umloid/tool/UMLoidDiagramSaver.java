package whiter4bbit.umloid.tool;

import java.io.FileOutputStream;
import java.io.IOException;
import com.thoughtworks.xstream.XStream;
import android.content.Context;
import android.util.Log;
import whiter4bbit.umloid.structure.classdiagram.ClassDiagram;

public class UMLoidDiagramSaver {
	
	/**
	 * сохранить диаграмму классов
	 * @param classDiagram диаграмма классов
	 * @param context контекст
	 */
	public UMLoidDiagramSaver(ClassDiagram classDiagram, Context context){	
		XStream xStream = new XStream();		
		Log.i(UMLoidHelper.UMLOID_TAG, xStream.toXML(classDiagram));		
		try{
			FileOutputStream foStream = context.openFileOutput(classDiagram.getName()+".umloid", Context.MODE_WORLD_WRITEABLE);
			xStream.toXML(classDiagram, foStream);
			foStream.close();
		}catch (IOException e) {
			Log.e(UMLoidHelper.UMLOID_TAG, "Error while saving :"+e.getMessage());
		}

	}
}
