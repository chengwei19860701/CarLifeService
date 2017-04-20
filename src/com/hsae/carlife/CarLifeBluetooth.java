package com.hsae.carlife;

import android.content.Context;
import android.util.Log;
 

public class CarLifeBluetooth {
	
	private static final String TAG = "CarLifeBT";
	private Context mContext;
	private BluetoothListener mBluetoothListener;
	private static String mHuBlueToothAddress;
	private static String mHuBlueToothName;
	private String mMdBluetoothAddress;
	private static final int IDLE = 0;
	private static final int READY = 1;
	private static final int CONNECTED = 2;
	
	
	public CarLifeBluetooth(Context context, BluetoothListener listener) {
        Log.d(TAG, "on CarLifeBluetooth constructor");
        
        mContext = context;
        mBluetoothListener = listener;
	}
	
	public void stop()
	{	Log.d(TAG, "stop");
		mBluetoothListener = null;
		mMdBluetoothAddress = null;
		mContext = null;
	}
	
	public void startBtAutoPairRequestCb(int osType, String mdAddress, int delay) 
	{
		Log.d(TAG, "startBtAutoPairRequestCb, osType:" + osType + ", mdAddress:" + mdAddress + ", delay:" + delay);
		mMdBluetoothAddress = mdAddress;
    	if(mBluetoothListener != null) {
    		Log.d(TAG, "send BT Pair info status = 0");
    		mBluetoothListener.onSendBtPairInfo(IDLE, delay);
    	}
    	
	}
	
	public void btStartIdentifyReq(String address, String name)
	{
		mHuBlueToothAddress = address;
		mHuBlueToothName = name;
		if((mBluetoothListener != null) && (mHuBlueToothAddress != null)) {
			Log.d(TAG, "btStartIdentifyReq, mHuBlueToothAddress:" + mHuBlueToothAddress + 
					", mHuBlueToothName:" + mHuBlueToothName);
			mBluetoothListener.onSendStartBtIdentifyReq(mHuBlueToothAddress);
		}
	}
		
	public void mdBtPairInfoCb(byte[] mAddress, byte[] mPassKey, byte[] mHash, 
			byte[] mRandomizer, byte[] mUuid, byte[] mName, int mStatus) {
    	Log.d(TAG, "onMdBtPairInfo, address:" + new String(mAddress) + ", passKey:" + new String(mPassKey) + 
    			", hash:" + new String(mHash) + ", randomizer:" + new String(mRandomizer) + ", uuid:" + new String(mUuid) + 
    			", name:" + new String(mName) + ", status:" + mStatus);
    	
    	if(mBluetoothListener != null) {
    		Log.d(TAG, "mdBtPairInfo, send a BT Pair info status = 1");
    		mBluetoothListener.onSendBtPairInfo(READY, 0);
    	}
	}
	
	public void sendBtHfpResponse(int mStatus, int mCmd, int mDtmfCode){
		Log.d(TAG, "sendBtHfpResponse, status:" + mStatus + ", cmd:" +
				mCmd + ", dtmfCode:" + mDtmfCode);
		if(mBluetoothListener != null) {
			mBluetoothListener.onSendBtHfpResponse(mStatus, mCmd, mDtmfCode);
		}
	}
	
	public void sendBtHfpIndication(MsgBtHfpIndication mBtHfpIndication) {
		Log.d(TAG, "sendBtHfpIndication, state:" + mBtHfpIndication.state + 
				", phoneNumber:" + mBtHfpIndication.phoneNum + 
				", phoneName:" + mBtHfpIndication.phoneName + 
				", btaddress:" + mBtHfpIndication.address);
		if(mBluetoothListener != null) {
			mBluetoothListener.onSendBtHfpIndication(mBtHfpIndication);
		}		
	}
	
	public void sendBtHfpStatusRespone(int status, int type) {
		Log.d(TAG, "sendBtHfpStatusRespone, status:" + status + ", type:" + type);
		if(mBluetoothListener != null) {
			mBluetoothListener.onSendBtHfpStatusRespone(status, type);
		}	
	}
	
	public void handleHfpConnectStatus(int status)
	{
		if(mBluetoothListener != null) {
			if(status == 1) {
				Log.d(TAG, " send a BT Pair info status = 2");
				mBluetoothListener.onSendBtPairInfo(CONNECTED, 0);
			}
			Log.d(TAG, "handleHfpConnectStatus, status:" + status);
			//mBluetoothListener.onSendBtHfpConnection(status, mConnectedAddress, null);
		}
	}
	
	public String getHuAddress()
	{
		Log.d(TAG, "getHuAddress:" +mHuBlueToothAddress);
		return mHuBlueToothAddress;
	}
	
	public String getHuName()
	{	Log.d(TAG, "getHuName:" + mHuBlueToothName);
		return mHuBlueToothName;
		
	}
		
    public interface BluetoothListener
    {
    	void onSendStartBtIdentifyReq(String mAddress);
        void onSendBtPairInfo(final int mStatus, int mDelay);
        void onSendBtHfpIndication(MsgBtHfpIndication mHfpIdication);
        void onSendBtHfpResponse(int mStatus, int mCmd, int mDtmfCode);
        void onSendBtHfpConnection(int mState, String mAddress, String mName);
        void onSendBtHfpStatusRespone(int mStatus, int mType);
        void onsendBtErrorCode(String mErrorCode);
    }

    public class MsgBtHfpIndication {
        public int state;
        public String phoneNum;
        public String phoneName;
        public String address;
    }
}
