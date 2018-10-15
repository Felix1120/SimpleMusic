package com.felix.simplemusic.presenter;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.felix.simplemusic.R;
import com.felix.simplemusic.activity.HomeActivity;
import com.felix.simplemusic.model.HomeModel;
import com.felix.simplemusic.model.IHomeModel;
import com.felix.simplemusic.utils.MyLog;
import com.felix.simplemusic.view.IHomeView;

import java.util.List;

/**
 * Created by chaofei.xue on 2018/8/7.
 */

public class HomePresenter implements IHomePresenter {

    private IHomeView homeView;
    private IHomeModel homeModel;
    private Context mContext;
    private List<TextView> list;

    public HomePresenter(IHomeView homeView, Context mContext, List<TextView> list) {
        this.homeView = homeView;
        this.mContext = mContext;
        this.list = list;
        homeModel = new HomeModel();
    }

    /**
     * 根据fragment的位置切换对应bar的字体颜色
     *
     * @param position 当前fragment的位置
     */
    @Override
    public void changeToolbarText(int position) {
        if (list == null) {
            return;
        }
        MyLog.info("HomePresenter", "position = " + position);
        switch (position) {
            case 0:
                for (int i = 0; i < list.size(); i++) {
                    if (i == 0) {
                        list.get(i).setTextColor(mContext.getResources().
                                getColor(R.color.color_text_gray_2));
                    } else {
                        list.get(i).setTextColor(mContext.getResources().
                                getColor(R.color.color_text_gray_1));
                    }
                }
                break;
            case 1:
                for (int i = 0; i < list.size(); i++) {
                    if (i == 1) {
                        list.get(i).setTextColor(mContext.getResources().
                                getColor(R.color.color_text_gray_2));
                    } else {
                        list.get(i).setTextColor(mContext.getResources().
                                getColor(R.color.color_text_gray_1));
                    }
                }
                break;
            case 2:
                for (int i = 0; i < list.size(); i++) {
                    if (i == 2) {
                        list.get(i).setTextColor(mContext.getResources().
                                getColor(R.color.color_text_gray_2));
                    } else {
                        list.get(i).setTextColor(mContext.getResources().
                                getColor(R.color.color_text_gray_1));
                    }
                }
                break;
            case 3:
                for (int i = 0; i < list.size(); i++) {
                    if (i == 3) {
                        list.get(i).setTextColor(mContext.getResources().
                                getColor(R.color.color_text_gray_2));
                    } else {
                        list.get(i).setTextColor(mContext.getResources().
                                getColor(R.color.color_text_gray_1));
                    }
                }
                break;
        }

    }


    /**
     * 点击bar的标题切换到对应的fragment
     */
    @Override
    public void tvTitleOnClick() {
        list.get(0).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                homeView.setCurrentFragment(0);
            }
        });

        list.get(1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                homeView.setCurrentFragment(1);
            }
        });

        list.get(2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                homeView.setCurrentFragment(2);
            }
        });

        list.get(3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                homeView.setCurrentFragment(3);
            }
        });
    }

    /**
     * 播放动画
     */
    @Override
    public void startAnimator(int flag) {

        RelativeLayout rlSearch = (RelativeLayout) homeView.getRLSearch();
        LinearLayout llTitle = (LinearLayout) homeView.getLLTitle();

        if (flag == HomeActivity.SHOW_SEARCH) {
            ObjectAnimator animator1 = ObjectAnimator.ofFloat(llTitle, "alpha", 1f, 0f);
            animator1.setDuration(200);
            animator1.start();
            animator1.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    super.onAnimationEnd(animation);
                    homeView.setVisible(HomeActivity.SHOW_SEARCH);
                    animation.cancel();
                }
            });

            ObjectAnimator animator2 = ObjectAnimator.ofFloat(rlSearch, "translationX", 300f, 0f);
            ObjectAnimator animator3 = ObjectAnimator.ofFloat(rlSearch, "alpha", 0f, 1f);
            AnimatorSet set = new AnimatorSet();
            set.playTogether(animator2);
            set.playTogether(animator3);
            set.setDuration(350);
            set.start();
            set.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    super.onAnimationEnd(animation);
                    animation.cancel();
                }
            });
        } else if (flag == HomeActivity.SHOW_TITLE) {
            ObjectAnimator animator1 = ObjectAnimator.ofFloat(rlSearch, "alpha", 1f, 0f);
            animator1.setDuration(250);
            animator1.start();
            animator1.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    super.onAnimationEnd(animation);
                    homeView.setVisible(HomeActivity.SHOW_TITLE);
                    animation.cancel();
                }
            });

            ObjectAnimator animator2 = ObjectAnimator.ofFloat(llTitle, "alpha", 0f, 1f);
            animator2.setDuration(400);
            animator2.start();
            animator2.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    super.onAnimationEnd(animation);
                    animation.cancel();
                }
            });
        }
    }
}
