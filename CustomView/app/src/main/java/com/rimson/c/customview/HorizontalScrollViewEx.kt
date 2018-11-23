package com.rimson.c.customview

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.VelocityTracker
import android.view.View
import android.view.ViewGroup
import android.widget.Scroller

class HorizontalScrollViewEx : ViewGroup {
    private var mChildrenSize = 0
    private var mChildWidth = 0
    private var mChildIndex = 0

    // 记录上次滑动的坐标
    private var mLastX: Float? = 0f
    private var mLastY: Float? = 0f
    // 记录上次活动的坐标(onInterceptTouchEvent)
    private var mLastXIntercept: Float? = 0f
    private var mLastYIntercept: Float? = 0f

    private var mScroller = Scroller(context)
    private var mVelocityTracker = VelocityTracker.obtain()

    constructor(context: Context, attributeSet: AttributeSet?, defStyle: Int)
            : super(context, attributeSet, defStyle)

    constructor(context: Context, attributeSet: AttributeSet?) : this(context, attributeSet, 0)

    constructor(context: Context) : this(context, null)

    override fun onInterceptHoverEvent(event: MotionEvent?): Boolean {
        var intercepted = false
        val x: Float? = event?.x
        val y: Float? = event?.y

        when (event?.action) {
            MotionEvent.ACTION_DOWN -> {
                intercepted = false
                if (!mScroller.isFinished) {
                    mScroller.abortAnimation()
                    intercepted = true
                }
            }
            MotionEvent.ACTION_MOVE -> {
                val deltaX: Float? = x!! - mLastXIntercept!!
                val deltaY: Float? = y!! - mLastYIntercept!!
                intercepted = Math.abs(deltaX!!) > Math.abs(deltaY!!)
            }
            MotionEvent.ACTION_UP -> intercepted = false
        }

        mLastX = x
        mLastY = y
        mLastXIntercept = x
        mLastYIntercept = y

        return intercepted
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        mVelocityTracker.addMovement(event)
        val x: Int? = event?.x?.toInt()
        val y: Int? = event?.y?.toInt()
        when (event?.action) {
            MotionEvent.ACTION_DOWN -> {
                if (!mScroller.isFinished) {
                    mScroller.abortAnimation()
                }
            }
            MotionEvent.ACTION_MOVE -> {
                val deltaX = x!! - mLastX!!
                scrollBy(-deltaX.toInt(), 0)
            }
            MotionEvent.ACTION_UP -> {
                val scrollX = scrollX
                mVelocityTracker.computeCurrentVelocity(1000)
                val xVelocity = mVelocityTracker.xVelocity
                mChildIndex = if (Math.abs(xVelocity) >= 50) {
                    if (xVelocity > 0) {mChildIndex - 1} else {mChildIndex + 1}
                } else {
                    (scrollX + mChildWidth / 2) / mChildWidth
                }
                mChildIndex = Math.max(0, Math.min(mChildIndex, mChildIndex - 1))
                val dx = mChildIndex * mChildWidth - scrollX
                mScroller.startScroll(getScrollX(), 0, dx, 0, 500)
                mVelocityTracker.clear()
            }
        }
        mLastX = x!!.toFloat()
        mLastY = y!!.toFloat()

        return true
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        val measureWidth: Int
        val measureHeight: Int
        measureChildren(widthMeasureSpec, heightMeasureSpec)

        val widthSpecMode = MeasureSpec.getMode(widthMeasureSpec)
        val widthSpaceSize = MeasureSpec.getSize(widthMeasureSpec)
        val heightSpecMode = MeasureSpec.getMode(heightMeasureSpec)
        val heightSpaceSize = MeasureSpec.getSize(heightMeasureSpec)
        if (childCount == 0) {
            setMeasuredDimension(0, 0)
        } else if (widthSpecMode == MeasureSpec.AT_MOST && heightSpecMode == MeasureSpec.AT_MOST) {
            val childView = getChildAt(0)
            measureWidth = childView.measuredWidth * childCount
            measureHeight = childView.measuredHeight
            setMeasuredDimension(measureWidth, measureHeight)
        } else if (widthSpecMode == MeasureSpec.AT_MOST) {
            val childView = getChildAt(0)
            measureWidth = childView.measuredWidth * childCount
            setMeasuredDimension(measureWidth, heightSpaceSize)
        } else if (heightSpecMode == MeasureSpec.AT_MOST) {
            val childView = getChildAt(0)
            measureHeight = childView.measuredHeight
            setMeasuredDimension(widthSpaceSize, measureHeight)
        }
    }

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        var childLeft = 0
        mChildrenSize = childCount

        var i = 0
        while (i < childCount){
            val childView = getChildAt(i)
            if (childView.visibility != View.GONE) {
                val childWidth = childView.measuredWidth
                mChildWidth = childWidth
                childView.layout(childLeft, 0, childLeft + childWidth, childView.measuredHeight)
                childLeft += childWidth
            }
            i++
        }
    }

    override fun computeScroll() {
        if (mScroller.computeScrollOffset()) {
            scrollTo(mScroller.currX, mScroller.currY)
            postInvalidate()
        }
    }

    override fun onDetachedFromWindow() {
        mVelocityTracker.recycle()
        super.onDetachedFromWindow()
    }
}