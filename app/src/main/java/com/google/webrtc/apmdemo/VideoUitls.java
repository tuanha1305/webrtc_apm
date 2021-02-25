package com.google.webrtc.apmdemo;

import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.media.MediaExtractor;
import android.media.MediaFormat;
import android.media.MediaMetadataRetriever;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;

import java.io.IOException;
import java.text.DecimalFormat;


/**
 * <pre>
 *     author  : devyk on 2020-09-28 21:32
 *     blog    : https://juejin.im/user/578259398ac2470061f3a3fb/posts
 *     github  : https://github.com/yangkun19921001
 *     mailbox : yang1001yk@gmail.com
 *     desc    : This is VideoUitls
 * </pre>
 */
public class VideoUitls {

    /**
     * 获取视频信息
     *
     * @param url
     * @return 视频时长（单位微秒）
     */
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public static long getDuration(String url) {
        try {
            MediaExtractor mediaExtractor = new MediaExtractor();
            mediaExtractor.setDataSource(url);
            int videoExt = TrackUtils.selectVideoTrack(mediaExtractor);
            if (videoExt == -1) {
                videoExt = TrackUtils.selectAudioTrack(mediaExtractor);
                if (videoExt == -1) {
                    return 0;
                }
            }

            long res = 0;
            MediaFormat mediaFormat = mediaExtractor.getTrackFormat(videoExt);
            if (mediaFormat.containsKey(MediaFormat.KEY_DURATION))
                res = mediaFormat.getLong(MediaFormat.KEY_DURATION);
            else //时长
                res = 0;
            mediaExtractor.release();
            return res;
        } catch (Exception e) {
            return 0;
        }

    }

    /**
     * 获取音轨数量
     *
     * @return
     */
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public static int getChannelCount(String url) {
        try {
            MediaExtractor mediaExtractor = new MediaExtractor();
            mediaExtractor.setDataSource(url);
            int audioExt = TrackUtils.selectAudioTrack(mediaExtractor);
            if (audioExt == -1) {
                return 0;
            }
            MediaFormat mediaFormat = mediaExtractor.getTrackFormat(audioExt);
            int channel = 0;
            if (mediaFormat.containsKey(MediaFormat.KEY_CHANNEL_COUNT))
                channel = mediaFormat.getInteger(MediaFormat.KEY_CHANNEL_COUNT);

            mediaExtractor.release();
            return channel;
        } catch (Exception e) {
            return 0;
        }
    }


    /**
     * 获取视频源数据信息
     */
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public static VideoInfo getVideoInfo(String url) {
        MediaMetadataRetriever metadataRetriever = new MediaMetadataRetriever();
        metadataRetriever.setDataSource(url);
        // 获得时长
        String duration = findMetadata(metadataRetriever, MediaMetadataRetriever.METADATA_KEY_DURATION);
        // 获得名称
        String keyTitle = findMetadata(metadataRetriever, MediaMetadataRetriever.METADATA_KEY_TITLE);
        // 获得媒体类型
        String mimetype = findMetadata(metadataRetriever, MediaMetadataRetriever.METADATA_KEY_MIMETYPE);
        // 获得码率
        String bitrate = findMetadata(metadataRetriever, MediaMetadataRetriever.METADATA_KEY_BITRATE);
        //获取视频宽
        String videoWidth = findMetadata(metadataRetriever, MediaMetadataRetriever.METADATA_KEY_VIDEO_WIDTH);
        //获取视频高
        String videoHeight = findMetadata(metadataRetriever, MediaMetadataRetriever.METADATA_KEY_VIDEO_HEIGHT);
        //帧率
        String fps = findMetadata(metadataRetriever, MediaMetadataRetriever.METADATA_KEY_VIDEO_FRAME_COUNT);
        return new VideoInfo(duration, keyTitle, mimetype, bitrate, fps, videoWidth, videoHeight);
    }

    /**
     * 获取视频源数据信息
     */
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public static int getVideoFps(String url) {
        MediaMetadataRetriever metadataRetriever = new MediaMetadataRetriever();
        metadataRetriever.setDataSource(url);
        String duration = findMetadata(metadataRetriever, MediaMetadataRetriever.METADATA_KEY_DURATION);
        //帧率
        String fps = findMetadata(metadataRetriever, MediaMetadataRetriever.METADATA_KEY_VIDEO_FRAME_COUNT);
        return Integer.parseInt(fps) / (Integer.parseInt(duration) / 1000);
    }

    private static String findMetadata(MediaMetadataRetriever metadataRetriever, int type) {
        return metadataRetriever.extractMetadata(type);
    }


    /**
     * @param videoPath 视频路径
     * @param s         第几 s 的缩略图
     * @return
     */
    public static Bitmap getVideoThumbnail(String videoPath, long s) {
        try {
            MediaMetadataRetriever metadataRetriever = new MediaMetadataRetriever();
            metadataRetriever.setDataSource(videoPath);
            Log.d("getVideoThumbnail", "getFrameAtTime time = " + s);
            //这里传入的是ms
            Bitmap frameAtIndex = null;
            for (int i = 0; i < s + 10; i++) {
                frameAtIndex = metadataRetriever.getFrameAtTime(i * 1000);
                if (frameAtIndex != null) {
                    break;
                }
            }
            //因为是缩略图 这边压缩一下
//            Bitmap frame = Bitmap.createScaledBitmap(frameAtIndex, frameAtIndex.getWidth() / 8, frameAtIndex.getHeight() / 8, false);
            Bitmap frame = Bitmap.createScaledBitmap(frameAtIndex, frameAtIndex.getWidth(), frameAtIndex.getHeight(), false);
            frameAtIndex.recycle();
            metadataRetriever.release();
            return frame;
        } catch (Exception e) {
            Log.e("getVideoThumbnail error", e.getMessage());
            return null;
        }
    }


    /**
     * @param size
     * @return
     */
    public static String getSize(long size) {
        //获取到的size为：1705230
        int GB = 1024 * 1024 * 1024;//定义GB的计算常量
        int MB = 1024 * 1024;//定义MB的计算常量
        int KB = 1024;//定义KB的计算常量
        DecimalFormat df = new DecimalFormat("0.00");//格式化小数
        String resultSize = "";
        if (size / GB >= 1) {
            //如果当前Byte的值大于等于1GB
            resultSize = df.format(size / (float) GB) + "GB";
        } else if (size / MB >= 1) {
            //如果当前Byte的值大于等于1MB
            resultSize = df.format(size / (float) MB) + "MB";
        } else if (size / KB >= 1) {
            //如果当前Byte的值大于等于1KB
            resultSize = df.format(size / (float) KB) + "KB";
        } else {
            resultSize = size + "B   ";
        }

        return resultSize;
    }



    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public static int getSampleRate(String path) {
        MediaExtractor mex = new MediaExtractor();
        int sampleRate = 0;
        try {
            mex.setDataSource(path);// the adresss location of the sound on sdcard.
            MediaFormat mf = mex.getTrackFormat(0);
            int bitRate = mf.getInteger(MediaFormat.KEY_BIT_RATE);
            sampleRate = mf.getInteger(MediaFormat.KEY_SAMPLE_RATE);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            mex.release();
        }
        return sampleRate;
    }
}
