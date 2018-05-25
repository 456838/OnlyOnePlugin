package cn.edu.tit.module

import cn.edu.tit.module.api.TitNewsCategory
import cn.edu.tit.module.config.DataOpService
import cn.edu.tit.module.config.RetrofitHelper
import cn.edu.tit.module.model.bean.NewsTagInfo
import io.reactivex.Observable

/**
 * User: newSalton@outlook.com
 * Date: 2018/4/10 上午11:40
 * ModifyTime: 上午11:40
 * Description:
 */
object TitEduNews {
    fun getTitNewsList(page: Int, actiontype: String): Observable<MutableList<NewsTagInfo>> {
        return RetrofitHelper.getTitNewsApi()
                .getNewsCategory(TitNewsCategory.CATEGORY_IMPORTANCE_NEWS, page, actiontype)
                .flatMap { s -> Observable.just(DataOpService.parserNewsTagInfo(s)) }
    }
}