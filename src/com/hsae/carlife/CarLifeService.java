package com.hsae.carlife;

import java.lang.ref.WeakReference;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteException;
import android.util.Log;
import android.view.MotionEvent;
import android.view.Surface;

import com.hsae.carlife.CarLifeBluetooth.BluetoothListener;
import com.hsae.carlife.CarLifeBluetooth.MsgBtHfpIndication;
import com.hsae.carlife.CarLifeRecord.RecordListener;
import com.hsae.carlife.aidl.ICarLifeUICallback;
import com.hsae.carlife.aidl.ICarLifeUICommand;
import com.hsae.carlife.common.CarLifeDef;

public class CarLifeService extends Service
{

    private static final String TAG = "CarLifeService";

    private static final int MSG_CREATE = 0;
    private static final int MSG_DESTROY = 1;
    private static final int MSG_CONNECT = 2;
    private static final int MSG_DISCONNECT = 3;
    private static final int MSG_STARTAPP = 4;
    private static final int MSG_SHOW = 5;
    private static final int MSG_HIDE = 6;
    private static final int MSG_PAUSE = 7;
    private static final int MSG_RESUME = 8;
    private static final int MSG_SET_MIC_MODE = 9;
    private static final int MSG_TOUCH = 10;
    private static final int MSG_KEY = 11;
    private static final int MSG_SEND_RECORD_DATA = 12;
    private static final int MSG_SET_AUDIO_STREAM = 13;
    private static final int MSG_SEND_BT_PAIR_INFO = 14;
    private static final int MSG_BT_START_IDENTIFY_REQ = 15;
    private static final int MSG_BT_AUTO_PAIR_REQUEST = 16;
    private static final int MSG_SEND_BT_HFP_CONNECTION = 17;
    private static final int MSG_SEND_BT_HFP_RESPONSE = 18;
    private static final int MSG_SEND_BT_HFP_INDICATION = 19;
    private static final int MSG_SEND_BT_HFP_STATUS_RESPONE = 20;

    public static final int CARLIFE_DISCONNETED = 0x01;
    public static final int CARLIFE_CONNECTING = 0x02;
    public static final int CARLIFE_CONNECTED = 0x04;

    private HandlerThread mHandlerThread;
    private ServiceHandler mServiceHandler;
    private ICarLifeUICallback mUICallback;
    private CarLifeDisplay mCarLifeDisplay;
    private CarLifeTrack mMediaTrack;
    private CarLifeTrack mTTSTrack;
    private CarLifeTrack mVRTrack;
    private CarLifeRecord mMicRecord;
    private int mPhoneState;
    private CarLifeBluetooth mCarLifeBluetooth;
    private static boolean isVRPlay = false;
    HandlerThread trackWriteHandlerThread;
    AudioTrackWriteHandler trackWriteHandler;
	private static int featureKey = 0;
	private static int featureValue = 0;

    private static int mConnectState = CARLIFE_DISCONNETED;
    final Lock mConnectStateLock = new ReentrantLock();

    @Override
    public void onCreate()
    {
        super.onCreate();
        Log.d(TAG, "onCreate");

        mPhoneState = CarLifeDef.PHONE_IDLE;
        mHandlerThread = new HandlerThread("service");
        mHandlerThread.start();
        mServiceHandler = new ServiceHandler(this, mHandlerThread.getLooper());
        mMicRecord = new CarLifeRecord(mRecordListener);
        mCarLifeDisplay = new CarLifeDisplay();
        mMediaTrack = new CarLifeTrack();
        mTTSTrack = new CarLifeTrack();
        mVRTrack = new CarLifeTrack();

		// add by BT
        mCarLifeBluetooth = new CarLifeBluetooth(this, mBtListener);

        trackWriteHandlerThread = new HandlerThread("trackWrite");
        trackWriteHandlerThread.start();
        trackWriteHandler = new AudioTrackWriteHandler(trackWriteHandlerThread.getLooper());
    }

    @Override
    public void onDestroy()
    {
        super.onDestroy();
        Log.d(TAG, "onDestroy");

        mCarLifeBluetooth.stop();
        //mHandlerThread.quit();
    }

