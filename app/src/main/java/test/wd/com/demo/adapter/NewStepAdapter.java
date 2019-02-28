package test.wd.com.demo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import test.wd.com.demo.R;

/**
 * Created by 92457 on 2018/8/9.
 */

public class NewStepAdapter extends BaseAdapter {
    private Context mContext;
    private String[] arrays;
    public NewStepAdapter(Context context, String[] step) {
        this.mContext = context;
        this.arrays = step;
    }

    @Override
    public int getCount() {
        return arrays.length;
    }

    @Override
    public Object getItem(int position) {
        return arrays[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(mContext).inflate(R.layout.item_new_step, null);
        TextView tvStepName = (TextView) convertView.findViewById(R.id.tv_step_name);
        tvStepName.setText(arrays[position]);
        return convertView;
    }
}
