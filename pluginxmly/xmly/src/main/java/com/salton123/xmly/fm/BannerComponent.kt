package com.salton123.xmly.fm

import android.os.Bundle
import android.widget.ImageView
import cn.bingoogolapple.bgabanner.BGABanner
import com.salton123.GlideApp
import com.salton123.mvp.ui.BaseSupportPresenterFragment
import com.salton123.util.MLog
import com.salton123.xmly.R
import com.salton123.xmly.business.RequestContract
import com.salton123.xmly.business.RequestPresenter
import com.ximalaya.ting.android.opensdk.httputil.XimalayaException
import com.ximalaya.ting.android.opensdk.model.banner.BannerV2
import com.ximalaya.ting.android.opensdk.model.banner.BannerV2List
import kotlinx.android.synthetic.main.xmly_item_play_type_banner.*

/**
 * User: newSalton@outlook.com
 * Date: 2018/5/24 下午3:27
 * ModifyTime: 下午3:27
 * Description:
 */
@Deprecated("unuse")
class BannerComponent : BaseSupportPresenterFragment<RequestContract.IRequestPresenter>(), RequestContract.IRequestView {


    override fun getLayout(): Int {
        return R.layout.xmly_item_play_type_banner
    }

    override fun initVariable(savedInstanceState: Bundle?) {
        mPresenter = RequestPresenter()
    }

    override fun initViewAndData() {
        banner.setAdapter(object : BGABanner.Adapter<ImageView, BannerV2> {
            override fun fillBannerItem(banner: BGABanner?, itemView: ImageView?, model: BannerV2?, position: Int) {
                MLog.info(TAG, "fillBannerItem,model=$model")
                itemView?.let {
                    GlideApp.with(this@BannerComponent)
                            .load(model?.bannerUrl)
                            .centerInside()
                            .thumbnail(0.5f).into(it)

                }
            }
        })
        banner.setDelegate(object : BGABanner.Delegate<ImageView, BannerV2> {
            override fun onBannerItemClick(banner: BGABanner?, itemView: ImageView?, model: BannerV2?, position: Int) {
                model?.bannerUrl?.let { shortToast(it) }
            }
        })
        mPresenter.getCategoryBannersV2("0")
    }

    private val TAG: String = "BannerComponent"

    override fun onError(code: Int, msg: String) {
        MLog.info(TAG, "code=${code},msg=${msg}")
        shortToast("code=${code},msg=${XimalayaException.getExceptionByCode(code)}")
    }

    override fun <T> onSucceed(data: T?) {
        if (data is BannerV2List) {
            banner?.let { it.setData(data.bannerV2s, null) }
        }
    }

}