cmake_minimum_required(VERSION 3.6)

project(webrtc_apm)

set(LAME_LIBMP3_DIR "lame_3.99.5_libmp3lame")
set(LOCAL_CFLAGS "-DSTDC_HEADERS")
set(CMAKE_C_FLAGS "${CMAKE_C_FLAGS} ${LOCAL_CFLAGS}")
set(CMAKE_CXX_STANDARD 11)

SET(MP3LAME_SRC_FILES
        ${LAME_LIBMP3_DIR}/bitstream.c
        ${LAME_LIBMP3_DIR}/fft.c
        ${LAME_LIBMP3_DIR}/id3tag.c
        ${LAME_LIBMP3_DIR}/mpglib_interface.c
        ${LAME_LIBMP3_DIR}/presets.c
        ${LAME_LIBMP3_DIR}/quantize.c
        ${LAME_LIBMP3_DIR}/reservoir.c
        ${LAME_LIBMP3_DIR}/tables.c
        ${LAME_LIBMP3_DIR}/util.c
        ${LAME_LIBMP3_DIR}/VbrTag.c
        ${LAME_LIBMP3_DIR}/encoder.c
        ${LAME_LIBMP3_DIR}/gain_analysis.c
        ${LAME_LIBMP3_DIR}/lame.c
        ${LAME_LIBMP3_DIR}/newmdct.c
        ${LAME_LIBMP3_DIR}/psymodel.c
        ${LAME_LIBMP3_DIR}/quantize_pvt.c
        ${LAME_LIBMP3_DIR}/set_get.c
        ${LAME_LIBMP3_DIR}/takehiro.c
        ${LAME_LIBMP3_DIR}/vbrquantize.c
        ${LAME_LIBMP3_DIR}/version.c
        lame_util.cpp
        )

add_library(mp3lame SHARED
        ${MP3LAME_SRC_FILES}
        )

target_link_libraries(mp3lame
        m
        android
        log
        z
        )