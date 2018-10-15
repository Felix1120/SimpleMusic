package com.felix.simplemusic.activity;


import android.annotation.SuppressLint;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.felix.simplemusic.R;
import com.felix.simplemusic.adapter.HomeViewPagerAdapter;
import com.felix.simplemusic.base.BaseActivity;
import com.felix.simplemusic.fragment.AlbumFragment;
import com.felix.simplemusic.fragment.LikeFragment;
import com.felix.simplemusic.fragment.SingerFragment;
import com.felix.simplemusic.fragment.SongListFragment;
import com.felix.simplemusic.presenter.HomePresenter;
import com.felix.simplemusic.presenter.IHomePresenter;
import com.felix.simplemusic.utils.MyLog;
import com.felix.simplemusic.utils.StatusBarUtil;
import com.felix.simplemusic.view.IHomeView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class HomeActivity extends BaseActivity implements IHomeView, View.OnClickListener,
        NavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.dl_activity_home)
    DrawerLayout mDrawerLayout;
    @BindView(R.id.toolbar_activity_home)
    Toolbar mToolbar;
    @BindView(R.id.view_pager_activity_home)
    ViewPager mViewPager;
    @BindView(R.id.nav_activity_home)
    NavigationView mNavigationView;
    @BindView(R.id.ll_play_bar_activity_home)
    LinearLayout playBar;

    public final static int SHOW_TITLE = 1;
    public final static int SHOW_SEARCH = 2;

    private TextView tvSongList;
    private TextView tvSinger;
    private TextView tvAlbum;
    private TextView tvLike;
    private ImageView imgMenu;
    private ImageView imgSearch;
    private LinearLayout llBody;
    private RelativeLayout rlSearch;
    private EditText etSearch;
    private ImageView imgClose;

    private IHomePresenter presenter;
    private List<Fragment> lists;
    private List<TextView> tvLists;

    @Override
    public int initLayout() {
        return R.layout.activity_home;
    }

    @Override
    public void initView() {

        //设置自定义toolbar的布局
        setCustomActionBar(R.layout.custom_action_bar);

        tvLists = new ArrayList<>();
        tvLists.add(tvSongList);
        tvLists.add(tvSinger);
        tvLists.add(tvAlbum);
        tvLists.add(tvLike);
        presenter = new HomePresenter(this, mContext, tvLists);

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                //改变文字颜色 适配fragment的切换
                presenter.changeToolbarText(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @SuppressLint("ResourceType")
    @Override
    public void initData() {
        lists = new ArrayList<>();
        lists.add(new SongListFragment());
        lists.add(new SingerFragment());
        lists.add(new AlbumFragment());
        lists.add(new LikeFragment());

        HomeViewPagerAdapter adapter = new HomeViewPagerAdapter(getSupportFragmentManager(),
                lists);

        mViewPager.setAdapter(adapter);
        mViewPager.setCurrentItem(0);
        //默认页文字颜色更改
        presenter.changeToolbarText(0);
        //给toolbar设置监听器
        presenter.tvTitleOnClick();

        imgSearch.setOnClickListener(this);
        imgMenu.setOnClickListener(this);
        imgClose.setOnClickListener(this);
        playBar.setOnClickListener(this);

        mNavigationView.setNavigationItemSelectedListener(this);

        mNavigationView.setItemTextColor(getResources()
                .getColorStateList(R.drawable.nav_menu_color_selector, null));
        mNavigationView.setItemIconTintList(getResources()
                .getColorStateList(R.drawable.nav_menu_color_selector, null));
    }

    /**
     * Toolbar设置自定义布局
     *
     * @param layout 布局文件
     */
    public void setCustomActionBar(int layout) {
        setSupportActionBar(mToolbar);
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            ActionBar.LayoutParams lp = new ActionBar.LayoutParams(
                    ActionBar.LayoutParams.MATCH_PARENT,
                    ActionBar.LayoutParams.MATCH_PARENT);
            View mActionBarView = LayoutInflater.from(mContext).inflate(layout, null);
            tvSongList = mActionBarView.findViewById(R.id.tv_song_list_custom_action_bar);
            tvSinger = mActionBarView.findViewById(R.id.tv_singer_custom_action_bar);
            tvAlbum = mActionBarView.findViewById(R.id.tv_album_custom_action_bar);
            tvLike = mActionBarView.findViewById(R.id.tv_like_custom_action_bar);
            imgMenu = mActionBarView.findViewById(R.id.img_menu_custom_action_bar);
            imgSearch = mActionBarView.findViewById(R.id.img_search_custom_action_bar);
            imgClose = mActionBarView.findViewById(R.id.img_close_custom_action_bar);
            llBody = mActionBarView.findViewById(R.id.ll_body_custom_action_bar);
            rlSearch = mActionBarView.findViewById(R.id.rl_search_custom_action_bar);
            etSearch = mActionBarView.findViewById(R.id.et_search_custom_action_bar);
            actionBar.setCustomView(mActionBarView, lp);
            actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
            actionBar.setDisplayShowCustomEnabled(true);
            actionBar.setDisplayShowHomeEnabled(false);
            actionBar.setDisplayShowTitleEnabled(false);
        }
    }

    /**
     * 设置当前fragment
     *
     * @param position fragment下标
     */
    @Override
    public void setCurrentFragment(int position) {
        mViewPager.setCurrentItem(position);
    }

    /**
     * @param flag 决定标题和搜索框的显示规则
     */
    @Override
    public void setVisible(int flag) {
        if (flag == SHOW_SEARCH) {
            llBody.setVisibility(View.GONE);
            rlSearch.setVisibility(View.VISIBLE);
        } else if (flag == SHOW_TITLE) {
            llBody.setVisibility(View.VISIBLE);
            rlSearch.setVisibility(View.GONE);
        }
    }

    @Override
    public View getRLSearch() {
        return rlSearch;
    }

    @Override
    public View getLLTitle() {
        return llBody;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_search_custom_action_bar:
                if (rlSearch.getVisibility() == View.GONE) {
                    presenter.startAnimator(SHOW_SEARCH);
                } else {

                }
                break;
            case R.id.img_menu_custom_action_bar:
                mDrawerLayout.openDrawer(GravityCompat.START);
                break;
            case R.id.img_close_custom_action_bar:
                MyLog.info("HomeActivity", "img close click");
                presenter.startAnimator(SHOW_TITLE);
                break;
            case R.id.ll_play_bar_activity_home:
                startActivity(new Intent(mContext, PlayActivity.class),
                        ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
                break;
        }
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_menu_scan_music:
                break;
            case R.id.nav_menu_theme:
                break;
            case R.id.nav_menu_time_close:
                break;
            case R.id.nav_menu_check_version:
                break;
            case R.id.nav_menu_about:
                break;
        }
        mDrawerLayout.closeDrawers();
        return true;
    }
}