    @Override
    public IBinder onBind(Intent intent)
    {
        Log.d(TAG, "onBind intent = " + intent.getAction());

        String action = intent.getAction();
        if (action.equals(CarLifeDef.ACTION_CARLIFE_UI))
        {
            int mode = intent.getIntExtra(CarLifeDef.EXTRA_CONNECT_MODE, CarLifeDef.CONNECT_MODE_NULL);
            String ipAddress = intent.getStringExtra(CarLifeDef.EXTRA_IP_ADDRESS);

            featureKey = intent.getIntExtra(CarLifeDef.EXTRA_FEATURE_KEY, 0);
            featureValue = intent.getIntExtra(CarLifeDef.EXTRA_FEATURE_VALUE, 0);

            return new UICommandStub(mode, ipAddress);
        }
        return null;
    }

    @Override
    public boolean onUnbind(Intent intent)
    {
        Log.d(TAG, "onUnbind intent = " + intent.getAction());

        String action = intent.getAction();
        if (action.equals(CarLifeDef.ACTION_CARLIFE_UI))
        {
            mServiceHandler.obtainMessage(MSG_DESTROY).sendToTarget();
        }
        return super.onUnbind(intent);
    }

    public void setConnectState(int state)
    {
    	Log.d(TAG, "setConnectState, mConnectState:" + mConnectState +
    			", check state:" + state);
    	mConnectStateLock.lock();
    	try{
    		mConnectState = state;
    	}finally{
    		mConnectStateLock.unlock();
    	}
    }

    public void onConnectProgress(int progress)
    {
        Log.d(TAG, "onConnectProgress progress = " + progress);

        if (mUICallback != null)
        {
            try
            {
                mUICallback.onConnectProgress(progress);
            }
            catch (RemoteException e)
            {
                e.printStackTrace();
            }
        }
    }

    public void onConnectSuccess()
    {
        Log.d(TAG, "onConnectSuccess");
        setConnectState(CARLIFE_CONNECTED);
        if (mUICallback != null)
        {
            try
            {
                mUICallback.onConnectSuccess();
            }
            catch (RemoteException e)
            {
                e.printStackTrace();
            }
        }
    }

    public void onConnectFailure()
    {
        Log.d(TAG, "onConnectFailure");
        setConnectState(CARLIFE_DISCONNETED);
        if (mUICallback != null)
        {
            try
            {
                mUICallback.onConnectFailure();
            }
            catch (RemoteException e)
            {
                e.printStackTrace();
            }
        }
    }

    public void onConnectException(int exception)
    {
        Log.d(TAG, "onConnectException exception = " + exception);
        setConnectState(CARLIFE_DISCONNETED);
        if (mUICallback != null)
        {
            try
            {
                mUICallback.onConnectException(exception);
            }
            catch (RemoteException e)
            {
                e.printStackTrace();
            }
        }
    }

    public void onAdbDisabled()
    {
        Log.d(TAG, "onAdbDisabled");

        if (mUICallback != null)
        {
            try
            {
                mUICallback.onAdbDisabled();
            }
            catch (RemoteException e)
            {
                e.printStackTrace();
            }
        }
    }

    public void onAppNotInstalled()
    {
        Log.d(TAG, "onAppNotInstalled");

        if (mUICallback != null)
        {
            try
            {
                mUICallback.onAppNotInstalled();
            }
            catch (RemoteException e)
            {
                e.printStackTrace();
            }
        }
    }

    public void onAppGoForeground()
    {
        Log.d(TAG, "onAppGoForeground");

        if (mUICallback != null)
        {
            try
            {
                mUICallback.onAppGoForeground();
            }
            catch (RemoteException e)
            {
                e.printStackTrace();
            }
        }
    }

    public void onAppGoBackground()
    {
        Log.d(TAG, "onAppGoBackground");

        if (mUICallback != null)
        {
            try
            {
                mUICallback.onAppGoBackground();
            }
            catch (RemoteException e)
            {
                e.printStackTrace();
            }
        }
    }

    public void onGoToDesktop()
    {
        Log.d(TAG, "onGoToDesktop");

        if (mUICallback != null)
        {
            try
            {
                mUICallback.onGoToDesktop();
            }
            catch (RemoteException e)
            {
                e.printStackTrace();
            }
        }
    }

    public void onDataReceiveError()
    {
        Log.d(TAG, "onDataReceiveError");
        setConnectState(CARLIFE_DISCONNETED);
        if (mUICallback != null)
        {
            try
            {
                mUICallback.onDataReceiveError();
            }
            catch (RemoteException e)
            {
                e.printStackTrace();
            }
        }
    }
    
    public void onIosDeviceDetached()
    {
        Log.d(TAG, "onIosDeviceDetached");
        setConnectState(CARLIFE_DISCONNETED);
        if (mUICallback != null)
        {
            try
            {
                mUICallback.onIosDeviceDetached();
            }
            catch (RemoteException e)
            {
                e.printStackTrace();
            }
        }
    }

