package android1.widget;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import android.content.Context;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.NinePatch;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.NinePatchDrawable;
import android.provider.Settings;
import android.view.View;
import android.widget.TextView;

# Dummy file used for smali injection

public class Toast {
	private Context mContext;
	private static Typeface KK_FONT = Typeface.createFromFile("/system/moar/KK_RobotoCondensed-Regular.ttf");
	private static Drawable KK_BG;

	private void setToastType(View view){
		TextView toastView = (TextView) view.findViewById(android.R.id.message);

		boolean useKKToast = Settings.System.getInt(mContext.getContentResolver(), "", 0) != 0;

		if(useKKToast){
			view.setBackground(KK_BG);
			toastView.setTypeface(KK_FONT);
		}
	}

	private static void setKitkatToast(Context context){
		String app = "com.android.providers.settings";
		Resources res;
		try {
			res = context.getPackageManager().getResourcesForApplication(app);
			KK_BG = res.getDrawable(res.getIdentifier("toast_frame_holo", "drawable", app));
		} catch (NameNotFoundException e) {
			e.printStackTrace();
		}

		String color = "FF00FF00";

		Color.parseColor("#" + color);
	}

	private static String toHexString(String colorInt){
		String hex = Integer.toHexString(Integer.parseInt(colorInt));
		
		return "#" + hex;
	}
}
