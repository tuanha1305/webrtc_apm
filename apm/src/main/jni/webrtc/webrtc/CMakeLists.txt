cmake_minimum_required(VERSION 3.6)

project(webrtc_apm)

set(CMAKE_CXX_STANDARD 11)

#INCLUDE_DIRECTORIES(${CMAKE_CURRENT_SOURCE_DIR})
#INCLUDE_DIRECTORIES(${CMAKE_CURRENT_SOURCE_DIR}/audio_effect)
#INCLUDE_DIRECTORIES(${CMAKE_CURRENT_SOURCE_DIR}/libcommon)
#INCLUDE_DIRECTORIES(${CMAKE_CURRENT_SOURCE_DIR}/audio_effect/libaudio_effect/audio_effect_filter_impl/pitch_shift/include)
#
#INCLUDE_DIRECTORIES(${CMAKE_CURRENT_SOURCE_DIR}/rdparty)
#INCLUDE_DIRECTORIES(${CMAKE_CURRENT_SOURCE_DIR}/3rdparty/ffmpeg/include)
#INCLUDE_DIRECTORIES(${CMAKE_CURRENT_SOURCE_DIR}/liblivecore)
#INCLUDE_DIRECTORIES(${CMAKE_CURRENT_SOURCE_DIR}/video_encoder)
#INCLUDE_DIRECTORIES(${CMAKE_CURRENT_SOURCE_DIR}/libeditcore)
#INCLUDE_DIRECTORIES(${CMAKE_CURRENT_SOURCE_DIR}/video_consumer)

#add_definitions("-D__STDC_CONSTANT_MACROS")

add_subdirectory(${CMAKE_CURRENT_SOURCE_DIR}/faac)
add_subdirectory(${CMAKE_CURRENT_SOURCE_DIR}/lamemp3)
add_subdirectory(${CMAKE_CURRENT_SOURCE_DIR}/soundtouch)
add_subdirectory(${CMAKE_CURRENT_SOURCE_DIR}/webrtc)




#set(LIBHARDWARE_DIR "${CMAKE_CURRENT_SOURCE_DIR}/3rdparty/prebuilt")
#
#add_library(fdk-aac STATIC IMPORTED)
#set_target_properties(fdk-aac PROPERTIES IMPORTED_LOCATION ${LIBHARDWARE_DIR}/${ANDROID_ABI}/libfdk-aac.a)
#
#add_library(avfilter STATIC IMPORTED)
#set_target_properties(avfilter PROPERTIES IMPORTED_LOCATION ${LIBHARDWARE_DIR}/${ANDROID_ABI}/libavfilter.a)
#
#add_library(vo-aacenc STATIC IMPORTED)
#set_target_properties(vo-aacenc PROPERTIES IMPORTED_LOCATION ${LIBHARDWARE_DIR}/${ANDROID_ABI}/libvo-aacenc.a)
#
#add_library(avformat STATIC IMPORTED)
#set_target_properties(avformat PROPERTIES IMPORTED_LOCATION ${LIBHARDWARE_DIR}/${ANDROID_ABI}/libavformat.a)
#
#add_library(avcodec STATIC IMPORTED)
#set_target_properties(avcodec PROPERTIES IMPORTED_LOCATION ${LIBHARDWARE_DIR}/${ANDROID_ABI}/libavcodec.a)
#
#add_library(sox STATIC IMPORTED)
#set_target_properties(sox PROPERTIES IMPORTED_LOCATION ${LIBHARDWARE_DIR}/${ANDROID_ABI}/libsox.a)
#
#add_library(swresample STATIC IMPORTED)
#set_target_properties(swresample PROPERTIES IMPORTED_LOCATION ${LIBHARDWARE_DIR}/${ANDROID_ABI}/libswresample.a)
#
#add_library(avutil STATIC IMPORTED)
#set_target_properties(avutil PROPERTIES IMPORTED_LOCATION ${LIBHARDWARE_DIR}/${ANDROID_ABI}/libavutil.a)
#
#add_library(swscale STATIC IMPORTED)
#set_target_properties(swscale PROPERTIES IMPORTED_LOCATION ${LIBHARDWARE_DIR}/${ANDROID_ABI}/libswscale.a)
#
#add_library(postproc STATIC IMPORTED)
#set_target_properties(postproc PROPERTIES IMPORTED_LOCATION ${LIBHARDWARE_DIR}/${ANDROID_ABI}/libpostproc.a)
#
#add_library(x264 STATIC IMPORTED)
#set_target_properties(x264 PROPERTIES IMPORTED_LOCATION ${LIBHARDWARE_DIR}/${ANDROID_ABI}/libx264.a)
#
#add_library(songstudio SHARED
#        audio_effect/libaudio_effect/audio_effect_filter_impl/pitch_shift/SoundTouch/NativeSoundTouch.cpp
#        video_consumer/jni/VideoStudio.cpp
#        recorder_processor/jni/NativeRecordProcessor.cpp
#        camera_preview/jni/ChangbaRecordingPreviewScheduler.cpp
#        music_decoder/jni/MusicDecoder.cpp
#        )
#
#target_link_libraries(songstudio
#        audioeffect
#        camerapreview
#        editcore
#        livecore
#        musicdecoder
#        recordprocessor
#        videoconsumer
#        videoencoder
#        commontool
#        swscale
#        avformat
#        avcodec
#        avfilter
#        swresample
#        avutil
#        postproc
#        x264
#        fdk-aac
#        vo-aacenc
#        sox
#        GLESv2
#        EGL
#        OpenSLES
#        android
#        log
#        z
#
#        )