package android.provider;

import android.content.Context;
import android.util.Log;

public class Tools {
	public static int getStringID(Context c, String s){
		String type = "string";
		int id = c.getResources().getIdentifier(s, type, c.getPackageName());
		logIdentifier(type, s, id);

		return id;

	}

	public static int getDrawableID(Context c, String s){
		String type = "drawable";
		int id = c.getResources().getIdentifier(s, type, c.getPackageName());
		logIdentifier(type, s, id);

		return id;


	}

	public static int getLayoutID(Context c, String s){
		String type = "layout";
		int id = c.getResources().getIdentifier(s, type, c.getPackageName());
		logIdentifier(type, s, id);

		return id;


	}

	public static void logIdentifier(String type, String name, int id){
		Log.d("TW-Toolbox", "Looking up [" + type + "] identifier for resource [" + name + "]. ID found: " + id);
	}
}
