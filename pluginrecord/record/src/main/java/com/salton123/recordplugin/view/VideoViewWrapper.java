package com.salton123.recordplugin.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.media.MediaPlayer;
import android.net.Uri;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.salton123.recordplugin.R;
import com.salton123.recordplugin.model.bean.VideoBean;


/**
 * User: newSalton@outlook.com
 * Date: 2018/9/15 21:33
 * ModifyTime: 21:33
 * Description:
 */
public class VideoViewWrapper extends FrameLayout implements IPlayerControl {

    protected IVideoListener mVideoListener;
    private boolean isUserStartPlaying = false;

    public VideoViewWrapper(Context context) {
        super(context);
        initVideoViewWrapper();
    }

    public VideoViewWrapper(Context context, AttributeSet attrs) {
        super(context, attrs);
        initVideoViewWrapper();
    }

    public VideoViewWrapper(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initVideoViewWrapper();
    }

    private ImageView ivThumbnail;
    private PhoneVideoView videoPlayer;
    VideoBean mVideoBean = new VideoBean(0, "");

    private void initVideoViewWrapper() {
        LayoutInflater.from(getContext()).inflate(R.layout.record_video_view_wrapper, this, true);
        ivThumbnail = findViewById(R.id.ivThumbnail);
        videoPlayer = findViewById(R.id.phoneVideoView);
        videoPlayer.setOnErrorListener(new MediaPlayer.OnErrorListener() {
            @Override
            public boolean onError(MediaPlayer mp, int what, int extra) {
                if (mVideoListener != null) {
                    mVideoListener.onError();
                }
                return true;
            }
        });
        videoPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                if (mVideoListener != null) {
                    isUserStartPlaying = false;
                    mVideoListener.onComplete();
                }
            }
        });
        videoPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                if (mVideoListener != null) {
                    mVideoListener.onPrepared();
                }
            }
        });
        videoPlayer.setOnInfoListener(new MediaPlayer.OnInfoListener() {
            @Override
            public boolean onInfo(MediaPlayer mp, int what, int extra) {
                if (mVideoListener != null) {
                    mVideoListener.onInfo(what, extra);
                }
                switch (what) {
                    case MediaPlayer.MEDIA_INFO_VIDEO_RENDERING_START: // 视频开始渲染
                        if (mVideoListener != null) {
                            mVideoListener.onVideoStarted();
                        }
                        ivThumbnail.setVisibility(View.GONE);
                        break;
                }
                return true;
            }
        });
    }

    public void updatePlayUrl(VideoBean videoBean) {
        mVideoBean = videoBean;
        ivThumbnail.setImageResource(mVideoBean.resId);
        ivThumbnail.setVisibility(View.VISIBLE);
        videoPlayer.setVideoURI(Uri.parse(mVideoBean.url));
    }

    @Override
    public void start() {
        videoPlayer.start();
        isUserStartPlaying = true;
        if (mVideoListener != null) {
            mVideoListener.onVideoStarted();
        }
    }

    @Override
    public void pause() {
        isUserStartPlaying = false;
        videoPlayer.pause();
    }

    @Override
    public long getDuration() {
        return videoPlayer.getDuration();
    }

    @Override
    public long getCurrentPosition() {
        return videoPlayer.getCurrentPosition();
    }

    @Override
    public void seekTo(long pos) {
        videoPlayer.seekTo((int) pos);
    }

    @Override
    public boolean isPlaying() {
        return videoPlayer.isPlaying();
    }

    @Override
    public int getBufferPercentage() {
        return videoPlayer.getBufferPercentage();
    }

    @Override
    public void startFullScreen() {

    }

    @Override
    public void stopFullScreen() {

    }

    @Override
    public boolean isFullScreen() {
        return false;
    }

    @Override
    public String getTitle() {
        return null;
    }

    @Override
    public void setMute() {

    }

    @Override
    public boolean isMute() {
        return false;
    }

    @Override
    public void setLock(boolean isLocked) {

    }

    @Override
    public void setScreenScale(int screenScale) {

    }

    @Override
    public void retry() {
        isUserStartPlaying = true;
        videoPlayer.start();
    }

    @Override
    public void setSpeed(float speed) {

    }

    @Override
    public long getTcpSpeed() {
        return 0;
    }

    @Override
    public void refresh() {

    }

    @Override
    public void setMirrorRotation(boolean enable) {

    }

    @Override
    public Bitmap doScreenShot() {
        return null;
    }

    public void onPause() {
        if (isPlaying()) {
            videoPlayer.pause();
        }
    }

    public void onResume() {
        if (isUserStartPlaying) {
            updatePlayUrl(mVideoBean);
            start();
        }
    }

    public void onDestroy() {

    }

    public void setVideoListener(IVideoListener listener) {
        this.mVideoListener = listener;
    }
}
