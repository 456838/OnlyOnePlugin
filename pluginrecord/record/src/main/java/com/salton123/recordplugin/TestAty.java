package com.salton123.recordplugin;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;

import com.salton123.base.BaseSupportActivity;
import com.salton123.recordplugin.livedata.RecordMainViewModel;

import org.jetbrains.annotations.Nullable;

import cn.sharerec.recorder.media.MP4;

/**
 * User: newSalton@outlook.com
 * Date: 2018/10/2 上午1:47
 * ModifyTime: 上午1:47
 * Description:
 */
public class TestAty extends BaseSupportActivity {
    @Override
    public int getLayout() {
        return 0;
    }
    private RecordMainViewModel mViewModel;
    @Override
    public void initVariable(Bundle bundle) {
        // mViewModel = ViewModelProviders.of(this).get(RecordMainViewModel.class);
        // mViewModel = new RecordMainViewModel(this){
        //     @Override
        //     public void onChanged(@Nullable MP4[] t) {
        //         super.onChanged(t);
        //     }
        // };
    }

    @Override
    public void initViewAndData() {

    }
}
