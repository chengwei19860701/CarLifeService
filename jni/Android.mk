LOCAL_PATH := $(call my-dir)

include $(CLEAR_VARS)
LOCAL_MODULE    := libprotobuf
LOCAL_SRC_FILES := baidu/libprotobuf.so
include $(PREBUILT_SHARED_LIBRARY)

include $(CLEAR_VARS)
LOCAL_MODULE    := libcarlifevehicle
LOCAL_SRC_FILES := baidu/libcarlifevehicle.so
include $(PREBUILT_SHARED_LIBRARY)

include $(CLEAR_VARS)
LOCAL_MODULE    := ipod
LOCAL_SRC_FILES := ipod/lib/libipod.so
include $(PREBUILT_SHARED_LIBRARY)

include $(CLEAR_VARS)
LOCAL_MODULE    := usb
LOCAL_SRC_FILES := ipod/lib/libusb.so
include $(PREBUILT_SHARED_LIBRARY)

include $(CLEAR_VARS)
LOCAL_MODULE    := libcarlifeclient
LOCAL_SRC_FILES :=                         \
    client/src/carlife_adb.cpp             \
    client/src/carlife_bt.cpp              \
    client/src/carlife_command.cpp         \
    client/src/carlife_common.cpp          \
    client/src/carlife_control.cpp         \
    client/src/carlife_device.cpp          \
    client/src/carlife_event.cpp           \
    client/src/carlife_log.cpp             \
    client/src/carlife_media.cpp           \
    client/src/carlife_tts.cpp             \
    client/src/carlife_video.cpp           \
    client/src/carlife_vr.cpp              \
    client/src/carlife_wrapper.cpp
LOCAL_C_INCLUDES:=                         \
    $(LOCAL_PATH)/baidu                    \
    $(LOCAL_PATH)/client/include
LOCAL_LDFLAGS 	:= -llog
LOCAL_SHARED_LIBRARIES 	:= libcarlifevehicle
include $(BUILD_SHARED_LIBRARY)

include $(CLEAR_VARS)
LOCAL_MODULE    := libcarlife_jni
LOCAL_SRC_FILES := carlife_jni.cpp
LOCAL_LDFLAGS 	:= -llog
LOCAL_C_INCLUDES:= \
    $(LOCAL_PATH)/client/include
LOCAL_SHARED_LIBRARIES 	:= libcarlifevehicle libcarlifeclient
include $(BUILD_SHARED_LIBRARY)

include $(CLEAR_VARS)
LOCAL_MODULE    := EapRelayServer
LOCAL_SRC_FILES := \
	RelayServer/src/RelayServer.cpp				\
	RelayServer/src/CSocketConnector.cpp		\
	RelayServer/src/CTranRecvPackageProcess.cpp	\
	RelayServer/src/CConnectManager.cpp			\
	RelayServer/src/socket.cpp					\
	RelayServer/src/JenDebug.cpp                \
	RelayServer/src/EnumIphoneInterface.cpp
LOCAL_C_INCLUDES:= \
    $(LOCAL_PATH)/RelayServer/include           \
    $(LOCAL_PATH)/ipod/include
LOCAL_SHARED_LIBRARIES := libipod libusb
LOCAL_LDFLAGS 	:= -llog

LOCAL_C_INCLUDES += $(LIBUSB_ROOT_ABS)    
LOCAL_SHARED_LIBRARIES += libusb1.0 
include $(BUILD_EXECUTABLE)