/*
 * This file is auto-generated.  DO NOT MODIFY.
 * Original file: E:\\CarLifeSource\\Carlife\\Release\\BaseLine\\CarLife-Android\\CarLifeService\\src\\com\\hsae\\carlife\\aidl\\ICarLifeUICallback.aidl
 */
package com.hsae.carlife.aidl;
/**
 * CarLife服务回调，用于状态通知，权限申请。
 */
public interface ICarLifeUICallback extends android.os.IInterface
{
/** Local-side IPC implementation stub class. */
public static abstract class Stub extends android.os.Binder implements com.hsae.carlife.aidl.ICarLifeUICallback
{
private static final java.lang.String DESCRIPTOR = "com.hsae.carlife.aidl.ICarLifeUICallback";
/** Construct the stub at attach it to the interface. */
public Stub()
{
this.attachInterface(this, DESCRIPTOR);
}
/**
 * Cast an IBinder object into an com.hsae.carlife.aidl.ICarLifeUICallback interface,
 * generating a proxy if needed.
 */
public static com.hsae.carlife.aidl.ICarLifeUICallback asInterface(android.os.IBinder obj)
{
if ((obj==null)) {
return null;
}
android.os.IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
if (((iin!=null)&&(iin instanceof com.hsae.carlife.aidl.ICarLifeUICallback))) {
return ((com.hsae.carlife.aidl.ICarLifeUICallback)iin);
}
return new com.hsae.carlife.aidl.ICarLifeUICallback.Stub.Proxy(obj);
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
case TRANSACTION_onConnectProgress:
{
data.enforceInterface(DESCRIPTOR);
int _arg0;
_arg0 = data.readInt();
this.onConnectProgress(_arg0);
reply.writeNoException();
return true;
}
case TRANSACTION_onConnectSuccess:
{
data.enforceInterface(DESCRIPTOR);
this.onConnectSuccess();
reply.writeNoException();
return true;
}
case TRANSACTION_onConnectFailure:
{
data.enforceInterface(DESCRIPTOR);
this.onConnectFailure();
reply.writeNoException();
return true;
}
case TRANSACTION_onConnectException:
{
data.enforceInterface(DESCRIPTOR);
int _arg0;
_arg0 = data.readInt();
this.onConnectException(_arg0);
reply.writeNoException();
return true;
}
case TRANSACTION_onAdbDisabled:
{
data.enforceInterface(DESCRIPTOR);
this.onAdbDisabled();
reply.writeNoException();
return true;
}
case TRANSACTION_onAppNotInstalled:
{
data.enforceInterface(DESCRIPTOR);
this.onAppNotInstalled();
reply.writeNoException();
return true;
}
case TRANSACTION_onAppGoForeground:
{
data.enforceInterface(DESCRIPTOR);
this.onAppGoForeground();
reply.writeNoException();
return true;
}
case TRANSACTION_onAppGoBackground:
{
data.enforceInterface(DESCRIPTOR);
this.onAppGoBackground();
reply.writeNoException();
return true;
}
case TRANSACTION_onGoToDesktop:
{
data.enforceInterface(DESCRIPTOR);
this.onGoToDesktop();
reply.writeNoException();
return true;
}
case TRANSACTION_onDataReceiveError:
{
data.enforceInterface(DESCRIPTOR);
this.onDataReceiveError();
reply.writeNoException();
return true;
}
case TRANSACTION_onIosDeviceDetached:
{
data.enforceInterface(DESCRIPTOR);
this.onIosDeviceDetached();
reply.writeNoException();
return true;
}
case TRANSACTION_onModuleStateChanged:
{
data.enforceInterface(DESCRIPTOR);
int _arg0;
_arg0 = data.readInt();
int _arg1;
_arg1 = data.readInt();
this.onModuleStateChanged(_arg0, _arg1);
reply.writeNoException();
return true;
}
case TRANSACTION_requestAudioStream:
{
data.enforceInterface(DESCRIPTOR);
int _arg0;
_arg0 = data.readInt();
int _result = this.requestAudioStream(_arg0);
reply.writeNoException();
reply.writeInt(_result);
return true;
}
case TRANSACTION_reqMicPermission:
{
data.enforceInterface(DESCRIPTOR);
int _arg0;
_arg0 = data.readInt();
boolean _result = this.reqMicPermission(_arg0);
reply.writeNoException();
reply.writeInt(((_result)?(1):(0)));
return true;
}
case TRANSACTION_onTTSStop:
{
data.enforceInterface(DESCRIPTOR);
this.onTTSStop();
reply.writeNoException();
return true;
}
case TRANSACTION_onVRStop:
{
data.enforceInterface(DESCRIPTOR);
this.onVRStop();
reply.writeNoException();
return true;
}
case TRANSACTION_btIdentifyResultIndCb:
{
data.enforceInterface(DESCRIPTOR);
int _arg0;
_arg0 = data.readInt();
java.lang.String _arg1;
_arg1 = data.readString();
this.btIdentifyResultIndCb(_arg0, _arg1);
reply.writeNoException();
return true;
}
case TRANSACTION_btFeatureConfigureFinish:
{
data.enforceInterface(DESCRIPTOR);
int _arg0;
_arg0 = data.readInt();
this.btFeatureConfigureFinish(_arg0);
reply.writeNoException();
return true;
}
case TRANSACTION_startBtAutoPairRequestCb:
{
data.enforceInterface(DESCRIPTOR);
int _arg0;
_arg0 = data.readInt();
java.lang.String _arg1;
_arg1 = data.readString();
int _result = this.startBtAutoPairRequestCb(_arg0, _arg1);
reply.writeNoException();
reply.writeInt(_result);
return true;
}
case TRANSACTION_btHfpRequestCb:
{
data.enforceInterface(DESCRIPTOR);
int _arg0;
_arg0 = data.readInt();
java.lang.String _arg1;
_arg1 = data.readString();
int _arg2;
_arg2 = data.readInt();
this.btHfpRequestCb(_arg0, _arg1, _arg2);
reply.writeNoException();
return true;
}
case TRANSACTION_btHfpStatusRequestCb:
{
data.enforceInterface(DESCRIPTOR);
int _arg0;
_arg0 = data.readInt();
this.btHfpStatusRequestCb(_arg0);
reply.writeNoException();
return true;
}
case TRANSACTION_ontelState:
{
data.enforceInterface(DESCRIPTOR);
int _arg0;
_arg0 = data.readInt();
this.ontelState(_arg0);
reply.writeNoException();
return true;
}
case TRANSACTION_onMdOsNotSupport:
{
data.enforceInterface(DESCRIPTOR);
java.lang.String _arg0;
_arg0 = data.readString();
java.lang.String _arg1;
_arg1 = data.readString();
int _arg2;
_arg2 = data.readInt();
this.onMdOsNotSupport(_arg0, _arg1, _arg2);
reply.writeNoException();
return true;
}
}
return super.onTransact(code, data, reply, flags);
}
private static class Proxy implements com.hsae.carlife.aidl.ICarLifeUICallback
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
     * 连接进度
     * @param progress 进度， 0 ~ 100
     */
@Override public void onConnectProgress(int progress) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeInt(progress);
mRemote.transact(Stub.TRANSACTION_onConnectProgress, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
/**
     * 连接成功
     */
@Override public void onConnectSuccess() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_onConnectSuccess, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
/**
     * 连接失败
     */
@Override public void onConnectFailure() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_onConnectFailure, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
/**
     * 连接异常
     * @param exception 异常类型，参考 {@link com.hsae.carlife.common.CarLifeDef}
     */
@Override public void onConnectException(int exception) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeInt(exception);
mRemote.transact(Stub.TRANSACTION_onConnectException, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
/**
     * 手机端不支持或未打开ADB调试
     */
@Override public void onAdbDisabled() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_onAdbDisabled, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
/**
     * 手机端未安装应用
     */
@Override public void onAppNotInstalled() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_onAppNotInstalled, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
/**
     * 手机端应用前台显示
     */
@Override public void onAppGoForeground() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_onAppGoForeground, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
/**
     * 手机端应用后台显示
     */
@Override public void onAppGoBackground() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_onAppGoBackground, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
/**
     * 接收手机发送的回到车机主界面消息
     */
@Override public void onGoToDesktop() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_onGoToDesktop, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
/**
     * 数据接收错误，连接断开
     */
@Override public void onDataReceiveError() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_onDataReceiveError, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
/**
     * iPhone 设备断开
     */
@Override public void onIosDeviceDetached() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_onIosDeviceDetached, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
/**
     * 模块状态变化
     * @param module 相关模块，参考 {@link com.hsae.carlife.common.CarLifeDef}
     * @param state 相关状态，参考 {@link com.hsae.carlife.common.CarLifeDef}
     */
@Override public void onModuleStateChanged(int module, int state) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeInt(module);
_data.writeInt(state);
mRemote.transact(Stub.TRANSACTION_onModuleStateChanged, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
/**
     * 申请Audio权限
     * @param source 音源类型，包括Media/TTS/VR
     * @return 申请到的具体audio stream类型，-1表示申请失败，不可播放
     */
@Override public int requestAudioStream(int source) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
int _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeInt(source);
mRemote.transact(Stub.TRANSACTION_requestAudioStream, _data, _reply, 0);
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
     * 申请MIC权限
     * @param type 录音类型 参考 {@link com.hsae.carlife.common.CarLifeDef}
     */
@Override public boolean reqMicPermission(int type) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
boolean _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeInt(type);
mRemote.transact(Stub.TRANSACTION_reqMicPermission, _data, _reply, 0);
_reply.readException();
_result = (0!=_reply.readInt());
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
/**
     * 导航音结束
     */
@Override public void onTTSStop() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_onTTSStop, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
/**
     * 语音识别提示音结束
     */
@Override public void onVRStop() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_onVRStop, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
/**
     * 车机和手机标定结果
     * @param status 状态, 0表示标定为1, 即连接*****
     */
@Override public void btIdentifyResultIndCb(int status, java.lang.String address) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeInt(status);
_data.writeString(address);
mRemote.transact(Stub.TRANSACTION_btIdentifyResultIndCb, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
/**
     * 蓝牙Feature配置完成
     * @param result 0:成功 负值 :失败
     */
@Override public void btFeatureConfigureFinish(int result) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeInt(result);
mRemote.transact(Stub.TRANSACTION_btFeatureConfigureFinish, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
/**
     * 通知应用开始自动匹配的流程
     * @param ostype 手机系统类型，0：Android 1：iOS
     * @param mdaddress 手机的蓝牙的地址
     */
@Override public int startBtAutoPairRequestCb(int ostype, java.lang.String mdaddress) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
int _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeInt(ostype);
_data.writeString(mdaddress);
mRemote.transact(Stub.TRANSACTION_startBtAutoPairRequestCb, _data, _reply, 0);
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
     * 该消息由手机发送到车机，用来请求 车机通过自身的蓝牙HFP协议来完成电话有关的操作。
     * @param command 指明是哪一个操作命令
     * @param phoneNum 电话号码
     * @param dtmfCode DTMF Code/command 为SEND_DTMF有效
     */
@Override public void btHfpRequestCb(int command, java.lang.String phoneNum, int dtmfCode) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeInt(command);
_data.writeString(phoneNum);
_data.writeInt(dtmfCode);
mRemote.transact(Stub.TRANSACTION_btHfpRequestCb, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
/**
     * 该请求从手机发送到车机， 用来获取指定的状态，目前主要获取车机端MIC是否mute的状态
     *@param type 指定获取哪一种状态
     */
@Override public void btHfpStatusRequestCb(int type) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeInt(type);
mRemote.transact(Stub.TRANSACTION_btHfpStatusRequestCb, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
/**
     * 该请求从手机发送到车机， 用来获取手机端的电话状态
     *@param state 电话状态，0：CARLIFE_TEL_IDLE, 1：CARLIFE_TEL_INCOMING,
     *2：CARLIFE_TEL_OUTGOING, 3：CARLIFE_TEL_INCALLING
     */
@Override public void ontelState(int state) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeInt(state);
mRemote.transact(Stub.TRANSACTION_ontelState, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
/**
     * 该请求从手机发送到车机， 用来获取手机端的OS的信息
     *@param os 手机系统版本
     *@param release 如果OS是Android， 则表示为Android 版本信息， 如果OS是Iphone 则为空
     *@param sdk 如果OS是Android， 则表示为Android SDK信息， 如果OS是Iphone 则为0
     */
@Override public void onMdOsNotSupport(java.lang.String os, java.lang.String release, int sdk) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeString(os);
_data.writeString(release);
_data.writeInt(sdk);
mRemote.transact(Stub.TRANSACTION_onMdOsNotSupport, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
}
static final int TRANSACTION_onConnectProgress = (android.os.IBinder.FIRST_CALL_TRANSACTION + 0);
static final int TRANSACTION_onConnectSuccess = (android.os.IBinder.FIRST_CALL_TRANSACTION + 1);
static final int TRANSACTION_onConnectFailure = (android.os.IBinder.FIRST_CALL_TRANSACTION + 2);
static final int TRANSACTION_onConnectException = (android.os.IBinder.FIRST_CALL_TRANSACTION + 3);
static final int TRANSACTION_onAdbDisabled = (android.os.IBinder.FIRST_CALL_TRANSACTION + 4);
static final int TRANSACTION_onAppNotInstalled = (android.os.IBinder.FIRST_CALL_TRANSACTION + 5);
static final int TRANSACTION_onAppGoForeground = (android.os.IBinder.FIRST_CALL_TRANSACTION + 6);
static final int TRANSACTION_onAppGoBackground = (android.os.IBinder.FIRST_CALL_TRANSACTION + 7);
static final int TRANSACTION_onGoToDesktop = (android.os.IBinder.FIRST_CALL_TRANSACTION + 8);
static final int TRANSACTION_onDataReceiveError = (android.os.IBinder.FIRST_CALL_TRANSACTION + 9);
static final int TRANSACTION_onIosDeviceDetached = (android.os.IBinder.FIRST_CALL_TRANSACTION + 10);
static final int TRANSACTION_onModuleStateChanged = (android.os.IBinder.FIRST_CALL_TRANSACTION + 11);
static final int TRANSACTION_requestAudioStream = (android.os.IBinder.FIRST_CALL_TRANSACTION + 12);
static final int TRANSACTION_reqMicPermission = (android.os.IBinder.FIRST_CALL_TRANSACTION + 13);
static final int TRANSACTION_onTTSStop = (android.os.IBinder.FIRST_CALL_TRANSACTION + 14);
static final int TRANSACTION_onVRStop = (android.os.IBinder.FIRST_CALL_TRANSACTION + 15);
static final int TRANSACTION_btIdentifyResultIndCb = (android.os.IBinder.FIRST_CALL_TRANSACTION + 16);
static final int TRANSACTION_btFeatureConfigureFinish = (android.os.IBinder.FIRST_CALL_TRANSACTION + 17);
static final int TRANSACTION_startBtAutoPairRequestCb = (android.os.IBinder.FIRST_CALL_TRANSACTION + 18);
static final int TRANSACTION_btHfpRequestCb = (android.os.IBinder.FIRST_CALL_TRANSACTION + 19);
static final int TRANSACTION_btHfpStatusRequestCb = (android.os.IBinder.FIRST_CALL_TRANSACTION + 20);
static final int TRANSACTION_ontelState = (android.os.IBinder.FIRST_CALL_TRANSACTION + 21);
static final int TRANSACTION_onMdOsNotSupport = (android.os.IBinder.FIRST_CALL_TRANSACTION + 22);
}
/**
     * 连接进度
     * @param progress 进度， 0 ~ 100
     */
public void onConnectProgress(int progress) throws android.os.RemoteException;
/**
     * 连接成功
     */
public void onConnectSuccess() throws android.os.RemoteException;
/**
     * 连接失败
     */
public void onConnectFailure() throws android.os.RemoteException;
/**
     * 连接异常
     * @param exception 异常类型，参考 {@link com.hsae.carlife.common.CarLifeDef}
     */
public void onConnectException(int exception) throws android.os.RemoteException;
/**
     * 手机端不支持或未打开ADB调试
     */
public void onAdbDisabled() throws android.os.RemoteException;
/**
     * 手机端未安装应用
     */
public void onAppNotInstalled() throws android.os.RemoteException;
/**
     * 手机端应用前台显示
     */
public void onAppGoForeground() throws android.os.RemoteException;
/**
     * 手机端应用后台显示
     */
public void onAppGoBackground() throws android.os.RemoteException;
/**
     * 接收手机发送的回到车机主界面消息
     */
public void onGoToDesktop() throws android.os.RemoteException;
/**
     * 数据接收错误，连接断开
     */
public void onDataReceiveError() throws android.os.RemoteException;
/**
     * iPhone 设备断开
     */
public void onIosDeviceDetached() throws android.os.RemoteException;
/**
     * 模块状态变化
     * @param module 相关模块，参考 {@link com.hsae.carlife.common.CarLifeDef}
     * @param state 相关状态，参考 {@link com.hsae.carlife.common.CarLifeDef}
     */
public void onModuleStateChanged(int module, int state) throws android.os.RemoteException;
/**
     * 申请Audio权限
     * @param source 音源类型，包括Media/TTS/VR
     * @return 申请到的具体audio stream类型，-1表示申请失败，不可播放
     */
public int requestAudioStream(int source) throws android.os.RemoteException;
/**
     * 申请MIC权限
     * @param type 录音类型 参考 {@link com.hsae.carlife.common.CarLifeDef}
     */
public boolean reqMicPermission(int type) throws android.os.RemoteException;
/**
     * 导航音结束
     */
public void onTTSStop() throws android.os.RemoteException;
/**
     * 语音识别提示音结束
     */
public void onVRStop() throws android.os.RemoteException;
/**
     * 车机和手机标定结果
     * @param status 状态, 0表示标定为1, 即连接*****
     */
public void btIdentifyResultIndCb(int status, java.lang.String address) throws android.os.RemoteException;
/**
     * 蓝牙Feature配置完成
     * @param result 0:成功 负值 :失败
     */
public void btFeatureConfigureFinish(int result) throws android.os.RemoteException;
/**
     * 通知应用开始自动匹配的流程
     * @param ostype 手机系统类型，0：Android 1：iOS
     * @param mdaddress 手机的蓝牙的地址
     */
public int startBtAutoPairRequestCb(int ostype, java.lang.String mdaddress) throws android.os.RemoteException;
/**
     * 该消息由手机发送到车机，用来请求 车机通过自身的蓝牙HFP协议来完成电话有关的操作。
     * @param command 指明是哪一个操作命令
     * @param phoneNum 电话号码
     * @param dtmfCode DTMF Code/command 为SEND_DTMF有效
     */
public void btHfpRequestCb(int command, java.lang.String phoneNum, int dtmfCode) throws android.os.RemoteException;
/**
     * 该请求从手机发送到车机， 用来获取指定的状态，目前主要获取车机端MIC是否mute的状态
     *@param type 指定获取哪一种状态
     */
public void btHfpStatusRequestCb(int type) throws android.os.RemoteException;
/**
     * 该请求从手机发送到车机， 用来获取手机端的电话状态
     *@param state 电话状态，0：CARLIFE_TEL_IDLE, 1：CARLIFE_TEL_INCOMING,
     *2：CARLIFE_TEL_OUTGOING, 3：CARLIFE_TEL_INCALLING
     */
public void ontelState(int state) throws android.os.RemoteException;
/**
     * 该请求从手机发送到车机， 用来获取手机端的OS的信息
     *@param os 手机系统版本
     *@param release 如果OS是Android， 则表示为Android 版本信息， 如果OS是Iphone 则为空
     *@param sdk 如果OS是Android， 则表示为Android SDK信息， 如果OS是Iphone 则为0
     */
public void onMdOsNotSupport(java.lang.String os, java.lang.String release, int sdk) throws android.os.RemoteException;
}
