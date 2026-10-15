package com.example.studyprogress;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;

/**
 * 自定义圆形进度条。
 * 在布局 XML 中使用时，标签必须写全限定类名：
 * <com.example.studyprogress.CirclePgBar ... />
 *
 * 进度由 ValueAnimator 驱动，onDraw() 只负责绘制，不做任何状态推进。
 */
public class CirclePgBar extends View {

    /** 默认动画时长(ms) */
    private static final long DEFAULT_DURATION = 2000L;

    private Paint mBackPaint;
    private Paint mFrontPaint;
    private Paint mTextPaint;
    private float mStrokeWidth = 50;
    private float mHalfStrokeWidth = mStrokeWidth / 2;
    private float mRadius = 200;
    private RectF mRect;
    private int mProgress = 0;
    //目标值，想改多少就改多少
    private int mTargetProgress = 90;
    private int mMax = 100;
    private int mWidth;
    private int mHeight;

    /** 驱动进度的动画器 */
    private ValueAnimator mAnimator;


    public CirclePgBar(Context context) {
        super(context);
        init();
    }

    public CirclePgBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CirclePgBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }


    //完成相关参数初始化
    private void init() {
        mBackPaint = new Paint();
        // 原来是白色，在白底上看不见，改成浅灰
        mBackPaint.setColor(0xFFDDDDDD);
        mBackPaint.setAntiAlias(true);
        mBackPaint.setStyle(Paint.Style.STROKE);
        mBackPaint.setStrokeWidth(mStrokeWidth);

        mFrontPaint = new Paint();
        mFrontPaint.setColor(Color.GREEN);
        mFrontPaint.setAntiAlias(true);
        mFrontPaint.setStyle(Paint.Style.STROKE);
        mFrontPaint.setStrokeWidth(mStrokeWidth);


        mTextPaint = new Paint();
        mTextPaint.setColor(Color.GREEN);
        mTextPaint.setAntiAlias(true);
        mTextPaint.setTextSize(80);
        mTextPaint.setTextAlign(Paint.Align.CENTER);

        // 布局编辑器预览时不会执行动画，直接显示最终进度
        if (isInEditMode()) {
            mProgress = mTargetProgress;
        }
    }


    //重写测量大小的onMeasure方法和绘制View的核心方法onDraw()
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mWidth = getRealSize(widthMeasureSpec);
        mHeight = getRealSize(heightMeasureSpec);
        setMeasuredDimension(mWidth, mHeight);
    }


    /**
     * 尺寸变化时丢弃缓存的 mRect。
     * 否则横竖屏切换 / 父布局重新布局后，圆弧会按旧尺寸画，位置错乱。
     */
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mRect = null;
    }


    /** View 挂到窗口后自动开始动画（用默认时长 0 → mTargetProgress） */
    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (!isInEditMode()) {
            start();
        }
    }

    /** View 从窗口移除时停止动画，避免无意义的回调持有 View */
    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        stop();
    }


    /** 用默认时长从 0 播放到 mTargetProgress */
    public void start() {
        start(0, mTargetProgress, DEFAULT_DURATION);
    }

    /**
     * 从 from 播放到 to
     *
     * @param from     起始进度
     * @param to       结束进度
     * @param duration 时长(ms)
     */
    public void start(int from, int to, long duration) {
        stop();
        mAnimator = ValueAnimator.ofInt(from, to);
        mAnimator.setDuration(duration);
        // 原实现是每帧 +1，速度恒定；想更顺滑可换成 DecelerateInterpolator
        mAnimator.setInterpolator(new LinearInterpolator());
        mAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mProgress = (int) animation.getAnimatedValue();
                invalidate();
            }
        });
        mAnimator.start();
    }

    /** 停止动画（保留当前进度） */
    public void stop() {
        if (mAnimator != null) {
            mAnimator.cancel();
            mAnimator = null;
        }
    }

    /** 重置进度，不会自动开始动画 */
    public void setProgress(int progress) {
        stop();
        mProgress = progress;
        invalidate();
    }

    public int getProgress() {
        return mProgress;
    }


    @Override
    protected void onDraw(Canvas canvas) {
        // onDraw 只负责绘制，不推进状态
        initRect();
        float angle = mProgress / (float) mMax * 360;
        canvas.drawCircle(mWidth / 2f, mHeight / 2f, mRadius, mBackPaint);
        canvas.drawArc(mRect, -90, angle, false, mFrontPaint);
        canvas.drawText(mProgress + "%",
                mWidth / 2f + mHalfStrokeWidth,
                mHeight / 2f + mHalfStrokeWidth,
                mTextPaint);
    }

    public int getRealSize(int measureSpec) {
        int result = 1;
        int mode = MeasureSpec.getMode(measureSpec);
        int size = MeasureSpec.getSize(measureSpec);

        if (mode == MeasureSpec.AT_MOST || mode == MeasureSpec.UNSPECIFIED) {
            //自己计算
            result = (int) (mRadius * 2 + mStrokeWidth);
        } else {
            result = size;
        }

        return result;
    }

    private void initRect() {
        if (mRect == null) {
            mRect = new RectF();
            int viewSize = (int) (mRadius * 2);
            int left = (mWidth - viewSize) / 2;
            int top = (mHeight - viewSize) / 2;
            int right = left + viewSize;
            int bottom = top + viewSize;
            mRect.set(left, top, right, bottom);
        }
    }


}
