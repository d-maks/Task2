package by.darhyk.task1.toggle;

import java.util.HashMap;
import java.util.Map;

public class Toggle {

	private static Map<String, Boolean> toogles = new HashMap<>();
	private static boolean value;

	public static String getState(String className){
			if (toogles.containsKey(className)) {
				value = toogles.get(className);
				toogles.put(className, !value);
			} else {
				value = true;
				toogles.put(className, !value);
			}
			if(value==true){
				return "on";
			}
			else{
				return "off";
			}
	}

}
