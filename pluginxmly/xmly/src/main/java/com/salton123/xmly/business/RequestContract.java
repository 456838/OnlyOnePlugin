package com.salton123.xmly.business;

import com.salton123.mvp.presenter.BasePresenter;
import com.salton123.mvp.view.BaseView;

/**
 * User: newSalton@outlook.com
 * Date: 2018/5/23 下午6:28
 * ModifyTime: 下午6:28
 * Description:
 */
public interface RequestContract {

    interface IRequestPresenter extends BasePresenter<IRequestView> {
        void getCategories(); //获取喜马拉雅内容分类

        void getTags(String categoryId, String type); //获取专辑标签或者声音标签

        void getAlbumList(String categoryId, String tagName, String calcDimension, String page);//根据分类和标签获取某个分类某个标签下的专辑列表（最火/最新/最多播放）

        void getTracks(String albumId, String sort, String page);//专辑浏览，根据专辑ID获取专辑下的声音列表

        void getBatch(String ids);  //批量获取专辑列表

        void getUpdateBatch(String ids);//根据专辑ID列表批获取量专辑更新提醒信息列表

        void getHotTracks(String categoryId, String tagName, String page);//根据分类和标签获取热门声音列表

        void getBatchTracks(String ids);//批量获取声音列表

        void getLastPlayTracks(String albumId, String trackId, boolean containsPaid);//根据上一次所听声音的id，获取此声音所在那一页的声音z

        void getCategoryBannersV2(String categoryId);

        void getGuessLikeAlbum(String count);

        void getColdbootGenres();

    }

    interface IRequestView extends BaseView {
        void onError(int code, String msg);

        <T> void onSucceed(T data);

    }
}
