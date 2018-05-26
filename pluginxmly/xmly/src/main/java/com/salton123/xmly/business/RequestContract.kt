package com.salton123.xmly.business

import com.salton123.mvp.presenter.BasePresenter
import com.salton123.mvp.view.BaseView

/**
 * User: newSalton@outlook.com
 * Date: 2018/5/23 下午6:28
 * ModifyTime: 下午6:28
 * Description:
 */
interface RequestContract {

    interface IRequestPresenter : BasePresenter<IRequestView> {
        fun getCategories()  //获取喜马拉雅内容分类

        fun getTags(categoryId: String, type: String)  //获取专辑标签或者声音标签

        fun getAlbumList(categoryId: String, tagName: String, calcDimension: String, page: String) //根据分类和标签获取某个分类某个标签下的专辑列表（最火/最新/最多播放）

        fun getTracks(albumId: String, sort: String, page: String) //专辑浏览，根据专辑ID获取专辑下的声音列表

        fun getBatch(ids: String)   //批量获取专辑列表

        fun getUpdateBatch(ids: String) //根据专辑ID列表批获取量专辑更新提醒信息列表

        fun getHotTracks(categoryId: String, tagName: String, page: String) //根据分类和标签获取热门声音列表

        fun getBatchTracks(ids: String) //批量获取声音列表

        fun getLastPlayTracks(albumId: String, trackId: String, containsPaid: Boolean) //根据上一次所听声音的id，获取此声音所在那一页的声音z

        fun getCategoryRecommendAlbums(categoryId: String, count: String) //获取运营人员为某个分类配置的标签维度专辑推荐模块列表

        fun getDiscoveryRecommendAlbums(count: String)  //获取运营人员在发现页配置的分类维度专辑推荐模块的列表

        fun getCategoryBannersV2(categoryId: String)

        fun getGuessLikeAlbum(count: String)

        fun getColdbootGenres()

    }

    interface IRequestView : BaseView {
        fun onError(code: Int, msg: String)

        fun <T> onSucceed(data: T?)

    }
}