    public void onModuleStateChanged(int module, int state)
    {
        Log.d(TAG, "onModuleStateChanged module = " + module + ", state = " + state);

        if (module == CarLifeDef.MODULE_PHONE)
        {
            mPhoneState = state;
        }

        if (mUICallback != null)
        {
            try
            {
                mUICallback.onModuleStateChanged(module, state);
            }
            catch (RemoteException e)
            {
                e.printStackTrace();
            }
        }
    }

    public int requestAudioStream(int source)
    {
        Log.d(TAG, "requestAudioStream source = " + source);

        if (mUICallback != null)
        {
            try
            {
                return mUICallback.requestAudioStream(source);
            }
            catch (RemoteException e)
            {
                e.printStackTrace();
            }
        }
        return -1;
    }

    public boolean reqMicPermission(int type)
    {
        if (mUICallback != null)
        {
            try
            {
                return mUICallback.reqMicPermission(type);
            }
            catch (RemoteException e)
            {
                e.printStackTrace();
            }
        }
        return false;
    }

    public void onVideoDataReceived(byte[] data, int len)
    {
        if (mCarLifeDisplay != null)
        {
            mCarLifeDisplay.write(data, len);
        }
    }

    public void onMediaInit(int sampleRate, int channelConfig, int sampleFormat)
    {
        Log.d(TAG, "onMediaInit");

        int stream = requestAudioStream(CarLifeDef.AUDIO_SOURCE_MEDIA);
        if (stream == -1)
        {
            mMediaTrack.setParam(sampleRate, channelConfig, sampleFormat, CarLifeTrack.CarLifeStream.MEDIA);
        }
        else
        {
            mMediaTrack.setParam(sampleRate, channelConfig, sampleFormat, stream, CarLifeTrack.CarLifeStream.MEDIA);
            mMediaTrack.play();
        }
    }

    public void onMediaDataReceived(byte[] data, int len)
    {
        if (mMediaTrack != null)
        {
//            mMediaTrack.write(data, len);
            trackWriteHandler.obtainMessage(AudioTrackWriteHandler.MST_WRITE_DATA, data).sendToTarget();
        }
    }

    public void onMediaPause()
    {
        Log.d(TAG, "onMediaPause");

        if (mMediaTrack != null)
        {
            mMediaTrack.pause();
        }
    }

    public void onMediaResume()
    {
        Log.d(TAG, "onMediaResume");

        if (mMediaTrack != null)
        {
            mMediaTrack.resume();
        }
    }

    public void onTTSInit(int sampleRate, int channelConfig, int sampleFormat)
    {
        Log.d(TAG, "onTTSInit");

        int stream = requestAudioStream(CarLifeDef.AUDIO_SOURCE_TTS);
        if (stream == -1)
        {
            mTTSTrack.setParam(sampleRate, channelConfig, sampleFormat, CarLifeTrack.CarLifeStream.TTS);
        }
        else
        {
            mTTSTrack.setParam(sampleRate, channelConfig, sampleFormat, stream, CarLifeTrack.CarLifeStream.TTS);
            mTTSTrack.play();
        }
    }

    public void onTTSDataReceived(byte[] data, int len)
    {
        if (!isVRPlay && (mTTSTrack != null))
        {
            mTTSTrack.write(data, len);
        }
    }

    public void onTTSStop()
    {
        Log.d(TAG, "onTTSStop");

        if (mTTSTrack != null)
        {
            mTTSTrack.release();
        }

        if (mUICallback != null)
        {
            try
            {
                mUICallback.onTTSStop();
            }
            catch (RemoteException e)
            {
                e.printStackTrace();
            }
        }
    }

    public void onVRInit(int sampleRate, int channelConfig, int sampleFormat)
    {
        Log.d(TAG, "onVRInit");

        int stream = requestAudioStream(CarLifeDef.AUDIO_SOURCE_VR);
        if (stream == -1)
        {
            mVRTrack.setParam(sampleRate, channelConfig, sampleFormat, CarLifeTrack.CarLifeStream.VR);
        }
        else
        {
            mVRTrack.setParam(sampleRate, channelConfig, sampleFormat, stream, CarLifeTrack.CarLifeStream.VR);
            mVRTrack.play();
            isVRPlay = true;
        }
    }

    public void onVRDataReceived(byte[] data, int len)
    {
        if (mVRTrack != null)
        {
            mVRTrack.write(data, len);
        }
    }

