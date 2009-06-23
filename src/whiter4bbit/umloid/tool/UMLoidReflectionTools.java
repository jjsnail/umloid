package whiter4bbit.umloid.tool;

import java.lang.reflect.Method;


import android.util.Log;

public class UMLoidReflectionTools {
	
	public static Object invokeMethod(String name, Object[] params, Class clazz, Object instance){
		Object result = null;
		try{
			Method method = null;
			for(Method meth : clazz.getMethods()){
				if(meth.getName().equals(name)){					
					if(params.length>0 && meth.getParameterTypes().length >0 && 
							meth.getParameterTypes()[0]==params[0].getClass()){
						method = meth;
					}
					if(params.length==0){
						method = meth;
					}
				}
			}
			if(method!=null){
				Log.i(UMLoidHelper.UMLOID_TAG, "Trying to load method "+name+" with "+method.getParameterTypes().length+" parameters");
				result = method.invoke(instance, params);
			}else{
				Log.e(UMLoidHelper.UMLOID_TAG, "Method "+name+" in "+clazz+" not found");				
			}
		}catch(Exception e){
			Log.e(UMLoidHelper.UMLOID_TAG, "There was an exception while loading method "+name+" from class "+clazz.getName()+":"+e.getMessage());
		}
		return result;		
	}
	
	public static String makeMultiply(String name){		
		return name + "s";		
	}
	
}
