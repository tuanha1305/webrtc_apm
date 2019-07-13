cmake_minimum_required(VERSION 3.6)

project(webrtc_apm)

set(LOCAL_CFLAGS "-UHAVE_CONFIG_H")
set(CMAKE_C_FLAGS "${LOCAL_CFLAGS} ${CMAKE_C_FLAGS} ")
set(CMAKE_CXX_STANDARD 11)
add_definitions("-DENABLE_FAAC")

INCLUDE_DIRECTORIES(${CMAKE_CURRENT_SOURCE_DIR})
INCLUDE_DIRECTORIES(${CMAKE_CURRENT_SOURCE_DIR}/libFaac)


SET(FAAC_SRC_FILES
        libFaac/aacquant.c
        libFaac/channels.c
        libFaac/frame.c
        libFaac/midside.c
        libFaac/util.c
        libFaac/backpred.c
        libFaac/fft.c
        libFaac/huffman.c
        libFaac/psychkni.c
        libFaac/bitstream.c
        libFaac/filtbank.c
        libFaac/ltp.c
        libFaac/tns.c
        AACEncoder.cpp
        faac_jni.cpp
        )

add_library(faac SHARED
        ${FAAC_SRC_FILES}
        )

target_link_libraries(faac
        m
        android
        log
        z
        )