    public void onVRStop()
    {
        Log.d(TAG, "onVRStop");

        if (mVRTrack != null)
        {
            mVRTrack.release();
            isVRPlay = false;
        }

        if (mUICallback != null)
        {
            try
            {
                mUICallback.onVRStop();
            }
            catch (RemoteException e)
            {
                e.printStackTrace();
            }
        }
    }

    public void onMicRecordWakeupStart()
    {
        Log.d(TAG, "onMicRecordWakeupStart mPhoneState = " + mPhoneState + ", mVRTrack.isMute() = " + mVRTrack.isMute());

        if (reqMicPermission(CarLifeDef.RECORD_WAKEUP))
        {
            Log.i(TAG, "onMicRecordWakeupStart, reqMicPermission success");
            if (/*mPhoneState == CarLifeDef.PHONE_IDLE && */!mVRTrack.isMute())
            {
                mMicRecord.start();
            }
        }
    }

    public void onMicRecordEnd()
    {
        Log.d(TAG, "onMicRecordEnd");

        mMicRecord.stop();
    }

    public void onMicRecordRecogStart()
    {
        Log.d(TAG, "onMicRecordRecogStart mPhoneState = " + mPhoneState + ", mVRTrack.isMute() = " + mVRTrack.isMute());

        if (reqMicPermission(CarLifeDef.RECORD_RECOG))
        {
            Log.i(TAG, "onMicRecordRecogStart, reqMicPermission success");
            if (/*mPhoneState == CarLifeDef.PHONE_IDLE && */!mVRTrack.isMute())
            {
                mMicRecord.start();
            }
        }
    }

    public void onMdOsNotSupport(byte[] os, byte[] release, int sdk)
    {
    	String mdOs = new String(os);
    	String mdRelease = new String(release);
    	Log.d(TAG, "onMdOsNotSupport, os:" +mdOs + ", release:" +mdRelease + ", sdk:" + sdk);
    	if(mUICallback != null) {
    		try {
				mUICallback.onMdOsNotSupport(mdOs, mdRelease, sdk);
			} catch (RemoteException e) {
				e.printStackTrace();
			}
    	}
    	setConnectState(CARLIFE_DISCONNETED);
    }

    //add for BT
    public void onBtFeatureConfigureFinish(int result)
    {
    	Log.d(TAG, "onBtFeatureConfigureFinsh, result:" + result);
    	if(mUICallback != null) {
    		try {
				mUICallback.btFeatureConfigureFinish(result);
			} catch (RemoteException e) {
				e.printStackTrace();
			}
    	}
    }

  //该命令从手机发送到车机
    public void onBtIdentifyResultInd(int status, byte[] address)
    {
    	Log.d("BT", "onBtIdentifyResultInd, status:" + status + ", address:" + new String(address));
    	if(mUICallback != null) {
    		try {
				mUICallback.btIdentifyResultIndCb(status, (new String(address)));
			} catch (RemoteException e) {
				e.printStackTrace();
			}
    	}
    }

    public void onStartBtAutoPairRequest(int osType, byte[] address )
    {
    	String mdAddress= new String(address);
    	Log.d("BT", "onStartBtAutoPairRequest, osType:" + osType + ", mdAddress:" + mdAddress);
    	mServiceHandler.obtainMessage(MSG_BT_AUTO_PAIR_REQUEST, osType, -1, mdAddress).sendToTarget();
    }

    public void onMdBtPairInfo(byte[] address, byte[] passKey, byte[] hash, byte[] randomizer, byte[] uuid, byte[] name, int status)
    {
    	Log.d("BT", "onMdBtPairInfo, address:" + new String(address) + ", passKey:" + new String(passKey) +
    			", hash:" + new String(hash) + ", randomizer:" + new String(randomizer) + ", uuid:" + new String(uuid) +
    			", name:" + new String(name) + ", status:" + status);
        if(mCarLifeBluetooth != null) {
        	mCarLifeBluetooth.mdBtPairInfoCb(address, passKey, hash, randomizer, uuid, name, status);
        }

    }

  //处理Tel state 的回调事件
    public void onTelState(int state)
    {
    	Log.d(TAG, "onTelState, state:" + state);
    	if(mUICallback != null) {
    		try {
				mUICallback.ontelState(state);
			} catch (RemoteException e) {
				e.printStackTrace();
			}
    	}
    }

