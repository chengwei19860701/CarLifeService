package com.hsae.carlife.aidl;

import com.hsae.carlife.aidl.ICarLifeUICallback;

/**
 * CarLife服务主调接口，用于应用处理相关CarLife状态
 */
interface ICarLifeUICommand
{

    /**
     * 注册CarLife回调，用于通知状态改变、资源申请等
     */
    void registerUICallback(ICarLifeUICallback callback);
    
    /**
     * 注销CarLife回调
     */
    void unregisterUICallback(ICarLifeUICallback callback);
    
    /**
     * 连接CarLife
     * @param channel 百度提供的渠道号
     */
    void connect(String channel);
    
    /**
     * 断开CarLife
     */
    void disconnect();
    
    /**
     * 显示手机镜像
     * @param surface 显示的缓冲区域
     * @param width 显示区域的宽度
     * @param height 显示区域的高度
     */
    void show(in Surface surface, int width, int height);
    
    /**
     * 隐藏手机镜像
     */
    void hide();
    
    /**
     * 启动手机端CarLife应用
     */
    void startapp();
    
    /**
     * 静音指定音源
     * @param source CarLife音源，包括Media/TTS/VR
     */
    void mute(int source);
    
    /**
     * 取消静音指定音源
     * @param source CarLife音源，包括Media/TTS/VR
     */
    void unmute(int source);
    
    /**
     * 暂停指定音源
     * @param source CarLife音源，包括Media/TTS/VR
     */
    void pause(int source);
    
    /**
     * 播放指定音源
     * @param source CarLife音源，包括Media/TTS/VR
     */
    void resume(int source);
    
    /**
     * 设置指定音源的音量
     * @param source CarLife音源，包括Media/TTS/VR
     * @param volume 音量等级，0 ~ 10
     */
    void setVolume(int source, int volume);
    
    /**
     * 获取指定音源的音量
     * @param source CarLife音源，包括Media/TTS/VR
     * @return 音量等级，0 ~ 10
     */
    int getVolume(int source);
    
    /**
     * 触屏事件
     */
    void onTouchEvent(in MotionEvent event);
    
    /**
     * 按键事件
     * @param keyCode 参考 {@link com.hsae.carlife.common.CarLifeDef}
     */
    void onKeyEvent(int keyCode);
    
    /**
     * 设置mic模式，用于语音识别
     * @param mode mic模式，参考 {@link com.hsae.carlife.common.CarLifeDef}
     */
    void setMicMode(int mode);
    
    /**
     * 设置mic状态
     * @param state OFF/ON，参考 {@link com.hsae.carlife.common.CarLifeDef}
     */
    void setMicState(int state);
    
    /**
     * 设置audio音源的流类型
     * @param source CarLife音源
     * @param stream 流类型， 例如AudioManager.STREAM_MUSIC, AudioManager.STREAM_HSAE
     */
    void setAudioStream(int source, int stream);
 
    /**
     * 发送蓝牙标定请求用于初始化蓝牙自动匹配
     * @param address 本地蓝牙地址
     * @param name 本地蓝牙名称
     */
    void startBtIdentifyReq(String address, String name);
    
    /**
     * 发送蓝牙Hfp的连接状态
     * @param status HFP状态， 0: 已断开, 1: 已连接
     */
    void btHfpConnectStatus(int status);
    
    /**
     * 用来反馈执行命令的结果
     * @param status 指明指定命令执行的结果。0：命令执行失败；1：命令执行成功
     * @param command 指明是执行的哪条命令
     * @param dtmfCode 如果是发送DTMF code成功，需要反馈是发送的哪一个DTMF code, 包括数字0-9，*， #
     */
    void sendBtHfpResponse(int status, int command, int dtmfCode);
    
    /**
     * 用来告诉手机蓝牙连接后的电话状态
     * @param state 指明电话的状态， 具体的电话状态如下表
     * @param phoneNum 电话号码
     * @param phoneName 电话名称
     * @param address 当收到电话状态消息时，需要指明该电话状态来自哪个蓝牙设备地址，一般指车机的蓝牙地址
     */
    void sendBtHfpIndication(int state, String phoneNum, String phoneName, String address);
    
    /**
     * 反馈指定状态给手机， 目前主要用来反馈车机MIC是否Mute的状态
     * @param status 指明指定命令的执行结果
     * @param type 指明反馈的是哪一种状态
     */
    void sendBtHfpStatusRespone(int status, int type); 
    
    /**
     * 主要用来杀掉RelayServer和退出CarLifeService
     */
    void killProcess();
}