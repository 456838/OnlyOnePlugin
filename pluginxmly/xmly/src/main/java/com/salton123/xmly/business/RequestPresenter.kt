package com.salton123.xmly.business

import com.salton123.mvp.presenter.RxPresenter
import com.salton123.util.RxUtils
import com.ximalaya.ting.android.opensdk.constants.DTransferConstants
import com.ximalaya.ting.android.opensdk.datatrasfer.CommonRequest
import com.ximalaya.ting.android.opensdk.datatrasfer.IDataCallBack
import com.ximalaya.ting.android.opensdk.model.album.AlbumList
import com.ximalaya.ting.android.opensdk.model.album.BatchAlbumList
import com.ximalaya.ting.android.opensdk.model.album.CategoryRecommendAlbumsList
import com.ximalaya.ting.android.opensdk.model.album.DiscoveryRecommendAlbumsList
import com.ximalaya.ting.android.opensdk.model.album.GussLikeAlbumList
import com.ximalaya.ting.android.opensdk.model.album.UpdateBatchList
import com.ximalaya.ting.android.opensdk.model.banner.BannerV2List
import com.ximalaya.ting.android.opensdk.model.category.CategoryList
import com.ximalaya.ting.android.opensdk.model.coldboot.GenreList
import com.ximalaya.ting.android.opensdk.model.metadata.MetaDataList
import com.ximalaya.ting.android.opensdk.model.tag.TagList
import com.ximalaya.ting.android.opensdk.model.track.BatchTrackList
import com.ximalaya.ting.android.opensdk.model.track.LastPlayTrackList
import com.ximalaya.ting.android.opensdk.model.track.TrackHotList
import com.ximalaya.ting.android.opensdk.model.track.TrackList
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.FlowableOnSubscribe
import java.util.*

/**
 * User: newSalton@outlook.com
 * Date: 2018/5/23 下午6:36
 * ModifyTime: 下午6:36
 * Description:
 */
class RequestPresenter : RxPresenter<RequestContract.IRequestView>(), RequestContract.IRequestPresenter {
    override fun getMetadataList(categoryId: String) {
        val map = HashMap<String, String>()
        map[DTransferConstants.CATEGORY_ID] = categoryId
        CommonRequest.getMetadataList(map, object : IDataCallBack<MetaDataList> {
            override fun onSuccess(categoryList: MetaDataList?) {
                mView?.let { it.onSucceed(categoryList) }
            }

            override fun onError(i: Int, s: String) {
                mView?.let { it.onError(i, s) }
            }
        })
    }

    override fun getCategories() {
        val map = HashMap<String, String>()
        CommonRequest.getCategories(map, object : IDataCallBack<CategoryList> {
            override fun onSuccess(categoryList: CategoryList?) {
                mView?.let { it.onSucceed(categoryList) }
            }

            override fun onError(i: Int, s: String) {
                mView?.let { it.onError(i, s) }
            }
        })
    }

    override fun getTags(categoryId: String, type: String) {
        val map = HashMap<String, String>()
        map[DTransferConstants.CATEGORY_ID] = categoryId
        map[DTransferConstants.TYPE] = type
        CommonRequest.getTags(map, object : IDataCallBack<TagList> {
            override fun onSuccess(tagList: TagList?) {
                mView?.let { it.onSucceed(tagList) }
            }

            override fun onError(i: Int, s: String) {
                mView?.let { it.onError(i, s) }
            }
        })
    }

    override fun getAlbumList(categoryId: String, tagName: String, calcDimension: String, page: String) {
        val map = HashMap<String, String>()
        map[DTransferConstants.CATEGORY_ID] = categoryId
        map[DTransferConstants.TAG_NAME] = tagName
        map[DTransferConstants.CALC_DIMENSION] = calcDimension
        map[DTransferConstants.PAGE] = page
        CommonRequest.getAlbumList(map, object : IDataCallBack<AlbumList> {
            override fun onSuccess(albumList: AlbumList?) {
                mView?.let { it.onSucceed(albumList) }
            }

            override fun onError(i: Int, s: String) {
                mView?.let { it.onError(i, s) }
            }
        })
    }

    override fun getTracks(albumId: String, sort: String, page: String) {
        Flowable.create(FlowableOnSubscribe<TrackList> {
            val map = HashMap<String, String>()
            map[DTransferConstants.ALBUM_ID] = albumId
            map[DTransferConstants.SORT] = sort
            map[DTransferConstants.PAGE] = page
            CommonRequest.getTracks(map, object : IDataCallBack<TrackList> {
                override fun onSuccess(trackList: TrackList?) {
                    it.onNext(trackList!!)
                }

                override fun onError(i: Int, s: String) {
                    mView?.let { it.onError(i, s) }
                }
            })
        }, BackpressureStrategy.DROP)
            .compose(RxUtils.schedulersTransformerForFlowable())
            .subscribe {
                val tracks = it
                mView?.let { it.onSucceed(tracks) }
            }
    }

    override fun getBatch(ids: String) {
        val map = HashMap<String, String>()
        map[DTransferConstants.ALBUM_IDS] = ids
        CommonRequest.getBatch(map, object : IDataCallBack<BatchAlbumList> {
            override fun onSuccess(batchAlbumList: BatchAlbumList?) {
                mView?.let { it.onSucceed(batchAlbumList) }
            }

            override fun onError(i: Int, s: String) {
                mView?.let { it.onError(i, s) }
            }
        })
    }