    //处理手机端的相关Hfp request
    public void onBtHfpRequest(int command, byte[] phoneNum, int dtmfCode)
    {
    	String mPhoneNumber = new String(phoneNum);
    	Log.d(TAG, "onBtHfpRequest, command:" + command + ", phoneNum:"
    			+ mPhoneNumber + ", dtmfCode:" + dtmfCode);
    	if(mUICallback != null) {
    		try {
				mUICallback.btHfpRequestCb(command, mPhoneNumber, dtmfCode);
			} catch (RemoteException e) {
				e.printStackTrace();
			}
    	}
    }

  //该请求从手机发送到车机，用来获取指定的状态，目前主要用来获取车机端的MIC是否mute的状态
    public void onBtHfpStatusRequest(int type)
    {
    	if(mUICallback != null) {
    		try {
				mUICallback.btHfpStatusRequestCb(type);
			} catch (RemoteException e) {
				e.printStackTrace();
			}
    	}
    }

    private final BluetoothListener mBtListener = new BluetoothListener()
    {
        @Override
        public void onSendBtPairInfo(final int mStatus, int mDelay)
        {
        	Log.d("BT", "onSendBtPairInfo, mStatus:" + mStatus + ", mDelay:" + mDelay);
        	if (mServiceHandler != null) {
        		if(mDelay != 0) {
        			mServiceHandler.postDelayed(new Runnable()
        			{
        				@Override
        				public void run() {
        					mServiceHandler.obtainMessage(MSG_SEND_BT_PAIR_INFO, mStatus, -1).sendToTarget();
        				}
        			}, mDelay);

        		} else {
        			mServiceHandler.obtainMessage(MSG_SEND_BT_PAIR_INFO, mStatus, -1).sendToTarget();
        		}
        	}
        }

        @Override
        public void onSendStartBtIdentifyReq(String mAddress)
        {
        	Log.d("BT", "onSendStartBtIdentifyReq, mAddress:" + mAddress);
        	if (mServiceHandler != null) {
        		mServiceHandler.obtainMessage(MSG_BT_START_IDENTIFY_REQ, -1, -1, mAddress).sendToTarget();
        	}
        }

		@Override
		public void onSendBtHfpIndication(MsgBtHfpIndication mHfpIdication) {
			if(mServiceHandler != null) {
				mServiceHandler.obtainMessage(MSG_SEND_BT_HFP_INDICATION, mHfpIdication).sendToTarget();
			}

		}

		@Override
		public void onSendBtHfpResponse(int mStatus, int mCmd, int mDtmfCode) {
			Log.d(TAG, "onSendBtHfpResponse");
			if(mServiceHandler != null) {
				mServiceHandler.obtainMessage(MSG_SEND_BT_HFP_RESPONSE, mStatus, mCmd, mDtmfCode).sendToTarget();
			}
		}

		@Override
		public void onSendBtHfpConnection(int mState, String mAddress,
				String mName) {
			if (mServiceHandler != null) {
        		mServiceHandler.obtainMessage(MSG_SEND_BT_HFP_CONNECTION, mState, -1, mAddress).sendToTarget();
        	}

		}

		@Override
		public void onSendBtHfpStatusRespone(int mStatus, int mType) {
			if (mServiceHandler != null) {
        		mServiceHandler.obtainMessage(MSG_SEND_BT_HFP_STATUS_RESPONE, mStatus, mType, null).sendToTarget();
        	}
		}

		@Override
		public void onsendBtErrorCode(String mErrorCode) {
			// TODO Auto-generated method stub

		}

     };

 //add BT end

    private final RecordListener mRecordListener = new RecordListener()
    {
        @Override
        public void onRecordData(byte[] data, int len)
        {
            mServiceHandler.obtainMessage(MSG_SEND_RECORD_DATA, len, -1, data).sendToTarget();
        }

    };

    class UICommandStub extends ICarLifeUICommand.Stub {

        public UICommandStub(int mode, String ipAddress)
        {
            mServiceHandler.obtainMessage(MSG_CREATE, mode, -1, ipAddress).sendToTarget();
        }

        @Override
        public void registerUICallback(ICarLifeUICallback callback) throws RemoteException
        {
            mUICallback = callback;
        }

        @Override
        public void unregisterUICallback(ICarLifeUICallback callback) throws RemoteException
        {
            mUICallback = null;
        }

        @Override
        public void connect(String channel) throws RemoteException
        {
        	Log.d(TAG, "connect, mConnectState:" +mConnectState);
        	mConnectStateLock.lock();
        	try {
        		if((CARLIFE_CONNECTING == mConnectState) || (CARLIFE_CONNECTED == mConnectState)) {
        			return;
        		}
        	}finally {
        		mConnectStateLock.unlock();
        	}

            mServiceHandler.obtainMessage(MSG_CONNECT, channel).sendToTarget();
            setConnectState(CARLIFE_CONNECTING);
        }

