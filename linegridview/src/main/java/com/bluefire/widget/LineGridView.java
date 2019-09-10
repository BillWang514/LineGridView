package com.bluefire.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.support.annotation.IntDef;
import android.util.AttributeSet;
import android.view.View;
import android.widget.GridView;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by BlueFire on 2019/9/9  14:53
 * Describe: 添加了分割线的GirdView
 */
public class LineGridView extends GridView {

    @IntDef({FULL_LINE, DASH_LINE})
    @Retention(RetentionPolicy.SOURCE)
    public @interface LineType {
    }

    public static final int FULL_LINE = 0;
    public static final int DASH_LINE = 1;

    private Context mContext;
    private float linePadding;
    private int lineColor;
    private float lineWidth;
    private int lineType;
    private float dashGap;
    private float dashWidth;

    public LineGridView(Context context) {
        super(context);
    }

    public LineGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        mContext = context;
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.LineGridView);
        lineColor = array.getColor(R.styleable.LineGridView_lineColor, Color.BLACK);
        lineWidth = array.getDimension(R.styleable.LineGridView_lineWidth, getResources().getDimension(R.dimen.default_line_width));
        linePadding = array.getDimension(R.styleable.LineGridView_linePadding, 0f);
        lineType = array.getInt(R.styleable.LineGridView_lineType, FULL_LINE);
        dashGap = array.getDimension(R.styleable.LineGridView_dashGap, getResources().getDimension(R.dimen.default_line_dashGap));
        dashWidth = array.getDimension(R.styleable.LineGridView_dashWidth, getResources().getDimension(R.dimen.default_line_dashWidth));
        array.recycle();
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
        int column = getNumColumns(); //获取列数
        int row = (int) Math.ceil(getChildCount() / (float) column); //获取行数
        int childCount = getChildCount();
        int mVSpacing = getVerticalSpacing() / 2;
        int mHSpacing = getHorizontalSpacing() / 2;
        Paint linePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        linePaint.setStyle(Paint.Style.STROKE);
        linePaint.setStrokeWidth(lineWidth);
        if (lineType == DASH_LINE)
            linePaint.setPathEffect(new DashPathEffect(new float[]{dashWidth, dashGap}, 0));
        linePaint.setColor(lineColor);
        for (int i = 1; i <= childCount; i++) {
            View child = getChildAt(i - 1);
            if (i > column * (row - 1)) {//最后一行
                if (i != column * row) {
                    if (row != 1)//不只一行
                        canvas.drawLine(child.getRight() + mHSpacing, child.getTop() - mVSpacing, child.getRight() + mHSpacing, child.getBottom() - linePadding, linePaint);
                    else//只有一行
                        canvas.drawLine(child.getRight() + mHSpacing, child.getTop() + linePadding, child.getRight() + mHSpacing, child.getBottom() - linePadding, linePaint);
                }
            } else if (i % column == 0) {//最后一列
                canvas.drawLine(child.getLeft() - mHSpacing, child.getBottom() + mVSpacing, child.getRight() - linePadding, child.getBottom() + mVSpacing, linePaint);
            } else {
                if (i == 1) {//第一行第一个
                    canvas.drawLine(child.getLeft() + linePadding, child.getBottom() + mVSpacing, child.getRight() + mHSpacing, child.getBottom() + mVSpacing, linePaint);
                    canvas.drawLine(child.getRight() + mHSpacing, child.getTop() + linePadding, child.getRight() + mHSpacing, child.getBottom() + mVSpacing, linePaint);
                } else if (i % column > 1 && i / column == 0) {//第一行（除第一个）
                    canvas.drawLine(child.getLeft() - mHSpacing, child.getBottom() + mVSpacing, child.getRight() + mHSpacing, child.getBottom() + mVSpacing, linePaint);
                    canvas.drawLine(child.getRight() + mHSpacing, child.getTop() + linePadding, child.getRight() + mHSpacing, child.getBottom() + mVSpacing, linePaint);
                } else if (i % column == 1 && i / column > 0) {//第一列（除第一个）
                    canvas.drawLine(child.getLeft() + linePadding, child.getBottom() + mVSpacing, child.getRight() + mHSpacing, child.getBottom() + mVSpacing, linePaint);
                    canvas.drawLine(child.getRight() + mHSpacing, child.getTop() - mVSpacing, child.getRight() + mHSpacing, child.getBottom() + mVSpacing, linePaint);
                } else {
                    canvas.drawLine(child.getLeft() - mHSpacing, child.getBottom() + mVSpacing, child.getRight() + mHSpacing, child.getBottom() + mVSpacing, linePaint);
                    canvas.drawLine(child.getRight() + mHSpacing, child.getTop() - mVSpacing, child.getRight() + mHSpacing, child.getBottom() + mVSpacing, linePaint);
                }
            }
        }
    }

    public int getLineType() {
        return lineType;
    }

    public void setLineType(@LineType int lineType) {
        this.lineType = lineType;
    }

    public int getLineColor() {
        return lineColor;
    }

    public void setLineColor(int color) {
        this.lineColor = color;
    }

}
