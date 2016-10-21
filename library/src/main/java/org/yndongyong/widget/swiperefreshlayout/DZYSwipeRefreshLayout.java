package org.yndongyong.widget.swiperefreshlayout;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.ProgressBar;

/**
 * Created by Dong on 2016/10/21.
 */

public class DZYSwipeRefreshLayout extends SwipeRefreshLayout {

    private ProgressBar mLoadView;
    private Context mContext;


    private int mSlop;
    private int mYdown;
    private int mlastY;


    private RecyclerView mRecyclerView;

    private boolean isRefreshing = false;

    public DZYSwipeRefreshLayout(Context context) {
        super(context);
        intiLoadMoreView();
    }


    public DZYSwipeRefreshLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;

        View childView = getChildAt(1);//因为是集成SwipeRefreshLayout，其中已经自己添加了0 headView
        if (!(childView instanceof RecyclerView)) {
            throw new IllegalArgumentException("child view must be RecyclerView");
        }
        mRecyclerView = (RecyclerView) childView;

        mSlop = ViewConfiguration.get(context).getScaledTouchSlop();
        intiLoadMoreView();

    }

    /**
     * 初始化底部的view
     */
    private void intiLoadMoreView() {
        mLoadView = new ProgressBar(mContext);
//        mLoadView.setBackgroundColor(CIRCLE_BG_LIGHT);
        mLoadView.setVisibility(View.GONE);

        addView(mLoadView, getChildCount());
    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        int action = ev.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                mYdown = (int) ev.getRawY();
                break;
            case MotionEvent.ACTION_UP:
                mlastY = (int) ev.getRawY();

                if (shouldLoadMore()) {
                    
                }

                break;
        }


        return super.dispatchTouchEvent(ev);
    }

    private boolean shouldLoadMore() {

    
        return false;
    }

    private boolean isPullUp() {
        if ((mlastY - mYdown) >= mSlop)
            return true;
        return false;
    }

    /**
     * @param onStatus When onStatus is True show loadview,otherWith hide
     */
    private void toggleLoadViewStatus(boolean onStatus) {
        if (onStatus) {
            mLoadView.setVisibility(View.VISIBLE);
        } else {
            mLoadView.setVisibility(View.GONE);

        }
    }

}
