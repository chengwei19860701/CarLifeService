package com.hsae.carlife.common;

/**
 * 相关定义
 */
public class CarLifeDef
{

    /** service包名 */
    public static final String PACKAGE_NAME = "com.hsae.carlife";

    /** service类名 */
    public static final String SERVICE_CLASS_NAME = "com.hsae.carlife.CarLifeService";

    /** 启动service的action */
    public static final String ACTION_CARLIFE_UI = "com.intent.action.CARLIFE_UI";

    /** 启动模式，绑定服务时传入，默认为空 */
    public static final String EXTRA_CONNECT_MODE = "connect_mode";
    /** 手机IP地址，绑定服务时传入，默认为空 */
    public static final String EXTRA_IP_ADDRESS = "ip_address";

    /** 手机连接模式{@link #EXTRA_CONNECT_MODE}: 空 */
    public static final int CONNECT_MODE_NULL = 0;
    /** 手机连接模式{@link #EXTRA_CONNECT_MODE}: ADB */
    public static final int CONNECT_MODE_ADB = 1;
    /** 手机连接模式{@link #EXTRA_CONNECT_MODE}: AOA */
    public static final int CONNECT_MODE_AOA = 2;
    /** 手机连接模式{@link #EXTRA_CONNECT_MODE}: EAP */
    public static final int CONNECT_MODE_EAP = 3;
    /** 手机连接模式{@link #EXTRA_CONNECT_MODE}: WIFI */
    public static final int CONNECT_MODE_WIFI = 4;

    /** 异常类型: 编码错误 */
    public static final int EXCEPTION_ENCORDER_ERROR = 1;
    /** 异常类型: 没有权限 */
    public static final int EXCEPTION_PERMISSION_DENIED = 2;
    /** 异常类型: 屏幕请求失败 */
    public static final int EXCEPTION_SCREENSHARE_REQUEST = 3;

