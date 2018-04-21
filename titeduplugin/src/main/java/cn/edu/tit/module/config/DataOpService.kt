package cn.edu.tit.module.config

import cn.edu.tit.module.model.bean.NewsTagInfo
import com.salton123.util.LogUtils
import org.jsoup.Jsoup
import java.util.*
import kotlin.collections.ArrayList

/**
 * User: newSalton@outlook.com
 * Date: 2018/4/10 上午10:40
 * ModifyTime: 上午10:40
 * Description:
 */
object DataOpService {

    fun parserNewsTagInfo(rawHtml: String): MutableList<NewsTagInfo> {
        var data: MutableList<NewsTagInfo> = ArrayList()
        try {
            val doc = Jsoup.parse(rawHtml)
            val links = doc.getElementsByClass("winstyle981422905_42787").select("TR")
            for (link in links) {
                var tagInfo: NewsTagInfo = NewsTagInfo()
                val title = link.getElementsByClass(
                        "titlestyle981422905_42787").text()
                if (title != "") {
                    tagInfo.title = title
                }
                val href = link.select("td a").attr("href")
                if (href != "") {
                    if (href.contains("http://www.tit.edu.cn")) {
                        tagInfo.url = href
                    } else {
                        tagInfo.url = "http://www.tit.edu.cn$href"
                    }
                }
                val time = link.getElementsByClass(
                        "timestyle981422905_42787").text()
                tagInfo.time = time
                if (tagInfo.time.isEmpty() || tagInfo.title.isEmpty()||tagInfo.url.isEmpty()){

                }else{
                    data.add(tagInfo)
                }
            }

        } catch (e: Exception) {
            LogUtils.e(e)
        }
        return data
    }

    fun NewsTagInfos(html: String): List<Map<String, Any>>? {
        var datas: MutableList<Map<String, Any>>? = null
        datas = ArrayList()
        val tinyWebUrl = StringBuilder()
        try {
            val doc = Jsoup.parse(html)
            val links = doc.getElementsByClass("winstyle981422905_42787")
                    .select("TR")
            for (link in links) {
                val map = HashMap<String, Any>()
                val title = link.getElementsByClass(
                        "titlestyle981422905_42787").text()
                if (title != "") {
                    map["title"] = title
                }
                val href = link.select("td a").attr("href")
                if (href != "") {
                    if (href.contains("http://www.tit.edu.cn")) {
                        map["href"] = href
                        println(href)
                    } else {
                        map["href"] = "http://www.tit.edu.cn$href"
                    }
                }
                val time = link.getElementsByClass(
                        "timestyle981422905_42787").text()
                if (time != "") {
                    map["time"] = time
                    datas.add(map)
                }
            }
            return datas
        } catch (e: Exception) {
            e.printStackTrace()
            return null
        }

    }
}