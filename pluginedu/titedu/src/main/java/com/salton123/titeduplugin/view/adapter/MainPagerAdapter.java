package com.salton123.titeduplugin.view.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Pair;

import java.util.ArrayList;
import java.util.List;

/**
 * User: newSalton@outlook.com
 * Date: 2018/4/23 下午5:40
 * ModifyTime: 下午5:40
 * Description:
 */
public class MainPagerAdapter extends FragmentStatePagerAdapter {
    private List<Pair<Fragment, String>> mData = new ArrayList<>();

    public MainPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    public void setData(List<Pair<Fragment, String>> data) {
        mData = data;
        notifyDataSetChanged();
    }

    public void add(int position, Pair<Fragment, String> item) {
        mData.add(position, item);
    }

    @Override
    public Fragment getItem(int position) {
        return mData.get(position).first;
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mData.get(position).second;
    }
}
