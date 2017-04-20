package com.hsae.carlife;

import android.media.AudioFormat;
import android.media.AudioTrack;
import android.util.Log;

import com.hsae.carlife.common.CarLifeDef;

public class CarLifeTrack
{

    private static final String TAG = "CarLifeTrack";

    private static final float MUTE_VOLUME = 0;
    private static final float NORMAL_VOLUME = 1.0f;

    private AudioTrack mAudioTrack;
    private int mVolume;
    private int mAudioStream;
    private int mAudioRate;
    private int mAudioChannnel;
    private int mAudioFormat;
    private CarLifeStream mCarLifeStream;
    private Object mSyncObject;
    private boolean mMute;

    public enum CarLifeStream
    {
        MEDIA, TTS, VR
    }

    public CarLifeTrack()
    {
        mMute = false;
        mSyncObject = new Object();
    }

    public CarLifeTrack(int rate, int channel, int format, CarLifeStream carlifeStream)
    {
        setParam(rate, channel, format, carlifeStream);
    }

    public CarLifeTrack(int rate, int channel, int format, int audioStream, CarLifeStream carlifeStream)
    {
        setParam(rate, channel, format, audioStream, carlifeStream);
    }

    public void setParam(int rate, int channel, int format, CarLifeStream carlifeStream)
    {
        setParam(rate, channel, format, -1, carlifeStream);
    }

    public void setParam(int rate, int channel, int format, int audioStream, CarLifeStream carlifeStream)
    {
        Log.i(TAG, "setParam rate = " + rate + ", channel = " + channel + ", audioStream = " + audioStream + ", carlifeStream = " + carlifeStream);

        mAudioStream = audioStream;
        mAudioRate = rate;
        mCarLifeStream = carlifeStream;
        mVolume = CarLifeDef.AUDIO_MAX_VOLUME_LEVEL;

        int channelConfig = AudioFormat.CHANNEL_OUT_DEFAULT;
        if (channel == 1)
        {
            channelConfig = AudioFormat.CHANNEL_OUT_MONO;
        }
        else if (channel == 2)
        {
            channelConfig = AudioFormat.CHANNEL_OUT_STEREO;
        }
        mAudioChannnel = channelConfig;

        int audioFormat = AudioFormat.ENCODING_DEFAULT;
        if (format == 8)
        {
            audioFormat = AudioFormat.ENCODING_PCM_8BIT;
        }
        else if (format == 16)
        {
            audioFormat = AudioFormat.ENCODING_PCM_16BIT;
        }
        mAudioFormat = audioFormat;

        if (mAudioStream != -1)
        {
        	release();    //20170306 偶发Unable to retrieve AudioTrack pointer for write()
            int bufSize = AudioTrack.getMinBufferSize(rate, channelConfig, audioFormat)*4;
            mAudioTrack = new AudioTrack(audioStream, rate, channelConfig, audioFormat, bufSize, AudioTrack.MODE_STREAM);
            if (mMute)
            {
                mute();
            }
        }
    }

    public boolean isMute()
    {
        return mMute;
    }

    public void write(byte[] data, int len)
    {
        synchronized (mSyncObject)
        {
            if (mAudioTrack != null)
            {
                mAudioTrack.write(data, 0, len);
            }
        }
    }

    public void play()
    {
        synchronized (mSyncObject)
        {
            if (mAudioTrack != null)
            {
                mAudioTrack.play();
            }
        }
    }

    public void resume()
    {
        synchronized (mSyncObject)
        {
            if (mAudioTrack != null)
            {
                mAudioTrack.play();
            }
        }
    }

    public void pause()
    {
        synchronized (mSyncObject)
        {
            if (mAudioTrack != null)
            {
                mAudioTrack.pause();
            }
        }
    }

    public void release()
    {
        synchronized (mSyncObject)
        {
            if (mAudioTrack != null)
            {
                mAudioTrack.release();
                mAudioTrack = null;
            }
        }
    }

    public void mute()
    {
        mMute = true;
        synchronized (mSyncObject)
        {
            if (mAudioTrack != null)
            {
                mAudioTrack.setStereoVolume(MUTE_VOLUME, MUTE_VOLUME);
            }
        }
    }

    public void unmute()
    {
        mMute = false;
        synchronized (mSyncObject)
        {
            if (mAudioTrack != null)
            {
                mAudioTrack.setStereoVolume(NORMAL_VOLUME, NORMAL_VOLUME);
            }
        }
    }

    public void setVolume(int volume)
    {
        synchronized (mSyncObject)
        {
            if (mAudioTrack != null)
            {
                mVolume = volume;
                float fvolume = volume * 0.1f;
                mAudioTrack.setStereoVolume(fvolume, fvolume);
            }
        }
    }

    public int getVolume()
    {
        return mVolume;
    }

    public void setAudioStream(int stream)
    {
        Log.d(TAG, "setAudioStream stream = " + stream + ", mCarLifeStream = " + mCarLifeStream);

        if (mCarLifeStream == null)
        {
            Log.i(TAG, "This audiostream is not ready!");
            return;
        }
        synchronized (mSyncObject)
        {
            if (mAudioStream != stream)
            {
                mAudioStream = stream;
                if (mAudioTrack != null)
                {
                    mAudioTrack.release();
                    mAudioTrack = null;
                }
                if (mAudioStream == -1)
                {
                    return;
                }
                Log.i(TAG, "setAudioStream mAudioRate = " + mAudioRate + ", mAudioChannnel = " + mAudioChannnel + ", mAudioFormat = " + mAudioFormat);
                int bufSize = AudioTrack.getMinBufferSize(mAudioRate, mAudioChannnel, mAudioFormat)*4;
                mAudioTrack = new AudioTrack(mAudioStream, mAudioRate, mAudioChannnel, mAudioFormat, bufSize, AudioTrack.MODE_STREAM);
                if (mMute)
                {
                    mute();
                }
                mAudioTrack.play();
            }
        }
    }

}