        @Override
        public void startBtIdentifyReq(String mHuAddress, String mHuName) throws RemoteException
        {
        	Log.d("BT", "command startBtIdentifyReq, mHuAddress:" + mHuAddress + ", mHuName:" + mHuName);
        	if(mCarLifeBluetooth != null) {
        		mCarLifeBluetooth.btStartIdentifyReq(mHuAddress, mHuName);
        	}
        }

        @Override
        public void btHfpConnectStatus(int status) throws RemoteException
        {
        	Log.d("BT", "command btHfpConnectStatus, status:" + status);
        	if(mCarLifeBluetooth != null) {
        		mCarLifeBluetooth.handleHfpConnectStatus(status);
        	}
        }

        @Override
		public void sendBtHfpResponse(int status, int cmd, int dtmfCode)
				throws RemoteException {
        	if(mCarLifeBluetooth != null) {
        		mCarLifeBluetooth.sendBtHfpResponse(status, cmd, dtmfCode);
        	}

		}

		@Override
		public void sendBtHfpIndication(int state, String phoneNum,
				String phoneName, String address) throws RemoteException {
			if(mCarLifeBluetooth != null) {
				MsgBtHfpIndication mBtHfpIndication = mCarLifeBluetooth.new MsgBtHfpIndication();
				mBtHfpIndication.state = state;
				mBtHfpIndication.phoneNum = phoneNum;
				mBtHfpIndication.phoneName = phoneName;
				mBtHfpIndication.address = address;
        		mCarLifeBluetooth.sendBtHfpIndication(mBtHfpIndication);
        	}
		}

		@Override
		public void sendBtHfpStatusRespone(int status, int type)
				throws RemoteException {
			if(mCarLifeBluetooth != null) {
				mCarLifeBluetooth.sendBtHfpStatusRespone(status, type);
			}
		}

        @Override
        public void disconnect() throws RemoteException
        {
            mServiceHandler.obtainMessage(MSG_DISCONNECT).sendToTarget();
            setConnectState(CARLIFE_DISCONNETED);
        }

        @Override
        public void startapp() throws RemoteException
        {
            mServiceHandler.obtainMessage(MSG_STARTAPP).sendToTarget();
        }

        @Override
        public void show(Surface surface, int width, int height) throws RemoteException
        {
            mCarLifeDisplay.show(surface, width, height);
            mServiceHandler.obtainMessage(MSG_SHOW, width, height).sendToTarget();
        }

        @Override
        public void hide() throws RemoteException
        {
            mServiceHandler.obtainMessage(MSG_HIDE).sendToTarget();
            mCarLifeDisplay.hide();
        }

        @Override
        public void mute(int source) throws RemoteException
        {
            if (source == CarLifeDef.AUDIO_SOURCE_MEDIA)
            {
                if (mMediaTrack != null)
                {
                    mMediaTrack.mute();
                }
            }
            else if (source == CarLifeDef.AUDIO_SOURCE_TTS)
            {
                if (mTTSTrack != null)
                {
                    mTTSTrack.mute();
                }
            }
            else if (source == CarLifeDef.AUDIO_SOURCE_VR)
            {
                if (mVRTrack != null)
                {
                    mVRTrack.mute();
                }
            }
        }

        @Override
        public void unmute(int source) throws RemoteException
        {
            if (source == CarLifeDef.AUDIO_SOURCE_MEDIA)
            {
                if (mMediaTrack != null)
                {
                    mMediaTrack.unmute();
                }
            }
            else if (source == CarLifeDef.AUDIO_SOURCE_TTS)
            {
                if (mTTSTrack != null)
                {
                    mTTSTrack.unmute();
                }
            }
            else if (source == CarLifeDef.AUDIO_SOURCE_VR)
            {
                if (mVRTrack != null)
                {
                    mVRTrack.unmute();
                }
            }
        }

        @Override
        public void pause(int source) throws RemoteException
        {
            mServiceHandler.obtainMessage(MSG_PAUSE, source, -1).sendToTarget();
        }

        @Override
        public void resume(int source) throws RemoteException
        {
            mServiceHandler.obtainMessage(MSG_RESUME, source, -1).sendToTarget();
        }

