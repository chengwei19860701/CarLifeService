package com.hsae.carlife;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.app.Application;
import android.content.Context;

public class CarLifeApp extends Application
{

    private static final String BAIDU_FOLDER = "baidu";
    private static final String IPOD_FOLDER = "carlife";

    @Override
    public void onCreate()
    {
        super.onCreate();

        if (isDefaultProcess())
        {
            copyAssetFiles(BAIDU_FOLDER);
        }
        if (isDefaultProcess())
        {
            copyAssetFiles(IPOD_FOLDER);
        }
    }

    private boolean isDefaultProcess()
    {
        ActivityManager am = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        List<RunningAppProcessInfo> processes = am.getRunningAppProcesses();
        if (processes == null)
        {
            return false;
        }
        for (RunningAppProcessInfo process : processes)
        {
            if (process.pid == android.os.Process.myPid() && process.processName.equals(getPackageName()))
            {
                return true;
            }
        }
        return false;
    }

    private void copyAssetFiles(String folder)
    {
        String destPath = getFilesDir().getAbsolutePath() + "/" + folder;
        File destDir = new File(destPath);
        if (!destDir.exists())
        {
            destDir.mkdirs();
        }
        try
        {
            String[] srcFiles = getAssets().list(folder);
            for (int i = 0; i < srcFiles.length; i++)
            {
                String path = getFilesDir().getAbsolutePath() + "/" + folder + "/" + srcFiles[i];
                File file = new File(path);
                if (file.exists())
                {
                    continue;
                }
                InputStream in = getAssets().open(folder + "/" + srcFiles[i]);
                OutputStream out = new FileOutputStream(path);
                byte[] buf = new byte[1024];
                int len;
                while ((len = in.read(buf)) > 0)
                {
                    out.write(buf, 0, len);
                }
                in.close();
                out.close();
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

}
