package com.android.systemui.statusbar.policy.quicksetting;
import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.database.ContentObserver;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.provider.Settings;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

# Modded for smali injection of custom code

public class QuickSettingButton
  extends LinearLayout
  implements View.OnClickListener, View.OnFocusChangeListener, View.OnLongClickListener
{
  private static final String DEFAULT_SCREENREADER_NAME = "com.google.android.marvin.talkback";
  private static final char ENABLED_SERVICES_SEPARATOR = ':';
  public static final int LINE_RESTRICTION = 12;
  public static final int MIDIUM_DENSITY = 160;
  public static final int STATUS_DIM = 3;
  public static final int STATUS_OFF = 2;
  public static final int STATUS_OFF2 = 5;
  public static final int STATUS_ON = 1;
  public static final int STATUS_ON2 = 4;
  private static final String TW_TAG = "STATUSBAR-QuickSettingButton";
  private static final TextUtils.SimpleStringSplitter sStringColonSplitter = new TextUtils.SimpleStringSplitter(':');
  private View buttonDivider;
  private int mActivateStatus;
  private AlertDialog mAlertDialog;
  private ImageView mBtnImage = null;
  private ImageView mBtnLED = null;
  protected TextView mBtnText = null;
  protected Context mContext;
  private boolean mDeviceProvisioned = false;
  private int mDimIconID;
  private BroadcastReceiver mIntentReceiver = new BroadcastReceiver()
  {
    public void onReceive(Context paramAnonymousContext, Intent paramAnonymousIntent)
    {
      if (("android.intent.action.CLOSE_SYSTEM_DIALOGS".equals(paramAnonymousIntent.getAction())) && (QuickSettingButton.this.mAlertDialog != null) && (QuickSettingButton.this.mAlertDialog.isShowing())) {
        QuickSettingButton.this.mAlertDialog.cancel();
      }
    }
  };
  protected boolean mIsProcessing;
  private Listener mListener;
  private int mOffIconID;
  private int mOffIconID2;
  private int mOnIconID;
  private int mOnIconID2;
  private String mText = null;
  private int mTextID;
  protected View mView;
  
  public QuickSettingButton(Context context, AttributeSet as, boolean bool){
	  super(context, as, 0);
  }
  
  public QuickSettingButton(Context paramContext, AttributeSet paramAttributeSet, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6)
  {
    super(paramContext, paramAttributeSet, 0);
    this.mContext = paramContext;
    View.inflate(paramContext, 2130968644, this);
    this.mBtnText = ((TextView)findViewById(2131296521));
    this.mBtnImage = ((ImageView)findViewById(2131296520));
    this.mBtnLED = ((ImageView)findViewById(2131296522));
    this.buttonDivider = findViewById(2131296519);
    this.mTextID = paramInt1;
    this.mOnIconID = paramInt2;
    this.mOffIconID = paramInt3;
    this.mDimIconID = paramInt4;
    this.mOnIconID2 = paramInt5;
    this.mOffIconID2 = paramInt6;
    setGravity(17);
    setFocusable(true);
    setBackgroundResource(2130838013);
    setBackgroundColor(Settings.System.getInt(this.mBtnImage.getContext().getContentResolver(), "toggle_bg_color", -15653326));
    regObserver(mContext.getContentResolver());
    
    buttonDivider.setVisibility(View.GONE);
  }
  
  protected void setKey(String name, int value){
	  Settings.System.putInt(mContext.getContentResolver(), name, value);
  }
  
  protected void initLayout(int textId, int onId, int offId, int dimId, int i5, int i6){
  }
  
  protected void checkStatusBarCollapse()
  {
    if (Settings.System.getInt(this.mContext.getContentResolver(), "qs_stat_collapse", 0) != 0) {
      statusBarCollapse();
    }
  }
  
  private void updateButton(){
	  if (Looper.getMainLooper().getThread() == Thread.currentThread()) {
		  this.invalidate();
		} else {
		  this.postInvalidate();
		}
  }
  
  
  private void regObserver(ContentResolver cs){
	  SettingsObserver ob = new SettingsObserver(this, new Handler());
	  cs.registerContentObserver(Settings.System.getUriFor("toggle_bg_color"), false, ob);
	  cs.registerContentObserver(Settings.System.getUriFor("toggle_color"), false, ob);
	  cs.registerContentObserver(Settings.System.getUriFor("toggle_bar_color"), false, ob);
	  cs.registerContentObserver(Settings.System.getUriFor("toggle_text_color"), false, ob);
	  cs.registerContentObserver(Settings.System.getUriFor("toggle_off_color"), false, ob);
	  cs.registerContentObserver(Settings.System.getUriFor("toggle_bar_off_color"), false, ob);
	  cs.registerContentObserver(Settings.System.getUriFor("notification_panel_active_app_list"), false, ob);
  }
  
  class SettingsObserver extends ContentObserver {
	QuickSettingButton qs;
	  
	public SettingsObserver(QuickSettingButton qs, Handler handler) {
		super(handler);
		this.qs = qs;
	}
	
	public void onChange(boolean selfChange){
		onChange(selfChange, null);
	}
	
	public void onChange(boolean selfChange, Uri uri){
		qs.updateButton();
	}
  }
}