    override fun getUpdateBatch(ids: String) {
        val map = HashMap<String, String>()

        map[DTransferConstants.ALBUM_IDS] = ids

        CommonRequest.getUpdateBatch(map, object : IDataCallBack<UpdateBatchList> {
            override fun onSuccess(updateBatchList: UpdateBatchList?) {
                mView?.let { it.onSucceed(updateBatchList) }
            }

            override fun onError(i: Int, s: String) {
                mView?.let { it.onError(i, s) }
            }
        })
    }

    override fun getHotTracks(categoryId: String, tagName: String, page: String) {
        val map = HashMap<String, String>()

        map[DTransferConstants.CATEGORY_ID] = categoryId

        map[DTransferConstants.TAG_NAME] = tagName

        map[DTransferConstants.PAGE] = page

        CommonRequest.getHotTracks(map, object : IDataCallBack<TrackHotList> {
            override fun onSuccess(trackHotList: TrackHotList?) {
                mView?.let { it.onSucceed(trackHotList) }
            }

            override fun onError(i: Int, s: String) {
                mView?.let { it.onError(i, s) }
            }
        })
    }

    override fun getBatchTracks(ids: String) {
        // String track_ids = "替换成用英文逗号分隔的声音ID";

        val map = HashMap<String, String>()

        map[DTransferConstants.TRACK_IDS] = ids

        CommonRequest.getBatchTracks(map, object : IDataCallBack<BatchTrackList> {
            override fun onSuccess(batchTrackList: BatchTrackList?) {
                mView?.let { it.onSucceed(batchTrackList) }
            }

            override fun onError(i: Int, s: String) {
                mView?.let { it.onError(i, s) }
            }
        })
    }

    override fun getLastPlayTracks(albumId: String, trackId: String, containsPaid: Boolean) {


        val map = HashMap<String, String>()

        map[DTransferConstants.ALBUM_ID] = albumId

        map[DTransferConstants.TRACK_ID] = trackId
        map[DTransferConstants.CONTAINS_PAID] = containsPaid.toString() + ""
        CommonRequest.getLastPlayTracks(map, object : IDataCallBack<LastPlayTrackList> {
            override fun onSuccess(lastPlayTrackList: LastPlayTrackList?) {
                mView?.let { it.onSucceed(lastPlayTrackList) }
            }

            override fun onError(i: Int, s: String) {
                mView?.let { it.onError(i, s) }
            }
        })
    }

    override fun getCategoryRecommendAlbums(categoryId: String, count: String) {
        val map = HashMap<String, String>()

        map[DTransferConstants.CATEGORY_ID] = categoryId

        map[DTransferConstants.DISPLAY_COUNT] = count
        CommonRequest.getCategoryRecommendAlbums(map, object : IDataCallBack<CategoryRecommendAlbumsList> {
            override fun onSuccess(lastPlayTrackList: CategoryRecommendAlbumsList?) {
                mView?.let { it.onSucceed(lastPlayTrackList) }
            }

            override fun onError(i: Int, s: String) {
                mView?.let { it.onError(i, s) }
            }
        })
    }

    override fun getDiscoveryRecommendAlbums(count: String) {

        val map = HashMap<String, String>()
        map[DTransferConstants.DISPLAY_COUNT] = count
        CommonRequest.getDiscoveryRecommendAlbums(map, object : IDataCallBack<DiscoveryRecommendAlbumsList> {
            override fun onSuccess(lastPlayTrackList: DiscoveryRecommendAlbumsList?) {
                mView?.let { it.onSucceed(lastPlayTrackList) }
            }

            override fun onError(i: Int, s: String) {
                mView?.let { it.onError(i, s) }
            }
        })
    }


    override fun getCategoryBannersV2(categoryId: String) {
        val map = HashMap<String, String>()
        map[DTransferConstants.CATEGORY_ID] = categoryId
        CommonRequest.getCategoryBannersV2(map, object : IDataCallBack<BannerV2List> {
            override fun onSuccess(bannerV2List: BannerV2List?) {
                mView?.let { it.onSucceed(bannerV2List) }
            }

            override fun onError(i: Int, s: String) {
                mView?.let { it.onError(i, s) }
            }
        })
    }

    override fun getGuessLikeAlbum(count: String) {
        val map = HashMap<String, String>()
        map[DTransferConstants.LIKE_COUNT] = count
        CommonRequest.getGuessLikeAlbum(map, object : IDataCallBack<GussLikeAlbumList> {
            override fun onSuccess(gussLikeAlbumList: GussLikeAlbumList?) {
                mView?.let { it.onSucceed(gussLikeAlbumList) }
            }

            override fun onError(i: Int, s: String) {
                mView?.let { it.onError(i, s) }
            }
        })
    }

    override fun getColdbootGenres() {
        val map = HashMap<String, String>()
        CommonRequest.getColdbootGenres(map, object : IDataCallBack<GenreList> {
            override fun onSuccess(genreList: GenreList?) {
                mView?.let { it.onSucceed(genreList) }
            }

            override fun onError(i: Int, s: String) {
                mView?.let { it.onError(i, s) }
            }
        })
    }
}
