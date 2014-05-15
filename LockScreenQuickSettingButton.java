package com.android.systemui.statusbar.policy.quicksetting;
import android.content.Context;
import android.provider.Settings;
import android.widget.Toast;

public class LockScreenQuickSettingButton extends QuickSettingButton implements QuickSettingButton.Listener{
	public static final String DISABLE_LOCKSCREEN_KEY = "disable_lock";
	public static String TAG = "434e65787573";

	private boolean mActive;

	public LockScreenQuickSettingButton(Context context) {
		super(context,
				null,
				context.getResources().getIdentifier("quickpanel_lockscreen_text", "string", "com.android.systemui"),
				context.getResources().getIdentifier("tw_quick_panel_icon_lock_screen_on", "drawable", "com.android.systemui"),
				context.getResources().getIdentifier("tw_quick_panel_icon_lock_screen_off", "drawable", "com.android.systemui"),
				0x0, 0x0, 0x0);

		setListener(this);
		mActive = false;
	}

	public void deinit() {
		setActivateStatus(2);
	}

	public void init() {
		mActive = Settings.System.getInt(mContext.getContentResolver(), DISABLE_LOCKSCREEN_KEY, 0) == 1;
		setActivateStatus(mActive ? 1 : 2);
	}

	public void updateLockScreenDisabled() {
		Settings.System.putInt(mContext.getContentResolver(), DISABLE_LOCKSCREEN_KEY, mActive ? 1 : 0);
	}

	public void onClick(boolean paramBoolean) {
		mActive = !mActive;

		String text = "Lockscreen ";

		if(mActive){
			setActivateStatus(1);
			text += "disabled";
		}else{
			setActivateStatus(2);
			text += "enabled";
		}
		
		
		Toast.makeText(mContext, text, Toast.LENGTH_SHORT).show();
	}

	public void onLongClick() {
		callActivity("android.settings.DISPLAY_SETTINGS");
	}
}
