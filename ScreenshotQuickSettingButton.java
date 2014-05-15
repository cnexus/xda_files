package com.android.systemui.statusbar.policy.quicksetting;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.content.Context;
import android.text.format.Time;
import android1.widget.Toast;

public class ScreenshotQuickSettingButton extends QuickSettingButton
implements QuickSettingButton.Listener{

	private static Time current = new Time();
	
	public ScreenshotQuickSettingButton(Context context) {
		super(context,
				null,
				context.getResources().getIdentifier("quick_panel_screenshot_text", "string", "com.android.systemui"), context.getResources().getIdentifier("quick_panel_screenshot_text", "string", "com.android.systemui"),
				context.getResources().getIdentifier("tw_quick_panel_icon_screenshot", "drawable", "com.android.systemui"),
				context.getResources().getIdentifier("tw_quick_panel_icon_screenshot", "drawable", "com.android.systemui"),
				0x0, 0x0);
		
		
	}
	
	private String getTime(){
		SimpleDateFormat fmt = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss", mContext.getResources().getConfiguration().locale);
		Date date = new Date();
		String dateString = fmt.format(date);
		
		return dateString;
	}

	public void deinit() {
		setActivateStatus(1);
	}

	public void init() {
		setActivateStatus(1);
	}

	public void onClick(boolean paramBoolean) {
		setActivateStatus(1);
		takeScreenshot();
	}
	
	private void takeScreenshot(){
		statusBarCollapse();
		
		String out = "/sdcard/Pictures/Screenshots";
		
		File screenDir = new File(out);
		screenDir.mkdirs();
		
		String file = out + "/" + getTime() + ".png";
		
		String[] cmd = {"/system/bin/screencap", "-p", file};
		
		//Toast.makeText(mContext, "Saving screenshot...", Toast.LENGTH_LONG).show();
		
		try {
			Thread.sleep(700);
			Runtime.getRuntime().exec(cmd).waitFor();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		//Toast.makeText(mContext, "Screenshot saved", Toast.LENGTH_SHORT).show();
	}

	public void onLongClick() {
		return;
	}

}
