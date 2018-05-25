package com.salton123.xmly.business;

import android.support.annotation.Nullable;

import com.salton123.mvp.presenter.RxPresenter;
import com.ximalaya.ting.android.opensdk.constants.DTransferConstants;
import com.ximalaya.ting.android.opensdk.datatrasfer.CommonRequest;
import com.ximalaya.ting.android.opensdk.datatrasfer.IDataCallBack;
import com.ximalaya.ting.android.opensdk.model.album.AlbumList;
import com.ximalaya.ting.android.opensdk.model.album.BatchAlbumList;
import com.ximalaya.ting.android.opensdk.model.album.GussLikeAlbumList;
import com.ximalaya.ting.android.opensdk.model.album.UpdateBatchList;
import com.ximalaya.ting.android.opensdk.model.banner.BannerV2List;
import com.ximalaya.ting.android.opensdk.model.category.CategoryList;
import com.ximalaya.ting.android.opensdk.model.coldboot.GenreList;
import com.ximalaya.ting.android.opensdk.model.tag.TagList;
import com.ximalaya.ting.android.opensdk.model.track.BatchTrackList;
import com.ximalaya.ting.android.opensdk.model.track.LastPlayTrackList;
import com.ximalaya.ting.android.opensdk.model.track.TrackHotList;
import com.ximalaya.ting.android.opensdk.model.track.TrackList;

import java.util.HashMap;
import java.util.Map;

/**
 * User: newSalton@outlook.com
 * Date: 2018/5/23 下午6:36
 * ModifyTime: 下午6:36
 * Description:
 */
