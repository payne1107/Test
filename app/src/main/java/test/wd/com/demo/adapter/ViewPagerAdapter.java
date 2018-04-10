package test.wd.com.demo.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

import test.wd.com.demo.R;

/**
 * Created by 92457 on 2018/4/5.
 */

public class ViewPagerAdapter extends PagerAdapter{

    private Context mContext;
    private List mList;
    public ViewPagerAdapter(List mList, Context mContext) {
        this.mContext = mContext;
        this.mList = mList;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_viewpager, null);
        container.addView(view);
        ImageView imageView = (ImageView) view.findViewById(R.id.imageView);
        imageView.setImageResource((Integer) mList.get(position));
        return view;

    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
//        super.destroyItem(container, position, object);
    }
}
