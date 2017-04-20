/*
 * This file is auto-generated.  DO NOT MODIFY.
 * Original file: E:\\CarLifeSource\\Carlife\\Release\\BaseLine\\CarLife-Android\\CarLifeService\\src\\com\\hsae\\carlife\\aidl\\ICarLifeUICommand.aidl
 */
package com.hsae.carlife.aidl;
/**
 * CarLife服务主调接口，用于应用处理相关CarLife状态
 */
public interface ICarLifeUICommand extends android.os.IInterface
{
/** Local-side IPC implementation stub class. */
public static abstract class Stub extends android.os.Binder implements com.hsae.carlife.aidl.ICarLifeUICommand
{
private static final java.lang.String DESCRIPTOR = "com.hsae.carlife.aidl.ICarLifeUICommand";
/** Construct the stub at attach it to the interface. */
public Stub()
{
this.attachInterface(this, DESCRIPTOR);
}
/**
 * Cast an IBinder object into an com.hsae.carlife.aidl.ICarLifeUICommand interface,
 * generating a proxy if needed.
 */
public static com.hsae.carlife.aidl.ICarLifeUICommand asInterface(android.os.IBinder obj)
{
if ((obj==null)) {
return null;
}
android.os.IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
if (((iin!=null)&&(iin instanceof com.hsae.carlife.aidl.ICarLifeUICommand))) {
return ((com.hsae.carlife.aidl.ICarLifeUICommand)iin);
}
return new com.hsae.carlife.aidl.ICarLifeUICommand.Stub.Proxy(obj);
}
@Override public android.os.IBinder asBinder()
{
return this;
}
@Override public boolean onTransact(int code, android.os.Parcel data, android.os.Parcel reply, int flags) throws android.os.RemoteException
{
switch (code)
{
case INTERFACE_TRANSACTION:
{
reply.writeString(DESCRIPTOR);
return true;
}
case TRANSACTION_registerUICallback:
{
data.enforceInterface(DESCRIPTOR);
com.hsae.carlife.aidl.ICarLifeUICallback _arg0;
_arg0 = com.hsae.carlife.aidl.ICarLifeUICallback.Stub.asInterface(data.readStrongBinder());
this.registerUICallback(_arg0);
reply.writeNoException();
return true;
}
case TRANSACTION_unregisterUICallback:
{
data.enforceInterface(DESCRIPTOR);
com.hsae.carlife.aidl.ICarLifeUICallback _arg0;
_arg0 = com.hsae.carlife.aidl.ICarLifeUICallback.Stub.asInterface(data.readStrongBinder());
this.unregisterUICallback(_arg0);
reply.writeNoException();
return true;
}
case TRANSACTION_connect:
{
data.enforceInterface(DESCRIPTOR);
java.lang.String _arg0;
_arg0 = data.readString();
this.connect(_arg0);
reply.writeNoException();
return true;
}
case TRANSACTION_disconnect:
{
data.enforceInterface(DESCRIPTOR);
this.disconnect();
reply.writeNoException();
return true;
}
case TRANSACTION_show:
{
data.enforceInterface(DESCRIPTOR);
android.view.Surface _arg0;
if ((0!=data.readInt())) {
_arg0 = android.view.Surface.CREATOR.createFromParcel(data);
}
else {
_arg0 = null;
}
int _arg1;
_arg1 = data.readInt();
int _arg2;
_arg2 = data.readInt();
this.show(_arg0, _arg1, _arg2);
reply.writeNoException();
return true;
}
case TRANSACTION_hide:
{
data.enforceInterface(DESCRIPTOR);
this.hide();
reply.writeNoException();
return true;
}
case TRANSACTION_startapp:
{
data.enforceInterface(DESCRIPTOR);
this.startapp();
reply.writeNoException();
return true;
}
case TRANSACTION_mute:
{
data.enforceInterface(DESCRIPTOR);
int _arg0;
_arg0 = data.readInt();
this.mute(_arg0);
reply.writeNoException();
return true;
}
case TRANSACTION_unmute:
{
data.enforceInterface(DESCRIPTOR);
int _arg0;
_arg0 = data.readInt();
this.unmute(_arg0);
reply.writeNoException();
return true;
}
case TRANSACTION_pause:
{
data.enforceInterface(DESCRIPTOR);
int _arg0;
_arg0 = data.readInt();
this.pause(_arg0);
reply.writeNoException();
return true;
}
case TRANSACTION_resume:
{
data.enforceInterface(DESCRIPTOR);
int _arg0;
_arg0 = data.readInt();
this.resume(_arg0);
reply.writeNoException();
return true;
}
case TRANSACTION_setVolume:
{
data.enforceInterface(DESCRIPTOR);
int _arg0;
_arg0 = data.readInt();
int _arg1;
_arg1 = data.readInt();
this.setVolume(_arg0, _arg1);
reply.writeNoException();
return true;
}
case TRANSACTION_getVolume:
{
data.enforceInterface(DESCRIPTOR);
int _arg0;
_arg0 = data.readInt();
int _result = this.getVolume(_arg0);
reply.writeNoException();
reply.writeInt(_result);
return true;
}
case TRANSACTION_onTouchEvent:
{
data.enforceInterface(DESCRIPTOR);
android.view.MotionEvent _arg0;
if ((0!=data.readInt())) {
_arg0 = android.view.MotionEvent.CREATOR.createFromParcel(data);
}
else {
_arg0 = null;
}
this.onTouchEvent(_arg0);
reply.writeNoException();
return true;
}
case TRANSACTION_onKeyEvent:
{
data.enforceInterface(DESCRIPTOR);
int _arg0;
_arg0 = data.readInt();
this.onKeyEvent(_arg0);
reply.writeNoException();
return true;
}
case TRANSACTION_setMicMode:
{
data.enforceInterface(DESCRIPTOR);
int _arg0;
_arg0 = data.readInt();
this.setMicMode(_arg0);
reply.writeNoException();
return true;
}
case TRANSACTION_setMicState:
{
data.enforceInterface(DESCRIPTOR);
int _arg0;
_arg0 = data.readInt();
this.setMicState(_arg0);
reply.writeNoException();
return true;
}
case TRANSACTION_setAudioStream:
{
data.enforceInterface(DESCRIPTOR);
int _arg0;
_arg0 = data.readInt();
int _arg1;
_arg1 = data.readInt();
this.setAudioStream(_arg0, _arg1);
reply.writeNoException();
return true;
}
case TRANSACTION_startBtIdentifyReq:
{
data.enforceInterface(DESCRIPTOR);
java.lang.String _arg0;
_arg0 = data.readString();
java.lang.String _arg1;
_arg1 = data.readString();
this.startBtIdentifyReq(_arg0, _arg1);
reply.writeNoException();
return true;
}
case TRANSACTION_btHfpConnectStatus:
{
data.enforceInterface(DESCRIPTOR);
int _arg0;
_arg0 = data.readInt();
this.btHfpConnectStatus(_arg0);
reply.writeNoException();
return true;
}
case TRANSACTION_sendBtHfpResponse:
{
data.enforceInterface(DESCRIPTOR);
int _arg0;
_arg0 = data.readInt();
int _arg1;
_arg1 = data.readInt();
int _arg2;
_arg2 = data.readInt();
this.sendBtHfpResponse(_arg0, _arg1, _arg2);
reply.writeNoException();
return true;
}
case TRANSACTION_sendBtHfpIndication:
{
data.enforceInterface(DESCRIPTOR);
int _arg0;
_arg0 = data.readInt();
java.lang.String _arg1;
_arg1 = data.readString();
java.lang.String _arg2;
_arg2 = data.readString();
java.lang.String _arg3;
_arg3 = data.readString();
this.sendBtHfpIndication(_arg0, _arg1, _arg2, _arg3);
reply.writeNoException();
return true;
}
case TRANSACTION_sendBtHfpStatusRespone:
{
data.enforceInterface(DESCRIPTOR);
int _arg0;
_arg0 = data.readInt();
int _arg1;
_arg1 = data.readInt();
this.sendBtHfpStatusRespone(_arg0, _arg1);
reply.writeNoException();
return true;
}
case TRANSACTION_killProcess:
{
data.enforceInterface(DESCRIPTOR);
this.killProcess();
reply.writeNoException();
return true;
}
}
return super.onTransact(code, data, reply, flags);
}
private static class Proxy implements com.hsae.carlife.aidl.ICarLifeUICommand
{
private android.os.IBinder mRemote;
Proxy(android.os.IBinder remote)
{
mRemote = remote;
}
@Override public android.os.IBinder asBinder()
{
return mRemote;
}
public java.lang.String getInterfaceDescriptor()
{
return DESCRIPTOR;
}
/**
     * 注册CarLife回调，用于通知状态改变、资源申请等
     */
@Override public void registerUICallback(com.hsae.carlife.aidl.ICarLifeUICallback callback) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeStrongBinder((((callback!=null))?(callback.asBinder()):(null)));
mRemote.transact(Stub.TRANSACTION_registerUICallback, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
/**
     * 注销CarLife回调
     */
@Override public void unregisterUICallback(com.hsae.carlife.aidl.ICarLifeUICallback callback) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeStrongBinder((((callback!=null))?(callback.asBinder()):(null)));
mRemote.transact(Stub.TRANSACTION_unregisterUICallback, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
/**
     * 连接CarLife
     * @param channel 百度提供的渠道号
     */
@Override public void connect(java.lang.String channel) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeString(channel);
mRemote.transact(Stub.TRANSACTION_connect, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
/**
     * 断开CarLife
     */
@Override public void disconnect() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_disconnect, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
/**
     * 显示手机镜像
     * @param surface 显示的缓冲区域
     * @param width 显示区域的宽度
     * @param height 显示区域的高度
     */
@Override public void show(android.view.Surface surface, int width, int height) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
if ((surface!=null)) {
_data.writeInt(1);
surface.writeToParcel(_data, 0);
}
else {
_data.writeInt(0);
}
_data.writeInt(width);
_data.writeInt(height);
mRemote.transact(Stub.TRANSACTION_show, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
/**
     * 隐藏手机镜像
     */
@Override public void hide() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_hide, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
/**
     * 启动手机端CarLife应用
     */
@Override public void startapp() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_startapp, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
/**
     * 静音指定音源
     * @param source CarLife音源，包括Media/TTS/VR
     */
@Override public void mute(int source) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeInt(source);
mRemote.transact(Stub.TRANSACTION_mute, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
/**
     * 取消静音指定音源
     * @param source CarLife音源，包括Media/TTS/VR
     */
@Override public void unmute(int source) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeInt(source);
mRemote.transact(Stub.TRANSACTION_unmute, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
/**
     * 暂停指定音源
     * @param source CarLife音源，包括Media/TTS/VR
     */
@Override public void pause(int source) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeInt(source);
mRemote.transact(Stub.TRANSACTION_pause, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
/**
     * 播放指定音源
     * @param source CarLife音源，包括Media/TTS/VR
     */
@Override public void resume(int source) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeInt(source);
mRemote.transact(Stub.TRANSACTION_resume, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
/**
     * 设置指定音源的音量
     * @param source CarLife音源，包括Media/TTS/VR
     * @param volume 音量等级，0 ~ 10
     */
@Override public void setVolume(int source, int volume) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeInt(source);
_data.writeInt(volume);
mRemote.transact(Stub.TRANSACTION_setVolume, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
/**
     * 获取指定音源的音量
     * @param source CarLife音源，包括Media/TTS/VR
     * @return 音量等级，0 ~ 10
     */
@Override public int getVolume(int source) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
int _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeInt(source);
mRemote.transact(Stub.TRANSACTION_getVolume, _data, _reply, 0);
_reply.readException();
_result = _reply.readInt();
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
/**
     * 触屏事件
     */
@Override public void onTouchEvent(android.view.MotionEvent event) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
if ((event!=null)) {
_data.writeInt(1);
event.writeToParcel(_data, 0);
}
else {
_data.writeInt(0);
}
mRemote.transact(Stub.TRANSACTION_onTouchEvent, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
/**
     * 按键事件
     * @param keyCode 参考 {@link com.hsae.carlife.common.CarLifeDef}
     */
@Override public void onKeyEvent(int keyCode) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeInt(keyCode);
mRemote.transact(Stub.TRANSACTION_onKeyEvent, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
/**
     * 设置mic模式，用于语音识别
     * @param mode mic模式，参考 {@link com.hsae.carlife.common.CarLifeDef}
     */
@Override public void setMicMode(int mode) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeInt(mode);
mRemote.transact(Stub.TRANSACTION_setMicMode, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
/**
     * 设置mic状态
     * @param state OFF/ON，参考 {@link com.hsae.carlife.common.CarLifeDef}
     */
@Override public void setMicState(int state) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeInt(state);
mRemote.transact(Stub.TRANSACTION_setMicState, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
/**
     * 设置audio音源的流类型
     * @param source CarLife音源
     * @param stream 流类型， 例如AudioManager.STREAM_MUSIC, AudioManager.STREAM_HSAE
     */
@Override public void setAudioStream(int source, int stream) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeInt(source);
_data.writeInt(stream);
mRemote.transact(Stub.TRANSACTION_setAudioStream, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
/**
     * 发送蓝牙标定请求用于初始化蓝牙自动匹配
     * @param address 本地蓝牙地址
     * @param name 本地蓝牙名称
     */
@Override public void startBtIdentifyReq(java.lang.String address, java.lang.String name) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeString(address);
_data.writeString(name);
mRemote.transact(Stub.TRANSACTION_startBtIdentifyReq, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
/**
     * 发送蓝牙Hfp的连接状态
     * @param status HFP状态， 0: 已断开, 1: 已连接
     */
@Override public void btHfpConnectStatus(int status) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeInt(status);
mRemote.transact(Stub.TRANSACTION_btHfpConnectStatus, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
/**
     * 用来反馈执行命令的结果
     * @param status 指明指定命令执行的结果。0：命令执行失败；1：命令执行成功
     * @param command 指明是执行的哪条命令
     * @param dtmfCode 如果是发送DTMF code成功，需要反馈是发送的哪一个DTMF code, 包括数字0-9，*， #
     */
@Override public void sendBtHfpResponse(int status, int command, int dtmfCode) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeInt(status);
_data.writeInt(command);
_data.writeInt(dtmfCode);
mRemote.transact(Stub.TRANSACTION_sendBtHfpResponse, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
/**
     * 用来告诉手机蓝牙连接后的电话状态
     * @param state 指明电话的状态， 具体的电话状态如下表
     * @param phoneNum 电话号码
     * @param phoneName 电话名称
     * @param address 当收到电话状态消息时，需要指明该电话状态来自哪个蓝牙设备地址，一般指车机的蓝牙地址
     */
@Override public void sendBtHfpIndication(int state, java.lang.String phoneNum, java.lang.String phoneName, java.lang.String address) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeInt(state);
_data.writeString(phoneNum);
_data.writeString(phoneName);
_data.writeString(address);
mRemote.transact(Stub.TRANSACTION_sendBtHfpIndication, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
/**
     * 反馈指定状态给手机， 目前主要用来反馈车机MIC是否Mute的状态
     * @param status 指明指定命令的执行结果
     * @param type 指明反馈的是哪一种状态
     */
@Override public void sendBtHfpStatusRespone(int status, int type) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeInt(status);
_data.writeInt(type);
mRemote.transact(Stub.TRANSACTION_sendBtHfpStatusRespone, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
/**
     * 主要用来杀掉RelayServer和退出CarLifeService
     */
@Override public void killProcess() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_killProcess, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
}
static final int TRANSACTION_registerUICallback = (android.os.IBinder.FIRST_CALL_TRANSACTION + 0);
static final int TRANSACTION_unregisterUICallback = (android.os.IBinder.FIRST_CALL_TRANSACTION + 1);
static final int TRANSACTION_connect = (android.os.IBinder.FIRST_CALL_TRANSACTION + 2);
static final int TRANSACTION_disconnect = (android.os.IBinder.FIRST_CALL_TRANSACTION + 3);
static final int TRANSACTION_show = (android.os.IBinder.FIRST_CALL_TRANSACTION + 4);
static final int TRANSACTION_hide = (android.os.IBinder.FIRST_CALL_TRANSACTION + 5);
static final int TRANSACTION_startapp = (android.os.IBinder.FIRST_CALL_TRANSACTION + 6);
static final int TRANSACTION_mute = (android.os.IBinder.FIRST_CALL_TRANSACTION + 7);
static final int TRANSACTION_unmute = (android.os.IBinder.FIRST_CALL_TRANSACTION + 8);
static final int TRANSACTION_pause = (android.os.IBinder.FIRST_CALL_TRANSACTION + 9);
static final int TRANSACTION_resume = (android.os.IBinder.FIRST_CALL_TRANSACTION + 10);
static final int TRANSACTION_setVolume = (android.os.IBinder.FIRST_CALL_TRANSACTION + 11);
static final int TRANSACTION_getVolume = (android.os.IBinder.FIRST_CALL_TRANSACTION + 12);
static final int TRANSACTION_onTouchEvent = (android.os.IBinder.FIRST_CALL_TRANSACTION + 13);
static final int TRANSACTION_onKeyEvent = (android.os.IBinder.FIRST_CALL_TRANSACTION + 14);
static final int TRANSACTION_setMicMode = (android.os.IBinder.FIRST_CALL_TRANSACTION + 15);
static final int TRANSACTION_setMicState = (android.os.IBinder.FIRST_CALL_TRANSACTION + 16);
static final int TRANSACTION_setAudioStream = (android.os.IBinder.FIRST_CALL_TRANSACTION + 17);
static final int TRANSACTION_startBtIdentifyReq = (android.os.IBinder.FIRST_CALL_TRANSACTION + 18);
static final int TRANSACTION_btHfpConnectStatus = (android.os.IBinder.FIRST_CALL_TRANSACTION + 19);
static final int TRANSACTION_sendBtHfpResponse = (android.os.IBinder.FIRST_CALL_TRANSACTION + 20);
static final int TRANSACTION_sendBtHfpIndication = (android.os.IBinder.FIRST_CALL_TRANSACTION + 21);
static final int TRANSACTION_sendBtHfpStatusRespone = (android.os.IBinder.FIRST_CALL_TRANSACTION + 22);
static final int TRANSACTION_killProcess = (android.os.IBinder.FIRST_CALL_TRANSACTION + 23);
}
/**
     * 注册CarLife回调，用于通知状态改变、资源申请等
     */
public void registerUICallback(com.hsae.carlife.aidl.ICarLifeUICallback callback) throws android.os.RemoteException;
/**
     * 注销CarLife回调
     */
public void unregisterUICallback(com.hsae.carlife.aidl.ICarLifeUICallback callback) throws android.os.RemoteException;
/**
     * 连接CarLife
     * @param channel 百度提供的渠道号
     */
public void connect(java.lang.String channel) throws android.os.RemoteException;
/**
     * 断开CarLife
     */
public void disconnect() throws android.os.RemoteException;
/**
     * 显示手机镜像
     * @param surface 显示的缓冲区域
     * @param width 显示区域的宽度
     * @param height 显示区域的高度
     */
public void show(android.view.Surface surface, int width, int height) throws android.os.RemoteException;
/**
     * 隐藏手机镜像
     */
public void hide() throws android.os.RemoteException;
/**
     * 启动手机端CarLife应用
     */
public void startapp() throws android.os.RemoteException;
/**
     * 静音指定音源
     * @param source CarLife音源，包括Media/TTS/VR
     */
public void mute(int source) throws android.os.RemoteException;
/**
     * 取消静音指定音源
     * @param source CarLife音源，包括Media/TTS/VR
     */
public void unmute(int source) throws android.os.RemoteException;
/**
     * 暂停指定音源
     * @param source CarLife音源，包括Media/TTS/VR
     */
public void pause(int source) throws android.os.RemoteException;
/**
     * 播放指定音源
     * @param source CarLife音源，包括Media/TTS/VR
     */
public void resume(int source) throws android.os.RemoteException;
/**
     * 设置指定音源的音量
     * @param source CarLife音源，包括Media/TTS/VR
     * @param volume 音量等级，0 ~ 10
     */
public void setVolume(int source, int volume) throws android.os.RemoteException;
/**
     * 获取指定音源的音量
     * @param source CarLife音源，包括Media/TTS/VR
     * @return 音量等级，0 ~ 10
     */
public int getVolume(int source) throws android.os.RemoteException;
/**
     * 触屏事件
     */
public void onTouchEvent(android.view.MotionEvent event) throws android.os.RemoteException;
/**
     * 按键事件
     * @param keyCode 参考 {@link com.hsae.carlife.common.CarLifeDef}
     */
public void onKeyEvent(int keyCode) throws android.os.RemoteException;
/**
     * 设置mic模式，用于语音识别
     * @param mode mic模式，参考 {@link com.hsae.carlife.common.CarLifeDef}
     */
public void setMicMode(int mode) throws android.os.RemoteException;
/**
     * 设置mic状态
     * @param state OFF/ON，参考 {@link com.hsae.carlife.common.CarLifeDef}
     */
public void setMicState(int state) throws android.os.RemoteException;
/**
     * 设置audio音源的流类型
     * @param source CarLife音源
     * @param stream 流类型， 例如AudioManager.STREAM_MUSIC, AudioManager.STREAM_HSAE
     */
public void setAudioStream(int source, int stream) throws android.os.RemoteException;
/**
     * 发送蓝牙标定请求用于初始化蓝牙自动匹配
     * @param address 本地蓝牙地址
     * @param name 本地蓝牙名称
     */
public void startBtIdentifyReq(java.lang.String address, java.lang.String name) throws android.os.RemoteException;
/**
     * 发送蓝牙Hfp的连接状态
     * @param status HFP状态， 0: 已断开, 1: 已连接
     */
public void btHfpConnectStatus(int status) throws android.os.RemoteException;
/**
     * 用来反馈执行命令的结果
     * @param status 指明指定命令执行的结果。0：命令执行失败；1：命令执行成功
     * @param command 指明是执行的哪条命令
     * @param dtmfCode 如果是发送DTMF code成功，需要反馈是发送的哪一个DTMF code, 包括数字0-9，*， #
     */
public void sendBtHfpResponse(int status, int command, int dtmfCode) throws android.os.RemoteException;
/**
     * 用来告诉手机蓝牙连接后的电话状态
     * @param state 指明电话的状态， 具体的电话状态如下表
     * @param phoneNum 电话号码
     * @param phoneName 电话名称
     * @param address 当收到电话状态消息时，需要指明该电话状态来自哪个蓝牙设备地址，一般指车机的蓝牙地址
     */
public void sendBtHfpIndication(int state, java.lang.String phoneNum, java.lang.String phoneName, java.lang.String address) throws android.os.RemoteException;
/**
     * 反馈指定状态给手机， 目前主要用来反馈车机MIC是否Mute的状态
     * @param status 指明指定命令的执行结果
     * @param type 指明反馈的是哪一种状态
     */
public void sendBtHfpStatusRespone(int status, int type) throws android.os.RemoteException;
/**
     * 主要用来杀掉RelayServer和退出CarLifeService
     */
public void killProcess() throws android.os.RemoteException;
}
