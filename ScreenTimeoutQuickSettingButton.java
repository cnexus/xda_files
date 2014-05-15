package com.android.systemui.statusbar.policy.quicksetting;

import android.content.Context;
import android.database.ContentObserver;
import android.net.Uri;
import android.os.Handler;
import android.provider.Settings;
import android.widget.Toast;

public class ScreenTimeoutQuickSettingButton extends QuickSettingButton implements QuickSettingButton.Listener{

	private static int[] TIMEOUTS = {15000, 30000, 60000, 120000};
	public static String TAG = "434e65787573";
	private int currentTimeout;

	public ScreenTimeoutQuickSettingButton(Context context) {
		super(context,
				null,
				context.getResources().getIdentifier("quickpanel_timeout_text", "string", "com.android.systemui"),
				context.getResources().getIdentifier("tw_quick_panel_icon_timeout_0", "drawable", "com.android.systemui"),
				context.getResources().getIdentifier("tw_quick_panel_icon_timeout_1", "drawable", "com.android.systemui"),
				0,
				context.getResources().getIdentifier("tw_quick_panel_icon_timeout_2", "drawable", "com.android.systemui"),
				context.getResources().getIdentifier("tw_quick_panel_icon_timeout_3", "drawable", "com.android.systemui"));
		this.mContext = context;
		setListener(this);
		currentTimeout = 0;

		ContentObserver obs = new ContentObserver(new Handler()){
			public void onChange(boolean selfChange) {
				setCurrentTimeout();
			}

			public void onChange(boolean selfChange, Uri uri) {
				onChange(selfChange);
			}
		};

		mContext.getContentResolver().registerContentObserver(Settings.System.getUriFor(Settings.System.SCREEN_OFF_TIMEOUT), false, obs);
	}
	
	private void setCurrentTimeout(){
		int cur = Settings.System.getInt(mContext.getContentResolver(), Settings.System.SCREEN_OFF_TIMEOUT, 0);
		for (int i = 0; i < TIMEOUTS.length; i++){
			if (TIMEOUTS[i] == cur){
				setTimeout(TIMEOUTS[i], false);
				currentTimeout = i;
				return;
			}
		}
		
		currentTimeout = 0;
	}

	public void deinit() {
		setActivateStatus(1);
	}


	public void init() {
		setActivateStatus(1);
		setCurrentTimeout();
	}

	private static String processTimeout(int tm){
		String timeout = "";

		if (tm / 60000 > 0){ // Minutes
			timeout = tm / 60000 + " min";
		}else{ // Seconds
			timeout = tm / 1000 + " sec";
		}

		return timeout;
	}

	private void setTimeout(int newTime, boolean showToast){
		String tmText = processTimeout(newTime);

		if(showToast)
			Toast.makeText(mContext, "Screen timeout set to " + tmText, Toast.LENGTH_SHORT).show();

		Settings.System.putInt(mContext.getContentResolver(), Settings.System.SCREEN_OFF_TIMEOUT, newTime);

		setIcon(mContext.getResources().getIdentifier("quickpanel_timeout_text", "string", "com.android.systemui"),
				mContext.getResources().getIdentifier("tw_quick_panel_icon_timeout_" + currentTimeout, "drawable", "com.android.systemui"),
				0,
				0,
				0,
				0);

		setActivateStatus(1);
	}

	public void onClick(boolean paramBoolean) {
		currentTimeout = (currentTimeout + 1) % TIMEOUTS.length;
		int newTime = TIMEOUTS[currentTimeout];
		setTimeout(newTime, true);
	}

	public void onLongClick() {
		callActivity("android.settings.DISPLAY_SETTINGS");
	}

}
