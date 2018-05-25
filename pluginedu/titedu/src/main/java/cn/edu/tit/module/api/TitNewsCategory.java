package cn.edu.tit.module.api;

import android.support.annotation.IntDef;

/**
 * User: newSalton@outlook.com
 * Date: 2018/4/8 下午9:51
 * ModifyTime: 下午9:51
 * Description:
 */
@IntDef({
        TitNewsCategory.CATEGORY_IMPORTANCE_NEWS, TitNewsCategory.CATEGORY_NOTIFICATION,
        TitNewsCategory.CATEGORY_PICTURE_NEWS, TitNewsCategory.CATEGORY_NOTICE,
        TitNewsCategory.CATEGORY_DEPARTMENT_NEWS, TitNewsCategory.CATEGORY_OFFICE_NEWS,
        TitNewsCategory.CATEGORY_CAMPUS_CULTURE
})
public @interface TitNewsCategory {
    int CATEGORY_IMPORTANCE_NEWS = 1029;    //学院要闻
    int CATEGORY_NOTIFICATION = 1030;       //学院通知
    int CATEGORY_PICTURE_NEWS = 1026;       //图片新闻
    int CATEGORY_NOTICE = 1152;         //学院公告
    int CATEGORY_DEPARTMENT_NEWS = 1118;    //系部动态
    int CATEGORY_OFFICE_NEWS = 1153;    //处室动态
    int CATEGORY_CAMPUS_CULTURE = 1154;    //校园文化
}