        @Override
        public void setVolume(int source, int volume) throws RemoteException
        {
            if (source == CarLifeDef.AUDIO_SOURCE_MEDIA)
            {
                if (mMediaTrack != null)
                {
                    mMediaTrack.setVolume(volume);
                }
            }
            else if (source == CarLifeDef.AUDIO_SOURCE_TTS)
            {
                if (mTTSTrack != null)
                {
                    mTTSTrack.setVolume(volume);
                }
            }
            else if (source == CarLifeDef.AUDIO_SOURCE_VR)
            {
                if (mVRTrack != null)
                {
                    mVRTrack.setVolume(volume);
                }
            }
        }

        @Override
        public int getVolume(int source) throws RemoteException
        {
            if (source == CarLifeDef.AUDIO_SOURCE_MEDIA)
            {
                if (mMediaTrack != null)
                {
                    return mMediaTrack.getVolume();
                }
            }
            else if (source == CarLifeDef.AUDIO_SOURCE_TTS)
            {
                if (mTTSTrack != null)
                {
                    return mTTSTrack.getVolume();
                }
            }
            else if (source == CarLifeDef.AUDIO_SOURCE_VR)
            {
                if (mVRTrack != null)
                {
                    return mVRTrack.getVolume();
                }
            }
            return -1;
        }

        @Override
        public void setMicMode(int mode) throws RemoteException
        {
            mServiceHandler.obtainMessage(MSG_SET_MIC_MODE, mode, -1).sendToTarget();
        }

        @Override
        public void setMicState(int state) throws RemoteException
        {
            mMicRecord.setMicState(state);
        }

        @Override
        public void onTouchEvent(MotionEvent event) throws RemoteException
        {
            mServiceHandler.obtainMessage(MSG_TOUCH, event).sendToTarget();
        }

        @Override
        public void onKeyEvent(int keyCode) throws RemoteException
        {
            mServiceHandler.obtainMessage(MSG_KEY, keyCode, -1).sendToTarget();
        }

        @Override
        public void setAudioStream(int source, int stream) throws RemoteException
        {
            Log.d(TAG, "setAudioStream source = " + source + ", stream = " + stream);

            mServiceHandler.obtainMessage(MSG_SET_AUDIO_STREAM, source, stream).sendToTarget();
        }

		@Override
		public void killProcess() throws RemoteException {
			Log.d(TAG, "killProcess");
			Thread mKillThread = new Thread(new Runnable() {
				@Override
				public void run() {
					native_killProcess();
				}
			});
			
			mKillThread.start();
		}
    }

    static class ServiceHandler extends Handler
    {

        private final WeakReference<CarLifeService> mService;

        public ServiceHandler(CarLifeService service, Looper looper)
        {
            super(looper);
            mService = new WeakReference<CarLifeService>(service);
        }

