package test.wd.com.demo.adapter;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import test.wd.com.demo.R;

/**
 * Created by 92457 on 2018/2/5.item_tg_task_content
 */

public class TGTaskContentAdapter extends RecyclerView.Adapter{
    private LayoutInflater inflater;
    private List<String> mList;
    private Context mContext;

    public TGTaskContentAdapter(Context context, List<String> list) {
        this.mList = list;
        mContext = context;
        inflater = LayoutInflater.from(context);
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(inflater.inflate(R.layout.item_tg_task_content, null));
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        //绑定数据到ViewHolder里面
        ViewHolder viewHolde = (ViewHolder) holder;
        viewHolde.title.setText(mList.get(position));
        GridLayoutManager gridLayoutManager = new GridLayoutManager(mContext, 4);
        viewHolde.itemRecycleView.setLayoutManager(gridLayoutManager);
        ItemRecycleViewAdapter adapter = new ItemRecycleViewAdapter(mContext,mList);
        viewHolde.itemRecycleView.setAdapter(adapter);

        adapter.setmOnItemClickListener(new ItemRecycleViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Log.d("Dong", "positon = " + position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView title;
        private RecyclerView itemRecycleView;
        public ViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.title);
            itemRecycleView = (RecyclerView) itemView.findViewById(R.id.item_recycler_view);
        }
    }
}