    /** 按键: Home键 */
    public static final int KEYCODE_HOME = 0x00000001;
    /** 按键: 打电话 */
    public static final int KEYCODE_PHONE_CALL = 0x00000002;
    /** 按键: 挂断电话 */
    public static final int KEYCODE_PHONE_END = 0x00000003;
    /** 按键: 切 换 免 提 电话 和 移 动 设备电话 */
    public static final int KEYCODE_HFP = 0x00000005;
    /** 按键: 切 换 到 下 一个焦点 */
    public static final int KEYCODE_SELECTOR_NEXT = 0x00000006;
    /** 按键: 切 换 到 上 一个焦点 */
    public static final int KEYCODE_SELECTOR_PREV = 0x00000007;
    /** 按键: 切 换 到 多 媒体界面 */
    public static final int KEYCODE_MEDIA = 0x00000009;
    /** 按键: 切 换 到 导 航界面 */
    public static final int KEYCODE_NAVI = 0x0000000B;
    /** 按键: 切 换 到 上 一状态 */
    public static final int KEYCODE_BACK = 0x0000000E;
    /** 按键: 上一首 */
    public static final int KEYCODE_SEEK_SUB = 0x0000000F;
    /** 按键: 下一首 */
    public static final int KEYCODE_SEEK_ADD = 0x00000010;
    /** 按键: 静音 */
    public static final int KEYCODE_MUTE = 0x00000013;
    /** 按键: 确认键 */
    public static final int KEYCODE_OK = 0x00000014;
    /** 按键: 焦 点 区 域 左移 */
    public static final int KEYCODE_MOVE_LEFT = 0x00000015;
    /** 按键: 焦 点 区 域 右移 */
    public static final int KEYCODE_MOVE_RIGHT = 0x00000016;
    /** 按键: 焦 点 区 域 上移 */
    public static final int KEYCODE_MOVE_UP = 0x00000017;
    /** 按键: 焦 点 区 域 下移 */
    public static final int KEYCODE_MOVE_DOWN = 0x00000018;
    /** 按键: 左上移动 */
    public static final int KEYCODE_MOVE_UP_LEFT = 0x00000019;
    /** 按键: 右上移动 */
    public static final int KEYCODE_MOVE_UP_RIGHT = 0x0000001A;
    /** 按键: 左下移动 */
    public static final int KEYCODE_MOVE_DOWN_LEFT = 0x0000001B;
    /** 按键: 右下移动 */
    public static final int KEYCODE_MOVE_DOWN_RIGHT = 0x0000001C;
    /** 按键: 切 换 到 电 话界面 */
    public static final int KEYCODE_TEL = 0x0000001D;
    /** 按键: 切 换 到 主 页面 */
    public static final int KEYCODE_MAIN = 0x0000001E;
    /** 按键: 播放音乐 */
    public static final int KEYCODE_MEDIA_START = 0x0000001F;
    /** 按键: 暂停音乐 */
    public static final int KEYCODE_MEDIA_STOP = 0x00000020;
    /** 按键: 打开语音 */
    public static final int KEYCODE_VR_START = 0x00000021;
    /** 按键: 关闭语音 */
    public static final int KEYCODE_VR_STOP = 0x00000022;
    /** 按键: 数字键 0 */
    public static final int KEYCODE_NUMBER_0 = 0x00000023;
    /** 按键: 数字键 1 */
    public static final int KEYCODE_NUMBER_1 = 0x00000024;
    /** 按键: 数字键 2 */
    public static final int KEYCODE_NUMBER_2 = 0x00000025;
    /** 按键: 数字键 3 */
    public static final int KEYCODE_NUMBER_3 = 0x00000026;
    /** 按键: 数字键 4 */
    public static final int KEYCODE_NUMBER_4 = 0x00000027;
    /** 按键: 数字键 5 */
    public static final int KEYCODE_NUMBER_5 = 0x00000028;
    /** 按键: 数字键 6 */
    public static final int KEYCODE_NUMBER_6 = 0x00000029;
    /** 按键: 数字键 7 */
    public static final int KEYCODE_NUMBER_7 = 0x0000002A;
    /** 按键: 数字键 8 */
    public static final int KEYCODE_NUMBER_8 = 0x0000002B;
    /** 按键: 数字键 9 */
    public static final int KEYCODE_NUMBER_9 = 0x0000002C;
    /** 按键: 字符键* */
    public static final int KEYCODE_NUMBER_10 = 0x0000002D;
    /** 按键: 字符键# */
    public static final int KEYCODE_NUMBER_11 = 0x0000002E;
    /** 按键: 删除键 */
    public static final int KEYCODE_NUMBER_DEL = 0x0000002F;
    /** 按键: 清除键 */
    public static final int KEYCODE_NUMBER_CLEAR = 0x00000030;

    /** 语音类型: 多媒体 */
    public static final int AUDIO_SOURCE_MEDIA = 0;
    /** 语音类型: 导航提示音 */
    public static final int AUDIO_SOURCE_TTS = 1;
    /** 语音类型: 语音识别提示音 */
    public static final int AUDIO_SOURCE_VR = 2;

    /** 语音请求结果: 拒绝 */
    public static final int AUDIO_FOCUS_REQUEST_REFUSED = 0;
    /** 语音请求结果: 成功 */
    public static final int AUDIO_FOCUS_REQUEST_SUCCESS = 1;
    /** 语音请求结果: 错误 */
    public static final int AUDIO_FOCUS_REQUEST_ERROR   = 2;

    /** 状态模块: 手机 */
    public static final int MODULE_PHONE = 1;
    /** 状态模块: 导航 */
    public static final int MODULE_NAVI = 2;
    /** 状态模块: 音乐 */
    public static final int MODULE_MUSIC = 3;
    /** 状态模块: 语音识别 */
    public static final int MODULE_VR = 4;
    /** 状态模块: 连接 */
    public static final int MODULE_CONNECT = 5;
    /** 状态模块: 麦克风 */
    public static final int MODULE_MIC = 6;

    /** 电话模块状态{@link #MODULE_PHONE}-- 关闭 */
    public static final int PHONE_IDLE = 0;
    /** 电话模块状态{@link #MODULE_PHONE}: 来电 */
    public static final int PHONE_INCOMING = 1;
    /** 电话模块状态{@link #MODULE_PHONE}: 去电 */
    public static final int PHONE_OUTING = 2;
    /** 电话模块状态{@link #MODULE_PHONE}: 通话中 */
    public static final int PHONE_CONNECTED = 3;

