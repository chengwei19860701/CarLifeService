package com.hsae.carlife.aidl;

/**
 * CarLife服务回调，用于状态通知，权限申请。
 */
interface ICarLifeUICallback
{

    /**
     * 连接进度
     * @param progress 进度， 0 ~ 100
     */
    void onConnectProgress(int progress);
    
    /**
     * 连接成功
     */
    void onConnectSuccess();
    
    /**
     * 连接失败
     */
    void onConnectFailure();
    
    /**
     * 连接异常
     * @param exception 异常类型，参考 {@link com.hsae.carlife.common.CarLifeDef}
     */
    void onConnectException(int exception);
    
    /**
     * 手机端不支持或未打开ADB调试
     */
    void onAdbDisabled();
    
    /**
     * 手机端未安装应用
     */
    void onAppNotInstalled();
    
    /**
     * 手机端应用前台显示
     */
    void onAppGoForeground();
    
    /**
     * 手机端应用后台显示
     */
    void onAppGoBackground();
    
    /**
     * 接收手机发送的回到车机主界面消息
     */
    void onGoToDesktop();
    
    /**
     * 数据接收错误，连接断开
     */
    void onDataReceiveError();
    
    /**
     * iPhone 设备断开
     */
    void onIosDeviceDetached();

    /**
     * 模块状态变化
     * @param module 相关模块，参考 {@link com.hsae.carlife.common.CarLifeDef}
     * @param state 相关状态，参考 {@link com.hsae.carlife.common.CarLifeDef}
     */
    void onModuleStateChanged(int module, int state);
    
    /**
     * 申请Audio权限
     * @param source 音源类型，包括Media/TTS/VR
     * @return 申请到的具体audio stream类型，-1表示申请失败，不可播放
     */
    int requestAudioStream(int source);
    
    /**
     * 申请MIC权限
     * @param type 录音类型 参考 {@link com.hsae.carlife.common.CarLifeDef}
     */
    boolean reqMicPermission(int type);

    /**
     * 导航音结束
     */
    void onTTSStop();
    
    /**
     * 语音识别提示音结束
     */
    void onVRStop();
    
    /**
     * 车机和手机标定结果
     * @param status 状态, 0表示标定为1, 即连接*****
     */
    void btIdentifyResultIndCb(int status, String address);
    
    /**
     * 蓝牙Feature配置完成
     * @param result 0:成功 负值 :失败
     */
    void btFeatureConfigureFinish(int result);
    
    /**
     * 通知应用开始自动匹配的流程
     * @param ostype 手机系统类型，0：Android 1：iOS
     * @param mdaddress 手机的蓝牙的地址
     */
    int startBtAutoPairRequestCb(int ostype, String mdaddress);
   
    /**
     * 该消息由手机发送到车机，用来请求 车机通过自身的蓝牙HFP协议来完成电话有关的操作。
     * @param command 指明是哪一个操作命令
     * @param phoneNum 电话号码
     * @param dtmfCode DTMF Code/command 为SEND_DTMF有效
     */
    void btHfpRequestCb(int command, String phoneNum, int dtmfCode);
   
    /**
     * 该请求从手机发送到车机， 用来获取指定的状态，目前主要获取车机端MIC是否mute的状态
     *@param type 指定获取哪一种状态
     */
    void btHfpStatusRequestCb(int type);
    
    /**
     * 该请求从手机发送到车机， 用来获取手机端的电话状态
     *@param state 电话状态，0：CARLIFE_TEL_IDLE, 1：CARLIFE_TEL_INCOMING,
     *2：CARLIFE_TEL_OUTGOING, 3：CARLIFE_TEL_INCALLING
     */
     void ontelState(int state);
     
      /**
     * 该请求从手机发送到车机， 用来获取手机端的OS的信息
     *@param os 手机系统版本
     *@param release 如果OS是Android， 则表示为Android 版本信息， 如果OS是Iphone 则为空
     *@param sdk 如果OS是Android， 则表示为Android SDK信息， 如果OS是Iphone 则为0
     */
     void onMdOsNotSupport(String os, String release, int sdk);
}