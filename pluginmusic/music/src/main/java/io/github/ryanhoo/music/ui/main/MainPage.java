package io.github.ryanhoo.music.ui.main;

import android.Manifest;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.OnCheckedChanged;
import io.github.ryanhoo.music.R;
import io.github.ryanhoo.music.ui.base.BaseFragment;
import io.github.ryanhoo.music.ui.local.LocalFilesFragment;
import io.github.ryanhoo.music.ui.music.MusicPlayerFragment;
import io.github.ryanhoo.music.ui.playlist.PlayListFragment;
import io.github.ryanhoo.music.ui.settings.SettingsFragment;
import me.weyye.hipermission.HiPermission;
import me.weyye.hipermission.PermissionCallback;
import me.weyye.hipermission.PermissionItem;

/**
 * User: newSalton@outlook.com
 * Date: 2018/5/9 下午9:48
 * ModifyTime: 下午9:48
 * Description:
 */
public class MainPage extends FrameLayout {
    static final int DEFAULT_PAGE_INDEX = 2;

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.view_pager)
    ViewPager viewPager;
    @BindViews({R.id.radio_button_play_list, R.id.radio_button_music, R.id.radio_button_local_files, R.id.radio_button_settings})
    List<RadioButton> radioButtons;

    String[] mTitles;

    static {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }

    public MainPage(@NonNull Context context) {
        super(context);
    }

    public MainPage(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MainPage(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void init() {
        LayoutInflater.from(getContext()).inflate(R.layout.activity_main, this);
        ButterKnife.bind(this);
        checkPermission();
    }

    public void setup() {

        // Main Controls' Titles
        mTitles = getResources().getStringArray(R.array.mp_main_titles);

        // Fragments
        BaseFragment[] fragments = new BaseFragment[mTitles.length];
        fragments[0] = new PlayListFragment();
        fragments[1] = new MusicPlayerFragment();
        fragments[2] = new LocalFilesFragment();
        fragments[3] = new SettingsFragment();
        if (getContext() instanceof AppCompatActivity) {
            AppCompatActivity aty = (AppCompatActivity) getContext();
            aty.setSupportActionBar(toolbar);
            // aty.moveTaskToBack()
            // Inflate ViewPager
            MainPagerAdapter adapter = new MainPagerAdapter(aty.getSupportFragmentManager(), mTitles, fragments);
            viewPager.setAdapter(adapter);
            viewPager.setOffscreenPageLimit(adapter.getCount() - 1);
            viewPager.setPageMargin(getResources().getDimensionPixelSize(R.dimen.mp_margin_large));
            viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                    // Empty
                }

                @Override
                public void onPageScrollStateChanged(int state) {
                    // Empty
                }

                @Override
                public void onPageSelected(int position) {
                    radioButtons.get(position).setChecked(true);
                }
            });

            radioButtons.get(DEFAULT_PAGE_INDEX).setChecked(true);
        }
    }
    //
    // @Override
    // public void onBackPressed() {
    //     moveTaskToBack(true);
    // }

    @OnCheckedChanged({R.id.radio_button_play_list, R.id.radio_button_music, R.id.radio_button_local_files, R.id.radio_button_settings})
    public void onRadioButtonChecked(RadioButton button, boolean isChecked) {
        if (isChecked) {
            onItemChecked(radioButtons.indexOf(button));
        }
    }

    private void onItemChecked(int position) {
        viewPager.setCurrentItem(position);
        toolbar.setTitle(mTitles[position]);
    }

    /**
     * 6.0以下版本(系统自动申请) 不会弹框
     * 有些厂商修改了6.0系统申请机制，他们修改成系统自动申请权限了
     */
    private void checkPermission() {
        List<PermissionItem> permissionItems = new ArrayList();
        // permissionItems.add(new PermissionItem(Manifest.permission.READ_PHONE_STATE, "手机状态", R.drawable.permission_ic_phone));
        permissionItems.add(new PermissionItem(Manifest.permission.READ_EXTERNAL_STORAGE, "读取存储空间", R.drawable.permission_ic_storage));
        // permissionItems.add(new PermissionItem(Manifest.permission.WRITE_EXTERNAL_STORAGE, "写入存储空间", R.drawable.permission_ic_storage));
        HiPermission.create(getContext())
                .title("亲爱的上帝")
                .msg("为了能够正常使用，请开启这些权限吧！")
                .permissions(permissionItems)
                .style(R.style.PermissionDefaultBlueStyle)
                .animStyle(R.style.PermissionAnimScale)
                .checkMutiPermission(new PermissionCallback() {
                    @Override
                    public void onClose() {
                        Log.i("aa", "permission_onClose");
                        Toast.makeText(getContext(), "用户关闭了权限", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onFinish() {
                        Toast.makeText(getContext(), "初始化完毕", Toast.LENGTH_LONG).show();
                        setup();
                    }

                    @Override
                    public void onDeny(String s, int i) {
                        Log.i("aa", "permission_onDeny");
                    }

                    @Override
                    public void onGuarantee(String s, int i) {
                        Log.i("aa", "permission_onGuarantee");
                        setup();
                    }
                });
    }
}
