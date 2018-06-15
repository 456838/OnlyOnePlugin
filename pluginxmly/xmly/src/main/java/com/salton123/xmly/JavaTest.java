package com.salton123.xmly;

import com.salton123.xmly.business.XmlyInitializer;
import com.salton123.xmly.callback.AbsXmPlayerStatusListener;
import com.ximalaya.ting.android.opensdk.model.PlayableModel;
import com.ximalaya.ting.android.opensdk.model.track.Track;
import com.ximalaya.ting.android.opensdk.player.service.XmPlayerException;

import java.util.List;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;

/**
 * User: newSalton@outlook.com
 * Date: 2018/6/11 下午3:27
 * ModifyTime: 下午3:27
 * Description:
 */
public class JavaTest {
    private String hello;

    public void test() {
        // Observable.create(new ObservableOnSubscribe<List<PlayHistory>>() {
        //     @Override
        //     public void subscribe(ObservableEmitter<List<PlayHistory>> emitter) throws Exception {
        //         emitter.onNext(XmlyInitializer.database.historyDao().getHistory());
        //     }
        // }).subscribe(new Consumer<List<PlayHistory>>() {
        //     @Override
        //     public void accept(List<PlayHistory> playHistories) throws Exception {
        //
        //     }
        // }, new Consumer<Throwable>() {
        //     @Override
        //     public void accept(Throwable throwable) throws Exception {
        //
        //     }
        // }, new Action() {
        //     @Override
        //     public void run() throws Exception {
        //
        //     }
        // });
        //
        // Observable.defer(new Callable<ObservableSource<List<PlayHistory>>>() {
        //     @Override
        //     public ObservableSource<List<PlayHistory>> call() throws Exception {
        //         return null;
        //     }
        // });

        XmlyInitializer.xmPlayerManager.addPlayerStatusListener(mAbsXmPlayerStatusListener);
        Flowable.create(new FlowableOnSubscribe<List<Track>>() {
            @Override
            public void subscribe(FlowableEmitter<List<Track>> emitter) throws Exception {

            }
        }, BackpressureStrategy.DROP);
    }

    private AbsXmPlayerStatusListener mAbsXmPlayerStatusListener = new AbsXmPlayerStatusListener("mAbsXmPlayerStatusListener") {

        @Override
        public void onPlayStart() {
            super.onPlayStart();
        }

        @Override
        public void onPlayPause() {
            super.onPlayPause();
            System.out.println(hello);
        }

        @Override
        public void onPlayStop() {
            super.onPlayStop();
        }

        @Override
        public void onSoundPlayComplete() {
            super.onSoundPlayComplete();
        }

        @Override
        public void onSoundPrepared() {
            super.onSoundPrepared();
        }

        @Override
        public void onSoundSwitch(PlayableModel playableModel, PlayableModel playableModel1) {
            super.onSoundSwitch(playableModel, playableModel1);
        }

        @Override
        public void onBufferingStart() {
            super.onBufferingStart();
        }

        @Override
        public void onBufferingStop() {
            super.onBufferingStop();
        }

        @Override
        public void onBufferProgress(int i) {
            super.onBufferProgress(i);
        }

        @Override
        public void onPlayProgress(int i, int i1) {
            super.onPlayProgress(i, i1);
        }

        @Override
        public boolean onError(XmPlayerException e) {
            return super.onError(e);
        }
    };
}
