package com.android.systemui.statusbar.policy.quicksetting;

import android.content.Context;
import android.content.Intent;

public class PhoneQuickSettingButton
  extends QuickSettingButton
  implements QuickSettingButton.Listener
{
  private static final String TW_TAG = "STATUSBAR-PhoneQuickSettingButton";
  private Context mContext;
  private boolean mPhone;
  
  public PhoneQuickSettingButton(Context paramContext)
  {
    super(paramContext, null, 2131493255, 2130839371, 2130839370, 0, 0, 0);
    this.mContext = paramContext;
    setListener(this);
  }
  
  public void deinit() {}
  
  public void init()
  {
    this.mPhone = true;
    setActivateStatus(1);
  }
  
  public void onClick(boolean paramBoolean)
  {
    Intent intent = new Intent("android.intent.action.DIAL");
	statusBarCollapse();
	mContext.startActivity(intent);
  }
  
  public void onLongClick() {}
}
