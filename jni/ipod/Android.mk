LOCAL_PATH := $(call my-dir)

include $(CLEAR_VARS)
LOCAL_MODULE    := ipod
LOCAL_SRC_FILES := lib/libipod.so
include $(PREBUILT_SHARED_LIBRARY)
