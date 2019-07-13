cmake_minimum_required(VERSION 3.6)

project(webrtc_apm)

set(LOCAL_CFLAGS "-DSTDC_HEADERS")
set(CMAKE_C_FLAGS "${CMAKE_C_FLAGS} ${LOCAL_CFLAGS}")
set(CMAKE_CXX_STANDARD 11)

INCLUDE_DIRECTORIES(${CMAKE_CURRENT_SOURCE_DIR})
INCLUDE_DIRECTORIES(${CMAKE_CURRENT_SOURCE_DIR}/..)
INCLUDE_DIRECTORIES(${CMAKE_CURRENT_SOURCE_DIR}/include)

include(AndroidNdkModules)
android_ndk_import_module_cpufeatures()

SET(SOUNDTOUCH_SRC_FILES
        source/SoundTouch/AAFilter.cpp
        source/SoundTouch/FIFOSampleBuffer.cpp
        source/SoundTouch/FIRFilter.cpp
        source/SoundTouch/cpu_detect_x86.cpp
        source/SoundTouch/sse_optimized.cpp
        source/SoundTouch/RateTransposer.cpp
        source/SoundTouch/InterpolateCubic.cpp
        source/SoundTouch/InterpolateLinear.cpp
        source/SoundTouch/InterpolateShannon.cpp
        source/SoundTouch/TDStretch.cpp
        source/SoundTouch/BPMDetect.cpp
        source/SoundTouch/PeakFinder.cpp
        source/SoundTouch/SoundTouch.cpp
        source/SoundTouch/mmx_optimized.cpp
        ijksoundtouch_wrap.cpp
        soundtouch_jni.cpp
        )

add_library(soundtouch SHARED
        ${SOUNDTOUCH_SRC_FILES}
        )

target_link_libraries(soundtouch
        m
        android
        log
        z
        cpufeatures
        )