    /** 导航模块状态{@link #MODULE_NAVI}: 关闭 */
    public static final int NAVI_IDLE = 0;
    /** 导航模块状态{@link #MODULE_NAVI}: 开始 */
    public static final int NAVI_START = 1;
    /** 导航模块状态{@link #MODULE_NAVI}: 停止 */
    public static final int NAVI_STOP = 2;

    /** 音乐模块状态{@link #MODULE_MUSIC}: 关闭 */
    public static final int MUSIC_IDLE = 0;
    /** 音乐模块状态{@link #MODULE_MUSIC}: 运行 */
    public static final int MUSIC_RUNNING = 1;

    /** 语音识别模块状态{@link #MODULE_VR}: 关闭 */
    public static final int VR_RECORD_IDLE = 0;
    /** 语音识别模块状态{@link #MODULE_VR}: 运行 */
    public static final int VR_RECORD_RUNNING = 1;
    /** 语音识别模块状态 {@link #MODULE_VR}-- 不支持 */
    public static final int VR_NOT_SUPPORT = 2;

    /** 连接模块状态{@link #MODULE_CONNECT}: ADB */
    public static final int CONNECT_ADB = 1;
    /** 连接模块状态{@link #MODULE_CONNECT}: AOA */
    public static final int CONNECT_AOA = 2;
    /** 连接模块状态{@link #MODULE_CONNECT}: NCM Android */
    public static final int CONNECT_NCM_ANDROID = 3;
    /** 连接模块状态{@link #MODULE_CONNECT}: NCM IOS */
    public static final int CONNECT_NCM_IOS = 4;
    /** 连接模块状态{@link #MODULE_CONNECT}: WIFI */
    public static final int CONNECT_WIFI = 5;

    /** mic模块状态{@link #MODULE_MIC}: 车机mic */
    public static final int MIC_MODE_VEHICLE = 0;
    /** mic模块状态 {@link #MODULE_MIC}-- 手机mic */
    public static final int MIC_MODE_MOBILE = 1;
    /** mic模块状态{@link #MODULE_MIC}: 不支持mic */
    public static final int MIC_MODE_NOT_SUPPORT = 2;

    /** mic工作状态: 启动 */
    public static final int MIC_STATE_START = 0;
    /** mic工作状态: 停止 */
    public static final int MIC_STATE_STOP = 1;

    /** 音量最大值 */
    public static final int AUDIO_MAX_VOLUME_LEVEL = 10;

    /** mic 录音类型: 唤醒 */
    public static final int RECORD_WAKEUP = 0;
    /** mic 录音类型: 语音识别 */
    public static final int RECORD_RECOG = 1;

    /** 蓝牙电话命令: 拨打一个电话 */
    public static final int PLACE_OUTGOING_CALL = 1;
    /** 蓝牙电话命令: 挂断电话 */
    public static final int TERMINATE_CALL =2;
    /** 蓝牙电话命令: 接听电话 */
    public static final int ANSWER_CALL = 3;
    /** 蓝牙电话命令: 拒绝来电 */
    public static final int REJECT_CALL = 4;
    /** 蓝牙电话命令: 发送DTMF编码 */
    public static final int SEND_DTMF = 5;
    /** 蓝牙电话命令: 静音本地MIC */
    public static final int MUTE_MIC = 6;
    /** 蓝牙电话命令: 取消对本地MIC的静音 */
    public static final int UNMUTE_MIC = 7;

    /** 蓝牙电话的状态: 指明新的来电到来 */
    public static final int INCOMMING_CALL = 1;
    /** 蓝牙电话的状态: 指明一个新的呼叫成功 */
    public static final int OUTGOING_CALL = 2;
    /** 蓝牙电话的状态: 指明电话接通 */
    public static final int CALL_ACTIVE = 3;
    /** 蓝牙电话的状态: 指明通话结束 */
    public static final int CALL_INACTIVE = 4;
    /** 蓝牙电话的状态: 指明有三方通话正在进行 */
    public static final int MULTICALL_ACTIVE = 5;

