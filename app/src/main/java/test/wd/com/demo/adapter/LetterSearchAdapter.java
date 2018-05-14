package test.wd.com.demo.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;

import test.wd.com.demo.R;
import test.wd.com.demo.bean.Contacts;
import test.wd.com.demo.utils.PinyinUtils;

/**
 * Created by 92457 on 2018/5/9.
 */

public class LetterSearchAdapter extends BaseAdapter{
    private final String[] sections;
    private final HashMap<String, Integer> letterIndexes;
    private Context mContext;
    private List<Contacts> mList;
    private LayoutInflater inflater;
    public LetterSearchAdapter(Context context, List<Contacts> list) {
        this.mContext = context;
        this.mList = list;
        this.inflater = LayoutInflater.from(mContext);
        int size = mList.size();
        sections = new String[size];
        letterIndexes = new HashMap<>();
        for (int index = 0;index<mList.size();index++) {
            //当前城市拼音首字母
            String currentLetter = PinyinUtils.getFirstLetter(mList.get(index).getPinyin());
            //上个首字母，如果不存在设为""
            String previousLetter = index >= 1 ? PinyinUtils.getFirstLetter(mList.get(index - 1).getPinyin()) : "";
            if (!TextUtils.equals(currentLetter, previousLetter)){
                letterIndexes.put(currentLetter, index);
                sections[index] = currentLetter;
            }
        }
    }

    /**
     * 获取字母索引的位置
     * @param letter
     * @return
     */
    public int getLetterPosition(String letter){
        Integer integer = letterIndexes.get(letter);
        return integer == null ? -1 : integer;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        CityViewHolder holder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.cp_item_city_listview, parent, false);
            holder = new CityViewHolder();
            holder.letter = (TextView) convertView.findViewById(R.id.tv_item_city_listview_letter);
            holder.name = (TextView) convertView.findViewById(R.id.tv_item_city_listview_name);
            convertView.setTag(holder);
        }else{
            holder = (CityViewHolder) convertView.getTag();
        }
//        if (position >= 1){
            final String city = mList.get(position).getName();
            holder.name.setText(city);
            String currentLetter = PinyinUtils.getFirstLetter(mList.get(position).getPinyin());
            String previousLetter = position >= 1 ? PinyinUtils.getFirstLetter(mList.get(position - 1).getPinyin()) : "";
            if (!TextUtils.equals(currentLetter, previousLetter)){
                holder.letter.setVisibility(View.VISIBLE);
                holder.letter.setText(currentLetter);
            }else{
                holder.letter.setVisibility(View.GONE);
            }
//            holder.name.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    if (onCityClickListener != null){
//                        onCityClickListener.onCityClick(city);
//                    }
//                }
//            });
//        }
        return convertView;
    }

    public static class CityViewHolder{
        TextView letter;
        TextView name;
    }
}