public class IRequestPresenter
        extends RxPresenter<RequestContract.IRequestView>
        implements RequestContract.IRequestPresenter {


    @Override
    public void getCategories() {
        Map<String, String> map = new HashMap<>();
        CommonRequest.getCategories(map, new IDataCallBack<CategoryList>() {
            @Override
            public void onSuccess(@Nullable CategoryList categoryList) {
                mView.onSucceed(categoryList);
            }

            @Override
            public void onError(int i, String s) {
                mView.onError(i, s);
            }
        });
    }

    @Override
    public void getTags(String categoryId, String type) {
        Map<String, String> map = new HashMap<>();
        map.put(DTransferConstants.CATEGORY_ID, categoryId);
        map.put(DTransferConstants.TYPE, type);
        CommonRequest.getTags(map, new IDataCallBack<TagList>() {
            @Override
            public void onSuccess(@Nullable TagList tagList) {
                mView.onSucceed(tagList);
            }

            @Override
            public void onError(int i, String s) {
                mView.onError(i, s);
            }
        });
    }

    @Override
    public void getAlbumList(String categoryId, String tagName, String calcDimension, String page) {
        Map<String, String> map = new HashMap<>();
        map.put(DTransferConstants.CATEGORY_ID, categoryId);
        map.put(DTransferConstants.TAG_NAME, tagName);
        map.put(DTransferConstants.CALC_DIMENSION, calcDimension);
        map.put(DTransferConstants.PAGE, page);
        CommonRequest.getAlbumList(map, new IDataCallBack<AlbumList>() {
            @Override
            public void onSuccess(@Nullable AlbumList albumList) {
                mView.onSucceed(albumList);
            }

            @Override
            public void onError(int i, String s) {
                mView.onError(i, s);
            }
        });
    }

    @Override
    public void getTracks(String albumId, String sort, String page) {
        Map<String, String> map = new HashMap<>();
        map.put(DTransferConstants.ALBUM_ID, albumId);
        map.put(DTransferConstants.SORT, sort);
        map.put(DTransferConstants.PAGE, page);
        CommonRequest.getTracks(map, new IDataCallBack<TrackList>() {
            @Override
            public void onSuccess(@Nullable TrackList trackList) {
                mView.onSucceed(trackList);
            }

            @Override
            public void onError(int i, String s) {
                mView.onError(i, s);
            }
        });
    }

    @Override
    public void getBatch(String ids) {
        Map<String, String> map = new HashMap<>();
        map.put(DTransferConstants.ALBUM_IDS, ids);
        CommonRequest.getBatch(map, new IDataCallBack<BatchAlbumList>() {
            @Override
            public void onSuccess(@Nullable BatchAlbumList batchAlbumList) {
                mView.onSucceed(batchAlbumList);
            }

            @Override
            public void onError(int i, String s) {
                mView.onError(i, s);
            }
        });
    }

    @Override
    public void getUpdateBatch(String ids) {
        Map<String, String> map = new HashMap<String, String>();

        map.put(DTransferConstants.ALBUM_IDS, ids);

        CommonRequest.getUpdateBatch(map, new IDataCallBack<UpdateBatchList>() {
            @Override
            public void onSuccess(@Nullable UpdateBatchList updateBatchList) {
                mView.onSucceed(updateBatchList);
            }

            @Override
            public void onError(int i, String s) {
                mView.onError(i, s);
            }
        });
    }

    @Override
    public void getHotTracks(String categoryId, String tagName, String page) {
        Map<String, String> map = new HashMap<String, String>();

        map.put(DTransferConstants.CATEGORY_ID, categoryId);

        map.put(DTransferConstants.TAG_NAME, tagName);

        map.put(DTransferConstants.PAGE, page);

        CommonRequest.getHotTracks(map, new
                IDataCallBack<TrackHotList>() {
                    @Override
                    public void onSuccess(@Nullable TrackHotList trackHotList) {
                        mView.onSucceed(trackHotList);
                    }

                    @Override
                    public void onError(int i, String s) {
                        mView.onError(i, s);
                    }
                });
    }

    @Override
    public void getBatchTracks(String ids) {
        // String track_ids = "替换成用英文逗号分隔的声音ID";

        Map<String, String> map = new HashMap<String, String>();

        map.put(DTransferConstants.TRACK_IDS, ids);

        CommonRequest.getBatchTracks(map, new IDataCallBack<BatchTrackList>() {
            @Override
            public void onSuccess(@Nullable BatchTrackList batchTrackList) {
                mView.onSucceed(batchTrackList);
            }

            @Override
            public void onError(int i, String s) {
                mView.onError(i, s);
            }
        });
    }

    @Override
    public void getLastPlayTracks(String albumId, String trackId, boolean containsPaid) {


        Map<String, String> map = new HashMap<String, String>();

        map.put(DTransferConstants.ALBUM_ID, albumId);

        map.put(DTransferConstants.TRACK_ID, trackId);
        map.put(DTransferConstants.CONTAINS_PAID, containsPaid + "");
        CommonRequest.getLastPlayTracks(map, new IDataCallBack<LastPlayTrackList>() {
            @Override
            public void onSuccess(@Nullable LastPlayTrackList lastPlayTrackList) {
                mView.onSucceed(lastPlayTrackList);
            }

            @Override
            public void onError(int i, String s) {
                mView.onError(i, s);
            }
        });
    }

    @Override
    public void getCategoryBannersV2(String categoryId) {
        Map<String, String> map = new HashMap<>();
        map.put(DTransferConstants.CATEGORY_ID, categoryId);
        CommonRequest.getCategoryBannersV2(map, new IDataCallBack<BannerV2List>() {
            @Override
            public void onSuccess(@Nullable BannerV2List bannerV2List) {
                mView.onSucceed(bannerV2List);
            }

            @Override
            public void onError(int i, String s) {
                mView.onError(i, s);
            }
        });
    }

    @Override
    public void getGuessLikeAlbum(String count) {
        Map<String, String> map = new HashMap<>();
        map.put(DTransferConstants.LIKE_COUNT, count);
        CommonRequest.getGuessLikeAlbum(map, new IDataCallBack<GussLikeAlbumList>() {
            @Override
            public void onSuccess(@Nullable GussLikeAlbumList gussLikeAlbumList) {
                mView.onSucceed(gussLikeAlbumList);
            }

            @Override
            public void onError(int i, String s) {
                mView.onError(i, s);
            }
        });
    }

    @Override
    public void getColdbootGenres() {
        Map<String, String> map = new HashMap<>();
        CommonRequest.getColdbootGenres(map, new IDataCallBack<GenreList>() {
            @Override
            public void onSuccess(@Nullable GenreList genreList) {
                mView.onSucceed(genreList);
            }

            @Override
            public void onError(int i, String s) {
                mView.onError(i, s);
            }
        });
    }
}
