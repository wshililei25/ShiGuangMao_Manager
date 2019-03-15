package com.yizhipin.base.recyclerview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.jcodecraeer.xrecyclerview.ArrowRefreshHeader;
import com.jcodecraeer.xrecyclerview.LoadingMoreFooter;

public class XGridItemDecoration extends RecyclerView.ItemDecoration {
    private static final int[] ATTRS = new int[]{android.R.attr.listDivider};
    private Drawable mHorizontalDrawable;
    private Drawable mVerticalDrawable;

    public XGridItemDecoration(Context context) {
        final TypedArray a = context.obtainStyledAttributes(ATTRS);
        mHorizontalDrawable = mVerticalDrawable = a.getDrawable(0);
        a.recycle();
    }

    public XGridItemDecoration(Drawable horizontalDrawable, Drawable verticalDrawable) {
        this.mHorizontalDrawable = horizontalDrawable;
        this.mVerticalDrawable = verticalDrawable;
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        drawHorizontal(c, parent);
        drawVertical(c, parent);
    }

    private int getSpanCount(RecyclerView parent) {
        // 列数
        int spanCount = -1;
        RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {
            spanCount = ((GridLayoutManager) layoutManager).getSpanCount();
        }
        return spanCount;
    }

    public void drawHorizontal(Canvas c, RecyclerView parent) {
        int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            if (i == 0 || i == childCount - 1)
                continue;
            final View child = parent.getChildAt(i);
            final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child
                    .getLayoutParams();
            final int left = child.getLeft() - params.leftMargin;
            final int right = child.getRight() + params.rightMargin
                    + mHorizontalDrawable.getIntrinsicWidth();
            final int top = child.getBottom() + params.bottomMargin;
            final int bottom = top + mHorizontalDrawable.getIntrinsicHeight();
            mHorizontalDrawable.setBounds(left, top, right, bottom);
            mHorizontalDrawable.draw(c);
        }
    }

    public void drawVertical(Canvas c, RecyclerView parent) {
        final int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            if (i == 0 || i == childCount - 1)
                continue;
            final View child = parent.getChildAt(i);

            final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child
                    .getLayoutParams();
            final int top = child.getTop() - params.topMargin;
            final int bottom = child.getBottom() + params.bottomMargin + mHorizontalDrawable.getIntrinsicHeight();//加上水平分割线的高度
            final int left = child.getRight() + params.rightMargin;
            final int right = left + mVerticalDrawable.getIntrinsicWidth();

            mVerticalDrawable.setBounds(left, top, right, bottom);
            mVerticalDrawable.draw(c);
        }
    }

    /**
     * 是否是最后一行
     */
    private boolean isLastRow(int itemPosition, RecyclerView parent) {
        int spanCount = getSpanCount(parent);
        RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
        //有多少列
        if (layoutManager instanceof GridLayoutManager) {
            int childCount = parent.getAdapter().getItemCount();

            double count = Math.ceil((double) childCount / (double) spanCount);//总行数
            double currentCount = Math.ceil((double) (itemPosition + 1) / spanCount);//当前行数

            //最后当前数量小于总的
            if (currentCount < count) {
                return false;
            }
        }
        return true;
    }


    /**
     * 判断是否是最后一列
     */
    private boolean isLastColum(int itemPosition, RecyclerView parent) {
        RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
        //有多少列
        if (layoutManager instanceof GridLayoutManager) {
            int spanCount = getSpanCount(parent);
            if ((itemPosition + 1) % spanCount == 0) {//因为是从0可以所以要将ItemPosition先加1
                return true;
            }
        }
        return false;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        if (view instanceof ArrowRefreshHeader || view instanceof LoadingMoreFooter){
            return;
        }
        if (isLastColum(parent.getChildLayoutPosition(view)-1, parent))// 如果是最后一列，则不需要绘制右边
        {
            outRect.set(0, 0, 0, mHorizontalDrawable.getIntrinsicHeight());
        }else {
            outRect.set(0, 0, mVerticalDrawable.getIntrinsicWidth(), mHorizontalDrawable.getIntrinsicHeight());
        }
    }
}