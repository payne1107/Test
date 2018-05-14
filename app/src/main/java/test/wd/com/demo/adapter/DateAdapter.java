package test.wd.com.demo.adapter;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import test.wd.com.demo.R;

/**
 * Created by 92457 on 2018/5/14.
 */

public class DateAdapter  extends BaseAdapter {
    private int[] days = new int[42];
    private Context context;
    private int year;
    private int month;
    private int UPDATE_TEXT_COLOR;
    private ViewHolder viewHolder;

    public DateAdapter(Context context, int[][] days, int year, int month) {
        this.context = context;
        int dayNum = 0;
        //将二维数组转化为一维数组，方便使用
        for (int i = 0; i < days.length; i++) {
            for (int j = 0; j < days[i].length; j++) {
                this.days[dayNum] = days[i][j];
                dayNum++;
            }
        }
        this.year = year;
        this.month = month;
    }

    public void updateTextColor(int position) {
        UPDATE_TEXT_COLOR = position;
        Log.d("Dong", "UPDATE_TEXT_COLOR --" +UPDATE_TEXT_COLOR);
        notifyDataSetChanged();
    }

    /***
     * 设置选中后的字体颜色和背景色
     * @param position
     */
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    private void setTextStyle(int position) {
        if (UPDATE_TEXT_COLOR == position) {
            viewHolder.date_item.setTextColor(context.getResources().getColor(R.color.ff6000));
            viewHolder.date_item.setBackground(context.getResources().getDrawable(R.drawable.background_item));
        } else {
            Log.d("Dong", " UPDATE_TEXT_COLOR---" +UPDATE_TEXT_COLOR +" |||||| position--->" + position) ;
            viewHolder.date_item.setTextColor(context.getResources().getColor(R.color.cp_colorAccent));
            viewHolder.date_item.setBackground(context.getResources().getDrawable(R.drawable.background_item));
        }
    }
    @Override
    public int getCount() {
        return days.length;
    }

    @Override
    public Object getItem(int i) {
        return days[i];
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.item_calendar, null);
            viewHolder = new ViewHolder();
            viewHolder.date_item = (TextView) view.findViewById(R.id.date_item);

            setTextStyle(i);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        if (i < 7 && days[i] > 20) {
            viewHolder.date_item.setTextColor(Color.rgb(204, 204, 204));//将上个月的和下个月的设置为灰色
        } else if (i > 20 && days[i] < 15) {
            viewHolder.date_item.setTextColor(Color.rgb(204, 204, 204));
        }
        viewHolder.date_item.setText(days[i] + "");

        return view;
    }

    /**
     * 优化Adapter
     */
    class ViewHolder {
        TextView date_item;
    }
}
