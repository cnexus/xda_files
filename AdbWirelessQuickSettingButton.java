package com.android.systemui.statusbar.policy.quicksetting;

import java.io.IOException;

import android.app.Service;
import android.content.Context;
import android.net.wifi.WifiManager;
import android.text.format.Formatter;

public class AdbWirelessQuickSettingButton extends QuickSettingButton
implements QuickSettingButton.Listener{
    private static final String TW_TAG = "STATUSBAR-AdbWirelessQuickSettingButton";
    private static final String ADB_PORT_PROP = "service.adb.tcp.port";
    private boolean mAdbWireless = false;

    public AdbWirelessQuickSettingButton(Context context) {
    	super(context, null, 2131493255, 2130839371, 2130839370, 0, 0, 0);
    	this.mBtnText.setText("ADB Wireless");
        this.mContext = context;
        setListener(this);
    }
    
    public void onClick(boolean paramBoolean) {
    	WifiManager wm = (WifiManager) mContext.getSystemService(Context.WIFI_SERVICE);
    	String ip = Formatter.formatIpAddress(wm.getConnectionInfo().getIpAddress());
    	
    	mAdbWireless = !mAdbWireless;
    	
    	if(mIsProcessing){
    		return;
    	}
    	if(mAdbWireless){
    		System.setProperty(ADB_PORT_PROP, "5555");
    	}else{
    		System.clearProperty(ADB_PORT_PROP);
    	}
    	
    	Runtime r = Runtime.getRuntime();
    	try{
        
        setActivateStatus(3);
        
        Process p = r.exec("stop adbd".split(" "));
        p.waitFor();
        p = r.exec("start adbd".split(" "));
        p.waitFor();
    	} catch(IOException e){
    	} catch (InterruptedException e) {
		}
        
        setActivateStatus(mAdbWireless ? 1 : 2);
        
        this.mBtnText.setText(mAdbWireless ? ip : "ADB Wireless");
        
        checkStatusBarCollapse();
    }

	@Override
	public void deinit() {
		setActivateStatus(2);
	}

	@Override
	public void init() {
		setActivateStatus(2);
	}

	@Override
	public void onLongClick() {
		
	}
	
	
}
