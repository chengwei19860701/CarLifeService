package com.hsae.carlife;

import com.hsae.carlife.common.CarLifeDef;

import android.media.AudioFormat;
import android.media.AudioRecord;
import android.media.MediaRecorder;
import android.util.Log;

public class CarLifeRecord
{

    private static final String TAG = "CarLifeRecord";

    private static final int RECORD_DEF_SAMPLE_RATE = 16000;
    private static final int RECORD_DEF_CHANNEL = AudioFormat.CHANNEL_IN_STEREO;
    private static final int RECORD_DEF_ENCODING = AudioFormat.ENCODING_PCM_16BIT;

    private final int mSampleRate;
    private final int mChannel;
    private final int mEncoding;
    private final Object mSyncObject;
    private int mMicState;

    private RecordThread mRecordThread;
    private final RecordListener mListener;

    public CarLifeRecord(RecordListener listener)
    {
        this(listener, RECORD_DEF_SAMPLE_RATE, RECORD_DEF_CHANNEL, RECORD_DEF_ENCODING);
    }

    public CarLifeRecord(RecordListener listener, int sampleRate, int channel, int encoding)
    {
        mListener = listener;
        mSampleRate = sampleRate;
        mChannel = channel;
        mEncoding = encoding;
        mSyncObject = new Object();
        mMicState = CarLifeDef.MIC_STATE_START;
    }

    public void start()
    {
        if (mMicState == CarLifeDef.MIC_STATE_START)
        {
            if (mRecordThread == null)
            {
                mRecordThread = new RecordThread();
                mRecordThread.start();
            }
        }
    }

    public void stop()
    {
        if (mRecordThread != null)
        {
            mRecordThread.cancel();
            mRecordThread = null;
        }
    }

    public void setMicState(int state)
    {
        Log.i(TAG, "setMicState, state = " + state + ", mMicState = " + mMicState);

        if (mMicState != state)
        {
            mMicState = state;

            if (mMicState == CarLifeDef.MIC_STATE_START)
            {
                start();
            }
            else if (mMicState == CarLifeDef.MIC_STATE_STOP)
            {
                stop();
            }
        }
    }

    private class RecordThread extends Thread
    {

        private boolean bStop;

        public RecordThread()
        {
            bStop = false;
        }

        @Override
        public void run()
        {
            synchronized(mSyncObject)
            {
                Log.i(TAG, "********************* RecordThread run start *********************");
                int bufferSize = AudioRecord.getMinBufferSize(mSampleRate, mChannel, mEncoding);
                AudioRecord audioRecord = new AudioRecord(MediaRecorder.AudioSource.MIC, mSampleRate, mChannel, mEncoding, bufferSize);
                byte[] buffer = new byte[bufferSize];
                while (!bStop)
                {
                    if (audioRecord.getRecordingState() != AudioRecord.RECORDSTATE_RECORDING)
                    {
                        audioRecord.startRecording();
                        if (audioRecord.getRecordingState() != AudioRecord.RECORDSTATE_RECORDING)
                        {
                            try {
                                sleep(500);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            continue;
                        }
                    }
                    int size = audioRecord.read(buffer, 0, bufferSize);
                    if (AudioRecord.ERROR_INVALID_OPERATION != size)
                    {
                        if (mListener != null)
                        {
                            mListener.onRecordData(buffer, size);
                        }
                    }
                }
                audioRecord.stop();
                audioRecord.release();
                audioRecord = null;
                Log.i(TAG, "********************* RecordThread run end *********************");
            }
        }

        public void cancel()
        {
            bStop = true;
        }

    }

    public interface RecordListener
    {
        void onRecordData(byte[] data, int len);
    }

}
