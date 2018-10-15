package com.felix.simplemusic.view;

import android.view.View;

/**
 * Created by chaofei.xue on 2018/8/7.
 */

public interface IHomeView {
    void setCurrentFragment(int position);
    void setVisible(int flag);
    View getRLSearch();
    View getLLTitle();
}
