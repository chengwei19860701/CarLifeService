package com.hsae.carlife;

import java.nio.ByteBuffer;

import android.media.MediaCodec;
import android.media.MediaCodec.BufferInfo;
import android.media.MediaFormat;
import android.util.Log;
import android.view.Surface;

public class CarLifeDisplay
{
    private static final String TAG = "CarLifeDisplay";
    private static final int CARLIFE_DEFAULT_VIDEO_RATE = 30;

    private final int mRate;
    private boolean mCodecReady = false;
    private MediaCodec m_H264Decoder;
    private ByteBuffer[] m_InputBuffer;
    String m_strSyncSignal = "false";
    BufferInfo m_OutBufferInfo;
    MediaFormat m_VideoFormat;
    boolean m_bRenderThreadRunnable = false;
    Thread m_RendorThread;

    public CarLifeDisplay()
    {
        this(CARLIFE_DEFAULT_VIDEO_RATE);
    }

    public CarLifeDisplay(int rate)
    {
        Log.i(TAG, "CarLifeDisplay, rate = " + rate);

        mRate = rate;
    }

    public int getRate()
    {
        return mRate;
    }

    public void write(byte[] data, int len)
    {
        if (mCodecReady)
        {
            if(m_H264Decoder == null)
            {
                Log.w(TAG, "m_H264Decoder = null!!!!!!!!!!!!!!!!!!");
                return;
            }
            try
            {
                int iInputBufferIndex = 0;
                iInputBufferIndex = m_H264Decoder.dequeueInputBuffer(1000 * 1000);
                if (iInputBufferIndex >= 0)
                {
                    m_InputBuffer[iInputBufferIndex].clear();
                    m_InputBuffer[iInputBufferIndex].put(data, 0, len);

                    m_H264Decoder.queueInputBuffer(iInputBufferIndex, 0, len, 0, MediaCodec.BUFFER_FLAG_SYNC_FRAME);
                    synchronized (m_strSyncSignal)
                    {
                        m_strSyncSignal.notify();
                    }
                }
                else
                {
                    Log.w(TAG, "write failed: dequeueInputBuffer timeout!\n");
                }
            }
            catch (IllegalStateException e)
            {
                e.printStackTrace();
            }
        }
    }

    public void show(Surface surface, int width, int height)
    {
        m_OutBufferInfo = new BufferInfo();
        m_VideoFormat = MediaFormat.createVideoFormat("video/avc", width, height);
        m_H264Decoder = MediaCodec.createDecoderByType("video/avc");
        m_H264Decoder.configure(m_VideoFormat, surface, null, 0);
        m_H264Decoder.start();
        m_InputBuffer = m_H264Decoder.getInputBuffers();

        m_bRenderThreadRunnable = true;

        m_RendorThread = new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                int iOutputBufferIndex;
                while (m_bRenderThreadRunnable)
                {
                    synchronized(m_strSyncSignal)
                    {
                        try
                        {
                            m_strSyncSignal.wait(1 * 1000);
                        }
                        catch (InterruptedException e)
                        {
                            e.printStackTrace();
                        }
                    }
                    try
                    {
                        iOutputBufferIndex = m_H264Decoder.dequeueOutputBuffer(m_OutBufferInfo, 30 * 1000);
                        if (iOutputBufferIndex >= 0)
                        {
                            m_H264Decoder.releaseOutputBuffer(iOutputBufferIndex, true);
                        }
                        else if (iOutputBufferIndex == MediaCodec.INFO_OUTPUT_BUFFERS_CHANGED) { }
                        else if (iOutputBufferIndex == MediaCodec.INFO_OUTPUT_FORMAT_CHANGED) { }
                        else {}
                    }
                    catch (IllegalStateException e)
                    {
                        e.printStackTrace();
                    }
                }
            }
        });

        m_RendorThread.start();
        mCodecReady = true;
    }

    public void hide()
    {
        m_bRenderThreadRunnable = false;
        mCodecReady = false;
        synchronized (m_strSyncSignal)
        {
            m_strSyncSignal.notify();
        }
        try
        {
        	if(m_RendorThread != null){
        		m_RendorThread.join();
        	}
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        if(m_H264Decoder != null){
        	m_H264Decoder.stop();
        	m_H264Decoder.release();
        	m_H264Decoder = null;
        }
    }

}
