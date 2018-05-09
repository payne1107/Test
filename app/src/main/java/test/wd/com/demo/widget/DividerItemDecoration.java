package test.wd.com.demo.widget;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import test.wd.com.demo.R;

/**
 * Created by 92457 on 2018/2/5.
 * 时间轴
 */

public class DividerItemDecoration extends RecyclerView.ItemDecoration{
    private Context mContext;
    private final Paint mPaint;
    private final Paint mPaint1;

    // 左 上偏移长度
    private int itemView_leftinterval;
    private int itemView_topinterval;
    // 轴点半径
    private int circle_radius;


    //在构造函数里进行绘制的初始化,如画笔属性设置等
    public DividerItemDecoration(Context context) {
        mContext = context;
        //轴点画笔(橘黄色)
        mPaint = new Paint();
        mPaint.setColor(mContext.getResources().getColor(R.color.ff6000));

        //左边时间文本画笔
        mPaint1 = new Paint();

        //赋值ItemView的左偏移长度为200
        itemView_leftinterval = 200;
        //赋值ItemView的上偏移长度为50
        itemView_topinterval = 50;
        //赋值轴点圆的半径为10
        circle_radius = 10;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        // 设置ItemView的左 & 上偏移长度分别为200 px & 50px,即此为onDraw()可绘制的区域
        outRect.set(itemView_leftinterval, itemView_topinterval, 0, 0);
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);
        //获取RecycleView的Child View的个数
        int childCount = parent.getChildCount();

        //遍历每个Item，分别获取他们的位置信息，然后在绘制对应的分割线
        for (int i = 0; i < childCount; i++) {
            //获取每个Item对象
            View child = parent.getChildAt(i);
            /***
             * 绘制轴点
             */
            //轴点 = 圆 = 圆心(x,y)
            float centerx = child.getLeft() - itemView_leftinterval / 3;
            //布局顶部
            float centery = child.getTop() + itemView_topinterval / 2; //itemView_topinterval / 2 解决圆点和右边文字没有对齐
            //布局中间
//            float centery = child.getTop() - itemView_topinterval + (itemView_topinterval + child.getHeight()) / 2

            //绘制轴点圆
            c.drawCircle(centerx, centery, circle_radius, mPaint);

            /***
             * 绘制上半轴线
             */
            //上端点坐标(x，y)
            float upLine_up_x = centerx;
            float upLine_up_y = child.getTop() - itemView_topinterval;

            // 下端点坐标(x,y)
            float upLine_bottom_x = centerx;
            float upLine_bottom_y = centery - circle_radius;

            //绘制上半部轴线
//            c.drawLine(upLine_up_x, upLine_up_y, upLine_bottom_x, upLine_bottom_y, mPaint);
            c.drawLine(upLine_up_x, upLine_up_x, upLine_bottom_x, upLine_bottom_y, mPaint);
            /**
             * 绘制下半轴线
             */
            // 上端点坐标(x,y)
            float bottomLine_up_x = centerx;
            float bottom_up_y = centery + circle_radius;

            // 下端点坐标(x,y)
            float bottomLine_bottom_x = centerx;
            float bottomLine_bottom_y = child.getBottom();

            //绘制下半部轴线
            c.drawLine(bottomLine_up_x, bottom_up_y, bottomLine_bottom_x, bottomLine_bottom_y, mPaint);

            /**
             * 绘制左边时间文本
             */
            // 获取每个Item的位置
            int index = parent.getChildAdapterPosition(child);
            // 设置文本起始坐标
            float Text_x = child.getLeft() - itemView_leftinterval * 5 / 6;
            float Text_y = upLine_bottom_y;

            //根据Item位置设置时间文本
            switch (index) {
                case 0:
                    c.drawBitmap(BitmapFactory.decodeResource(mContext.getResources(), R.mipmap.img_bz1),Text_x -30, Text_y-15, mPaint1);
                    break;
                case 1:
                    c.drawBitmap(BitmapFactory.decodeResource(mContext.getResources(), R.mipmap.img_bz2),Text_x -30, Text_y-15, mPaint1);
                    break;
                case 2:
                    c.drawBitmap(BitmapFactory.decodeResource(mContext.getResources(), R.mipmap.img_bz3),Text_x -30, Text_y-15, mPaint1);
                    break;
                case 3:
                    c.drawBitmap(BitmapFactory.decodeResource(mContext.getResources(), R.mipmap.img_bz4),Text_x -30, Text_y-15, mPaint1);
                    break;
                case 4:
                    c.drawBitmap(BitmapFactory.decodeResource(mContext.getResources(), R.mipmap.img_bz5),Text_x -30, Text_y-15, mPaint1);
                    break;
                case 5:
                    c.drawBitmap(BitmapFactory.decodeResource(mContext.getResources(), R.mipmap.img_bz6),Text_x -30, Text_y-15, mPaint1);
                    break;
            }
        }
    }
}
