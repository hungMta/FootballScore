package com.hungtran.footballscore.ui.navigation.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hungtran.footballscore.R;

import java.util.List;

/**
 * Created by Hung Tran on 28/06/2017.
 */

public class NavigationDrawerAdapter extends RecyclerView.Adapter {

    private List<String> listMenu;
    private Context mContext;

    public NavigationDrawerAdapter(Context context, List<String> list) {
        this.mContext = context;
        this.listMenu = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ItemView(View.inflate(mContext, R.layout.item_menu_navigation, null));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((ItemView) holder).txtMenu.setText(listMenu.get(position));
    }

    @Override
    public int getItemCount() {
        return listMenu == null ? 0 : listMenu.size();
    }

    private class ItemView extends RecyclerView.ViewHolder {

        public TextView txtMenu;

        public ItemView(View itemView) {
            super(itemView);
            txtMenu = (TextView) itemView.findViewById(R.id.title);
        }
    }

}