    /** 手机的电话状态: 指明手机没有打电话*/
    public static final int CARLIFE_TEL_IDLE = 0;
    /** 手机的电话状态: 指明手机新的来电到来*/
    public static final int CARLIFE_TEL_INCOMING = 1;
    /** 手机的电话状态: 指明手机正在呼叫电话*/
    public static final int CARLIFE_TEL_OUTGOING = 2;
    /** 手机的电话状态: 指明手机正在通话中*/
    public static final int CARLIFE_TEL_INCALLING = 3;

    /**
     * 通过EXTRA_FEATURE_KEY设int类型值，可以使能相关的配置项，其值的 每一比特位表示一个配置项，
     * 如果相关的比特位为：1，表示配置该项； 为0：不配置该项
     * 	 8	            7	                   6	           5	            4		                3		          2            1
     * ----------------------------------------------------------------------------------------------------------------------------------------
     * |    | AUDIO_TRANSMISSION_MODE | MEDIA_SAMPLE_RATE | FOCUS_UI | BLUETOOTH_INTERNAL_UI | BLUETOOTH_AUTO_PAIR | VOICE_WAKEUP | VOICE_MIC |
     * -----------------------------------------------------------------------------------------------------------------------------------------
     *
     * 通过EXTRA_FEATURE_VALUE设int类型的值，其值可以表示每一位配置项的值
     * 1~2 bit位表示VOICE_MIC配置项的值
     * 3   bit位表示VOICE_WAKEUP配置项的值
     * 4   bit位表示BLUETOOTH_AUTO_PAIR配置项的值
     * 5   bit位表示BLUETOOTH_INTERNAL_UI配置项的值
     * 6   bit位表示FOCUS_UI配置项的值
     * 7   bit位表示MEDIA_SAMPLE_RATE配置项的值
     * 8   bit位表示AUDIO_TRANSMISSION_MODE配置项的值
     * -----------------------------------------------------------------------------------------
     * |    8     |    7      |    6     |     5     |     4    |    3    |     2     |    1   |
     * -----------------------------------------------------------------------------------------
     *
     */
    public static final String EXTRA_FEATURE_KEY = "feature_key";
    public static final String EXTRA_FEATURE_VALUE = "feature_value";

    public static final int KEY_VOICE_MIC = 0x01;
    public static final int KEY_VOICE_WAKEUP = 0x02;
    public static final int KEY_BLUETOOTH_AUTO_PAIR = 0x04;
    public static final int KEY_BLUETOOTH_INTERNAL_UI = 0x08;
    public static final int KEY_FOCUS_UI = 0x10;
    public static final int KEY_MEDIA_SAMPLE_RATE = 0x20;
    public static final int KEY_AUDIO_TRANSMISSION_MODE = 0x40;

    public static final int VALUE_VOICE_MIC_USE_VEHICLE_MIC = 0x00;
    public static final int VALUE_VOICE_MIC_USE_MOBILE_MIC = 0x01;
    public static final int VALUE_VOICE_MIC_NOT_SUPPORTED = 0x02;

    public static final int VALUE_VOICE_WAKEUP_ENABLE = 1<<2;
    public static final int VALUE_VOICE_WAKEUP_DISABLE = 0<<2;

    public static final int VALUE_BLUETOOTH_AUTO_PAIR_ENABLE = 1<<3;
    public static final int VALUE_BLUETOOTH_AUTO_PAIR_DISABLE = 0<<3;

    public static final int VALUE_BLUETOOTH_INTERNAL_UI_ENABLE = 1<<4;
    public static final int VALUE_BLUETOOTH_INTERNAL_UI_DISABLE = 0<<4;

    public static final int VALUE_FOCUS_UI_ENABLE = 1<<5;
    public static final int VALUE_FOCUS_UI_DISABLE = 0<<5;

    public static final int VALUE_MEDIA_SAMPLE_RATE_ORIGINAL = 0<<6;
    public static final int VALUE_MEDIA_SAMPLE_RATE_CUSTOMIZED_48K = 1<<6;

    public static final int VALUE_AUDIO_TRANSMISSION_MODE_INDEPENDENT_CHANNEL = 0<<7;
    public static final int VALUE_AUDIO_TRANSMISSION_MODE_BLUETOOTH = 1<<7;
}
