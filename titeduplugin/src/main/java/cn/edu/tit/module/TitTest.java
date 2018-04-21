package cn.edu.tit.module;

import java.util.List;

import cn.edu.tit.module.api.TitNewsCategory;
import cn.edu.tit.module.config.DataOpService;
import cn.edu.tit.module.config.RetrofitHelper;
import cn.edu.tit.module.model.bean.NewsTagInfo;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Function;

/**
 * User: newSalton@outlook.com
 * Date: 2018/4/10 上午11:47
 * ModifyTime: 上午11:47
 * Description:
 */
public class TitTest {

    public static void test() {
        RetrofitHelper.INSTANCE.getTitNewsApi()
                .getNewsCategory(TitNewsCategory.CATEGORY_IMPORTANCE_NEWS, 0, "NextPage")
                .flatMap(new Function<String, ObservableSource<List<NewsTagInfo>>>() {
                    @Override
                    public ObservableSource<List<NewsTagInfo>> apply(String s) throws Exception {
                        return Observable.just(DataOpService.INSTANCE.parserNewsTagInfo(s));
                    }
                });
    }
}
