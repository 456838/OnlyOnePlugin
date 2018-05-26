package com.salton123.xmly.model.type;

import android.support.annotation.NonNull;


/**
 * User: newSalton@outlook.com
 * Date: 2017/7/28 20:57
 * ModifyTime: 20:57
 * Description:
 */
public class MultiTypeItem implements Comparable<MultiTypeItem> {
    public static final int TYPE_GUESSLIKE = 0;     //猜你喜欢
    public static final int TYPE_MUSIC = 1;     //音乐
    public static final int TYPE_XIANGSHENG = 2;     //相声
    public static final int TYPE_TALK_SHOW = 3;     //脱口秀
    public static final int TYPE_TELE = 4;     //电台
    public static final int TYPE_COM = 5;     //商业财经
    public static final int TYPE_GONGKAIKE = 6;     //名校公开课
    private int viewType;
    private BasePlayType item;

    public MultiTypeItem(int viewType, BasePlayType item) {
        this.viewType = viewType;
        this.item = item;
    }

    public int getViewType() {
        return viewType;
    }

    public void setViewType(int viewType) {
        this.viewType = viewType;
    }

    public BasePlayType getItem() {
        return item;
    }

    public void setItem(BasePlayType item) {
        this.item = item;
    }


    @Override
    public String toString() {
        return "MultiTypeItem{" +
                "viewType=" + viewType +
                ", item=" + item +
                '}';
    }

    @Override
    public int compareTo(@NonNull MultiTypeItem another) {
        if (another.viewType > viewType) {
            return -1;
        } else if (another.viewType < viewType) {
            return 1;
        } else {
            return 0;
        }
    }
}