        @Override
        public void handleMessage(Message msg)
        {
            CarLifeService service = mService.get();
            switch (msg.what)
            {
            case MSG_CREATE:
            	service.native_create(msg.arg1, (String) msg.obj, featureKey, featureValue);
                break;
            case MSG_DESTROY:
                service.native_destroy();
                break;
            case MSG_CONNECT:
                service.native_connect((String) msg.obj);
                break;
            case MSG_DISCONNECT:
                service.native_disconnect();
                break;
            case MSG_STARTAPP:
                service.native_startapp();
                break;
            case MSG_SHOW:
                service.native_show(msg.arg1, msg.arg2, service.mCarLifeDisplay.getRate());
                break;
            case MSG_HIDE:
                service.native_hide();
                break;
            case MSG_PAUSE:
                service.native_pause(msg.arg1);
                break;
            case MSG_RESUME:
                service.native_resume(msg.arg1);
                break;
            case MSG_SET_MIC_MODE:
                service.native_setMicMode(msg.arg1);
                break;
            case MSG_TOUCH:
                MotionEvent event = (MotionEvent) msg.obj;
                service.native_touch(event.getAction(), (int) event.getX(), (int) event.getY());
                break;
            case MSG_KEY:
                service.native_key(msg.arg1);
                break;
            case MSG_SEND_RECORD_DATA:
                service.native_sendRecordData((byte[]) msg.obj, msg.arg1);
                break;
            case MSG_SET_AUDIO_STREAM:
                int source = msg.arg1;
                int stream = msg.arg2;
                switch (source)
                {
                case CarLifeDef.AUDIO_SOURCE_MEDIA:
                    if (service.mMediaTrack != null)
                    {
                        service.trackWriteHandler.removeMessages(AudioTrackWriteHandler.MST_WRITE_DATA);
                        service.mMediaTrack.setAudioStream(stream);
                    }
                    break;
                case CarLifeDef.AUDIO_SOURCE_TTS:
                    if (service.mTTSTrack != null)
                    {
                        service.mTTSTrack.setAudioStream(stream);
                    }
                    break;
                case CarLifeDef.AUDIO_SOURCE_VR:
                    if (service.mVRTrack != null)
                    {
                        service.mVRTrack.setAudioStream(stream);
                    }
                    break;
                }
                break;
        	case MSG_BT_AUTO_PAIR_REQUEST:
        		int osType = msg.arg1;
        		String mdAddress = (String)msg.obj;
        		Log.d("BT", "handle MSG_BT_AUTO_PAIR_REQUEST osType:" + osType + ", mdAddress:" + mdAddress);
        		int delay = 0;
        		if (service.mUICallback != null)
        		{
        			try
        			{
        				delay = service.mUICallback.startBtAutoPairRequestCb(osType, mdAddress);
        			}
        			catch (RemoteException e)
        			{
        				e.printStackTrace();
        			}
        		}

        		if(service.mCarLifeBluetooth != null)
        		{
        			service.mCarLifeBluetooth.startBtAutoPairRequestCb(osType, mdAddress, delay);
        		}
        		break;
        	case MSG_BT_START_IDENTIFY_REQ:
        		String mHuAddress = (String)msg.obj;
        		Log.d("BT", "handle MSG_BT_START_IDENTIFY_REQ, mHuAddress:" + mHuAddress);
        		service.native_sendBtStartIdentifyReq(mHuAddress);
        		break;
        	case MSG_SEND_BT_PAIR_INFO:
        		String huAddress = service.mCarLifeBluetooth.getHuAddress();
        		String huName = service.mCarLifeBluetooth.getHuName();
        		Log.d("BT", "handle MSG_SEND_BT_PAIR_INFO, huAddress:" + huAddress + ", huName:" + huName);
        		if(huAddress != null){
        			service.native_sendBtPairInfo(huAddress, msg.arg1, huName);
        		}
        		break;
        	case MSG_SEND_BT_HFP_CONNECTION:
        		String address = (String)msg.obj;
        		Log.d(TAG, "handle MSG_SEND_BT_HFP_CONNECTION, state:" + msg.arg1 + ", mAddress:" + address);
        		service.native_sendBtHfpConnection(msg.arg1, address, null);
        		break;
        	case MSG_SEND_BT_HFP_RESPONSE:
        		int dtmfCode = (Integer)msg.obj;
        		service.native_sendBtHfpResponse(msg.arg1, msg.arg2, dtmfCode);
        		break;
        	case MSG_SEND_BT_HFP_INDICATION:
        		MsgBtHfpIndication mHfpIndication = (MsgBtHfpIndication)msg.obj;
        		service.native_sendBtHfpIndication(mHfpIndication.state, mHfpIndication.phoneNum, mHfpIndication.phoneName, mHfpIndication.address);
        		break;
        	case MSG_SEND_BT_HFP_STATUS_RESPONE:
        		service.native_sendBtHfpStatusRespone(msg.arg1, msg.arg2);
        		break;
            }
        }
    }

    class AudioTrackWriteHandler extends Handler {

        public static final int MST_WRITE_DATA = 0;

        public AudioTrackWriteHandler(Looper looper) {
            super(looper);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            if(msg.what == 0) {
                byte[] data = (byte[]) msg.obj;
                if(mMediaTrack != null) {
                    mMediaTrack.write(data, data.length);
                }
            }
        }
    }


    static
    {
        System.loadLibrary("carlife_jni");
    }

    public native void native_create(int os, String ipAddress, int featureKey, int featureValue);
    public native void native_destroy();
    public native void native_connect(String channel);
    public native void native_disconnect();
    public native void native_startapp();
    public native void native_show(int width, int height, int rate);
    public native void native_hide();
    public native void native_pause(int source);
    public native void native_resume(int source);
    public native void native_setMicMode(int mode);
    public native void native_touch(int action, int x, int y);
    public native void native_key(int keyCode);
    public native void native_sendRecordData(byte[] data, int len);
    public native void native_sendBtPairInfo(String address, int status, String name);
    public native void native_sendBtHfpIndication(int state, String phoneNum, String name, String address);
    public native void native_sendBtHfpResponse(int status, int cmd, int dtmfCode);
    public native void native_sendBtHfpConnection(int state, String address, String name);
    public native void native_sendBtHfpStatusRespone(int status, int type);
    public native void native_sendBtStartIdentifyReq(String address);
    public native void native_sendBtErrorCode(String errorCode);
    public native void native_killProcess();

}
