package cn.edu.tit.module.model.bean

/**
 * User: newSalton@outlook.com
 * Date: 2018/4/10 上午10:50
 * ModifyTime: 上午10:50
 * Description:
 */
data class NewsTagInfo(
        var title: String = "", var time: String = ""
        , var url: String = "", var itemList: MutableList<NewsTagInfo> = ArrayList()
)