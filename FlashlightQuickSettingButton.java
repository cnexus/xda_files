package com.android.systemui.statusbar.policy.quicksetting;

import android.content.Context;
import android.content.Intent;

public class FlashlightQuickSettingButton
extends QuickSettingButton
implements QuickSettingButton.Listener
{
	private static final String TW_TAG = "STATUSBAR-FlashlightQuickSettingButton";
	private Context mContext;
	private int mFlashlight;

	public FlashlightQuickSettingButton(Context context)
	{
		super(context, null, false);
		this.initLayout(context.getResources().getIdentifier("quickpanel_flashlight_text", "string", "com.android.systemui"),
				context.getResources().getIdentifier("tw_quick_panel_icon_flashlight_on", "drawable", "com.android.systemui"),
				context.getResources().getIdentifier("tw_quick_panel_icon_flashlight_off", "drawable", "com.android.systemui"),
				0,
				0,
				0);
		this.mContext = context;
		setListener(this);
	}

	public void deinit() {}

	public void init(){
		setActivateStatus(2);
	}

	public void onClick(boolean paramBoolean){
		Intent localIntent = null;
		String bc = null;

		int activateStatus = 2;

		mFlashlight = mFlashlight + 1;
		
		mFlashlight = mFlashlight % 2;
		
		if(mFlashlight == 1){
			bc = "com.sec.samsung.torchwidget.torch_on_3";
			activateStatus = 1;
		}else{
			bc = "com.sec.samsung.torchwidget.torch_off";
		}

		setActivateStatus(activateStatus);

		localIntent = new Intent(bc);
		
		this.mContext.sendBroadcast(localIntent);
	}

	public void onLongClick() {}
}



/* Location:           C:\Users\Carlos\Downloads\jd-gui-0.3.6.windows\SystemUI.jar

 * Qualified Name:     com.android.systemui.statusbar.policy.quicksetting.FlashlightQuickSettingButton

 * JD-Core Version:    0.7.0.1

 */
