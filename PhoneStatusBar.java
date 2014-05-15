package com.android.systemui.statusbar.phone;

import com.android.systemui.SystemUI;
import com.android.systemui.statusbar.policy.quicksetting.QuickSettingPanel;

import android.content.Context;
import android.provider.Settings;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnHoverListener;

# For toggleable clock in pulldown

public class PhoneStatusBar extends SystemUI implements OnHoverListener{
	public PhoneStatusBar(Context context) {
		super(context);
	}
		
	private void doSomething(){
		this.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				if(checkToggleNotificationPanel())
					return;
			}

			private boolean checkToggleNotificationPanel(){
				View v = PhoneStatusBar.this.mNotificationPanelView;
				View qsPanel = PhoneStatusBar.this.findViewById(mContext.getResources().getIdentifier("quicksetting_scroller", "id", "com.android.systemui"));
				boolean res = Settings.System.getInt(mContext.getContentResolver(), "clock_toggle_eqs", 1) == 1;

				if(res){
					qsPanel.setVisibility(qsPanel.getVisibility() != View.GONE ? View.GONE : View.VISIBLE);
				}else
					return false;

				return true;
			}
		});
	}


	private NotificationPanelView mNotificationPanelView;
	private QuickSettingPanel mQSPanel;


	@Override
	public boolean onHover(View v, MotionEvent event) {


		return false;
	}
}
