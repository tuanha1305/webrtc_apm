cmake_minimum_required(VERSION 3.6)

project(webrtc_apm)

if ("${CMAKE_ANDROID_ARCH_ABI}" STREQUAL "armeabi-v7a")
    message(WARNNING "ABI=${CMAKE_ANDROID_ARCH_ABI}")
    set(LOCAL_CFLAGS "-DWEBRTC_ARCH_ARM_NEON -D__ARM_NEON -DWEBRTC_ANDROID -DWEBRTC_POSIX=1 -DWEBRTC_HAS_NEON -mfloat-abi=softfp -mfpu=neon -march=armv7-a")
elseif (${CMAKE_ANDROID_ARCH_ABI} MATCHES "arm64-v8a")
    message(WARNNING "ABI=${CMAKE_ANDROID_ARCH_ABI}")
    set(LOCAL_CFLAGS "-DWEBRTC_ANDROID -DWEBRTC_POSIX=1 -DWEBRTC_HAS_NEON")
endif ()

set(CMAKE_C_FLAGS "${CMAKE_C_FLAGS} ${LOCAL_CFLAGS}")
set(CMAKE_CXX_STANDARD 11)

INCLUDE_DIRECTORIES(${CMAKE_CURRENT_SOURCE_DIR})

SET(WEBRTC_SRC_FILES
        webrtc/modules/audio_processing/aec/aec_core_neon.c
        webrtc/modules/audio_processing/aec/aec_rdft_neon.c
        webrtc/modules/audio_processing/aecm/aecm_core_c.c
        webrtc/modules/audio_processing/aecm/aecm_core_neon.c
        webrtc/modules/audio_processing/ns/nsx_core_c.c
        webrtc/modules/audio_processing/ns/nsx_core_neon.c
        webrtc/common_audio/signal_processing/cross_correlation_neon.c
        webrtc/common_audio/signal_processing/downsample_fast_neon.c
        webrtc/common_audio/signal_processing/min_max_operations_neon.c
        webrtc/modules/audio_processing/aec/aec_core.c
        webrtc/modules/audio_processing/aec/aec_rdft.c
        webrtc/modules/audio_processing/aec/aec_resampler.c
        webrtc/modules/audio_processing/aec/echo_cancellation.c
        webrtc/modules/audio_processing/aecm/aecm_core.c
        webrtc/modules/audio_processing/aecm/echo_control_mobile.c
        webrtc/modules/audio_processing/ns/noise_suppression.c
        webrtc/modules/audio_processing/ns/noise_suppression_x.c
        webrtc/modules/audio_processing/ns/ns_core.c
        webrtc/modules/audio_processing/ns/nsx_core.c
        webrtc/modules/audio_processing/agc/digital_agc.c
        webrtc/modules/audio_processing/agc/analog_agc.c
        webrtc/modules/audio_processing/utility/delay_estimator_wrapper.c
        webrtc/modules/audio_processing/utility/delay_estimator.c
        webrtc/common_audio/fft4g.c
        webrtc/common_audio/ring_buffer.c
        webrtc/common_audio/signal_processing/complex_bit_reverse.c
        webrtc/common_audio/signal_processing/complex_fft.c
        webrtc/common_audio/signal_processing/copy_set_operations.c
        webrtc/common_audio/signal_processing/cross_correlation.c
        webrtc/common_audio/signal_processing/division_operations.c
        webrtc/common_audio/signal_processing/downsample_fast.c
        webrtc/common_audio/signal_processing/energy.c
        webrtc/common_audio/signal_processing/get_scaling_square.c
        webrtc/common_audio/signal_processing/min_max_operations.c
        webrtc/common_audio/signal_processing/randomization_functions.c
        webrtc/common_audio/signal_processing/real_fft.c
        webrtc/common_audio/signal_processing/spl_init.c
        webrtc/common_audio/signal_processing/spl_sqrt.c
        webrtc/common_audio/signal_processing/spl_sqrt_floor.c
        webrtc/common_audio/signal_processing/vector_scaling_operations.c
        webrtc/common_audio/signal_processing/resample_48khz.c
        webrtc/common_audio/signal_processing/resample_by_2_internal.c
        webrtc/common_audio/signal_processing/resample_by_2.c
        webrtc/common_audio/signal_processing/resample_fractional.c
        webrtc/common_audio/signal_processing/dot_product_with_scale.c
        webrtc/common_audio/vad/vad_core.c
        webrtc/common_audio/vad/vad_filterbank.c
        webrtc/common_audio/vad/vad_gmm.c
        webrtc/common_audio/vad/vad_sp.c
        webrtc/common_audio/vad/webrtc_vad.c
        webrtc/webrtc_jni.cpp
        )

add_library(webrtc_apm SHARED
        ${WEBRTC_SRC_FILES}
        )

target_link_libraries(webrtc_apm
        m
        log
        z
        )