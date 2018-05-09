package io.github.ryanhoo.music.data.source;

import android.content.Context;
import android.util.Log;

import com.litesuits.orm.LiteOrm;
import com.litesuits.orm.db.assit.QueryBuilder;
import com.litesuits.orm.db.model.ConflictAlgorithm;

import java.io.File;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import io.github.ryanhoo.music.data.model.Folder;
import io.github.ryanhoo.music.data.model.PlayList;
import io.github.ryanhoo.music.data.model.Song;
import io.github.ryanhoo.music.utils.DBUtils;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;

/**
 * Created with Android Studio.
 * User: ryan.hoo.j@gmail.com
 * Date: 9/10/16
 * Time: 4:54 PM
 * Desc: AppLocalDataSource
 */
/* package */ class AppLocalDataSource implements AppContract {

    private static final String TAG = "AppLocalDataSource";

    private Context mContext;
    private LiteOrm mLiteOrm;

    public AppLocalDataSource(Context context, LiteOrm orm) {
        mContext = context;
        mLiteOrm = orm;
    }

    // Play List

    @Override
    public Observable<List<PlayList>> playLists() {
        return Observable.create(new ObservableOnSubscribe<List<PlayList>>() {
            @Override
            public void subscribe(ObservableEmitter<List<PlayList>> emitter) throws Exception {
                List<PlayList> playLists = mLiteOrm.query(PlayList.class);
                if (playLists.isEmpty()) {
                    // First query, create the default play list
                    PlayList playList = DBUtils.generateFavoritePlayList(mContext);
                    long result = mLiteOrm.save(playList);
                    Log.d(TAG, "Create default playlist(Favorite) with " + (result == 1 ? "success" : "failure"));
                    playLists.add(playList);
                }
                emitter.onNext(playLists);
                emitter.onComplete();
            }
        });

    }

    @Override
    public List<PlayList> cachedPlayLists() {
        return null;
    }

    @Override
    public Observable<PlayList> create(final PlayList playList) {
        return Observable.create(new ObservableOnSubscribe<PlayList>() {
            @Override
            public void subscribe(ObservableEmitter<PlayList> subscriber) throws Exception {
                Date now = new Date();
                playList.setCreatedAt(now);
                playList.setUpdatedAt(now);

                long result = mLiteOrm.save(playList);
                if (result > 0) {
                    subscriber.onNext(playList);
                } else {
                    subscriber.onError(new Exception("Create play list failed"));
                }
                subscriber.onComplete();
            }
        });

    }

    @Override
    public Observable<PlayList> update(final PlayList playList) {
        return Observable.create(new ObservableOnSubscribe<PlayList>() {
            @Override
            public void subscribe(ObservableEmitter<PlayList> subscriber) throws Exception {
                playList.setUpdatedAt(new Date());

                long result = mLiteOrm.update(playList);
                if (result > 0) {
                    subscriber.onNext(playList);
                } else {
                    subscriber.onError(new Exception("Update play list failed"));
                }
                subscriber.onComplete();
            }
        });
    }

    @Override
    public Observable<PlayList> delete(final PlayList playList) {
        return Observable.create(new ObservableOnSubscribe<PlayList>() {
            @Override
            public void subscribe(ObservableEmitter<PlayList> subscriber) throws Exception {
                long result = mLiteOrm.delete(playList);
                if (result > 0) {
                    subscriber.onNext(playList);
                } else {
                    subscriber.onError(new Exception("Delete play list failed"));
                }
                subscriber.onComplete();
            }
        });
    }

    // Folder

    @Override
    public Observable<List<Folder>> folders() {
        return Observable.create(new ObservableOnSubscribe<List<Folder>>() {
            @Override
            public void subscribe(ObservableEmitter<List<Folder>> subscriber) throws Exception {
                if (PreferenceManager.isFirstQueryFolders(mContext)) {
                    List<Folder> defaultFolders = DBUtils.generateDefaultFolders();
                    long result = mLiteOrm.save(defaultFolders);
                    Log.d(TAG, "subscribe default folders effected " + result + "rows");
                    PreferenceManager.reportFirstQueryFolders(mContext);
                }
                List<Folder> folders = mLiteOrm.query(
                        QueryBuilder.create(Folder.class).appendOrderAscBy(Folder.COLUMN_NAME)
                );
                subscriber.onNext(folders);
                subscriber.onComplete();
            }
        });
    }

    @Override
    public Observable<Folder> create(final Folder folder) {
        return Observable.create(new ObservableOnSubscribe<Folder>() {
            @Override
            public void subscribe(ObservableEmitter<Folder> subscriber) throws Exception {
                folder.setCreatedAt(new Date());

                long result = mLiteOrm.save(folder);
                if (result > 0) {
                    subscriber.onNext(folder);
                } else {
                    subscriber.onError(new Exception("Create folder failed"));
                }
                subscriber.onComplete();
            }
        });
    }

    @Override
    public Observable<List<Folder>> create(final List<Folder> folders) {
        return Observable.create(new ObservableOnSubscribe<List<Folder>>() {
            @Override
            public void subscribe(ObservableEmitter<List<Folder>> subscriber) throws Exception {
                Date now = new Date();
                for (Folder folder : folders) {
                    folder.setCreatedAt(now);
                }

                long result = mLiteOrm.save(folders);
                if (result > 0) {
                    List<Folder> allNewFolders = mLiteOrm.query(
                            QueryBuilder.create(Folder.class).appendOrderAscBy(Folder.COLUMN_NAME)
                    );
                    subscriber.onNext(allNewFolders);
                } else {
                    subscriber.onError(new Exception("Create folders failed"));
                }
                subscriber.onComplete();
            }
        });
    }

    @Override
    public Observable<Folder> update(final Folder folder) {
        return Observable.create(new ObservableOnSubscribe<Folder>() {
            @Override
            public void subscribe(ObservableEmitter<Folder> subscriber) throws Exception {
                mLiteOrm.delete(folder);
                long result = mLiteOrm.save(folder);
                if (result > 0) {
                    subscriber.onNext(folder);
                } else {
                    subscriber.onError(new Exception("Update folder failed"));
                }
                subscriber.onComplete();
            }
        });
    }

    @Override
    public Observable<Folder> delete(final Folder folder) {
        return Observable.create(new ObservableOnSubscribe<Folder>() {
            @Override
            public void subscribe(ObservableEmitter<Folder> subscriber) throws Exception {
                long result = mLiteOrm.delete(folder);
                if (result > 0) {
                    subscriber.onNext(folder);
                } else {
                    subscriber.onError(new Exception("Delete folder failed"));
                }
                subscriber.onComplete();
            }
        });
    }

    @Override
    public Observable<List<Song>> insert(final List<Song> songs) {
        return Observable.create(new ObservableOnSubscribe<List<Song>>() {
            @Override
            public void subscribe(ObservableEmitter<List<Song>> emitter) throws Exception {
                for (Song song : songs) {
                    mLiteOrm.insert(song, ConflictAlgorithm.Abort);
                }
                List<Song> allSongs = mLiteOrm.query(Song.class);
                File file;
                for (Iterator<Song> iterator = allSongs.iterator(); iterator.hasNext(); ) {
                    Song song = iterator.next();
                    file = new File(song.getPath());
                    boolean exists = file.exists();
                    if (!exists) {
                        iterator.remove();
                        mLiteOrm.delete(song);
                    }
                }
                emitter.onNext(allSongs);
                emitter.onComplete();
            }
        });

    }

    @Override
    public Observable<Song> update(final Song song) {
        return Observable.create(new ObservableOnSubscribe<Song>() {
            @Override
            public void subscribe(ObservableEmitter<Song> subscriber) throws Exception {
                int result = mLiteOrm.update(song);
                if (result > 0) {
                    subscriber.onNext(song);
                } else {
                    subscriber.onError(new Exception("Update song failed"));
                }
                subscriber.onComplete();
            }
        });
    }

    @Override
    public Observable<Song> setSongAsFavorite(final Song song, final boolean isFavorite) {
        return Observable.create(new ObservableOnSubscribe<Song>() {
            @Override
            public void subscribe(ObservableEmitter<Song> subscriber) throws Exception {
                List<PlayList> playLists = mLiteOrm.query(
                        QueryBuilder.create(PlayList.class).whereEquals(PlayList.COLUMN_FAVORITE, String.valueOf(true))
                );
                if (playLists.isEmpty()) {
                    PlayList defaultFavorite = DBUtils.generateFavoritePlayList(mContext);
                    playLists.add(defaultFavorite);
                }
                PlayList favorite = playLists.get(0);
                song.setFavorite(isFavorite);
                favorite.setUpdatedAt(new Date());
                if (isFavorite) {
                    // Insert song to the beginning of the list
                    favorite.addSong(song, 0);
                } else {
                    favorite.removeSong(song);
                }
                mLiteOrm.insert(song, ConflictAlgorithm.Replace);
                long result = mLiteOrm.insert(favorite, ConflictAlgorithm.Replace);
                if (result > 0) {
                    subscriber.onNext(song);
                } else {
                    if (isFavorite) {
                        subscriber.onError(new Exception("Set song as favorite failed"));
                    } else {
                        subscriber.onError(new Exception("Set song as unfavorite failed"));
                    }
                }
                subscriber.onComplete();
            }
        });
    }
}
