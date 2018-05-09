package cn.edu.tit.module.model.bean

import android.os.Parcel
import android.os.Parcelable

/**
 * User: newSalton@outlook.com
 * Date: 2018/4/10 上午10:50
 * ModifyTime: 上午10:50
 * Description:
 */
data class NewsTagInfo(
        var title: String = "", var time: String = ""
        , var url: String = "", var itemList: MutableList<NewsTagInfo> = ArrayList()
) : Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            TODO("itemList")) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(title)
        parcel.writeString(time)
        parcel.writeString(url)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<NewsTagInfo> {
        override fun createFromParcel(parcel: Parcel): NewsTagInfo {
            return NewsTagInfo(parcel)
        }

        override fun newArray(size: Int): Array<NewsTagInfo?> {
            return arrayOfNulls(size)
        }
    }
}