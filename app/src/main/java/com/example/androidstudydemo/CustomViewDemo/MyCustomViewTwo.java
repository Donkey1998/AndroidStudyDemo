package com.example.androidstudydemo.CustomViewDemo;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.example.androidstudydemo.R;

public class MyCustomViewTwo extends View {
    private static final String TAG = MyCustomViewTwo.class.getName();
    private int defalutSize;

    /**
     * 在java代码里new的时候会用到
     * @param context
     */
    public MyCustomViewTwo(Context context) {
        super(context);
    }


    /**
     * 在xml布局文件中使用时自动调用
     * @param context
     */
    public MyCustomViewTwo(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        //第二个参数就是我们在styles.xml文件中的<declare-styleable>标签
        //即属性集合的标签，在R文件中名称为R.styleable+name
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.MyCustomViewTwo);

        //第一个参数为属性集合里面的属性，R文件名称：R.styleable+属性集合名称+下划线+属性名称
        //第二个参数为，如果没有设置这个属性，则设置的默认的值
        defalutSize = a.getDimensionPixelSize(R.styleable.MyCustomViewTwo_default_size, 100);

        //最后记得将TypedArray对象回收
        a.recycle();
    }


    /**
     * 不会自动调用，如果有默认style时，在第二个构造函数中调用
     * @param context
     * @param attrs
     * @param defStyleAttr
     */
    public MyCustomViewTwo(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

    }


    /**
     * 只有在API版本>21时才会用到
     * 不会自动调用，如果有默认style时，在第二个构造函数中调用
     * @param context
     * @param attrs
     * @param defStyleAttr
     * @param defStyleRes
     */
    public MyCustomViewTwo(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    private int getMySize(int defaultSize, int measureSpec) {
        int mySize = defaultSize;

        int mode = MeasureSpec.getMode(measureSpec);
        int size = MeasureSpec.getSize(measureSpec);

        /**
         * UNSPECIFIED	无限制，View对尺寸没有任何限制，View设置为多大就应当为多大
         * AT_MOST	 最大模式，View的尺寸有一个最大值，View不可以超过MeasureSpec当中的Size值
         * EXACTLY   精准模式，View需要一个精确值，这个值即为MeasureSpec当中的Size
         */
        switch (mode) {
            case MeasureSpec.UNSPECIFIED: //如果没有指定大小，就设置为默认大小
                mySize = defaultSize;
                break;
            case MeasureSpec.AT_MOST: //如果测量模式是最大取值为size
                //我们将大小取最大值,你也可以取其他值
                mySize = size;
                break;
            case MeasureSpec.EXACTLY: //如果是固定的大小，那就不要去改变它
                mySize = size;
                break;

        }
        return mySize;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = getMySize(defalutSize, widthMeasureSpec);
        int height = getMySize(defalutSize, heightMeasureSpec);

        if (width < height) {
            height = width;
        } else {
            width = height;
        }

        setMeasuredDimension(width, height);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        //调用父View的onDraw函数，因为View这个类帮我们实现了一些
        // 基本的而绘制功能，比如绘制背景颜色、背景图片等
        super.onDraw(canvas);
        int r = getMeasuredWidth() / 2;//也可以是getMeasuredHeight()/2,本例中我们已经将宽高设置相等了

        int centerX = getMeasuredWidth() / 2;
        int centerY = getMeasuredWidth() / 2;
        Log.e(TAG, "getLeft()"+centerX+ "");
        Log.e(TAG, "getTop()"+centerY+"");
        Paint paint = new Paint();
        paint.setColor(Color.BLACK);
        //开始绘制
        //drawCircle 的 centerX，centerY 是已当前view（MyCustomViewTwo）的左上角作为原点
        canvas.drawCircle(centerX, centerY, r, paint);
    }



}
